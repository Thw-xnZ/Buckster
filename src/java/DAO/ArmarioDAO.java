/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import enums.StatusArmario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Armario;
import model.Cliente;
import util.Conexao;

/**
 *
 * @author pablo
 */
public class ArmarioDAO {
    
    public void cadastrar(Armario a) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        
        PreparedStatement comando = con.prepareStatement("insert into armarios (descricao, status, cliente_id) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        
        comando.setString(1, a.getDescricao());
        comando.setString(2, a.getStatus().name());
        
        if (a.getCliente() != null){
            comando.setInt(3, a.getCliente().getId());
        } else {
            comando.setNull(3, java.sql.Types.INTEGER);
        }
        
        comando.executeUpdate();
        
        ResultSet rs = comando.getGeneratedKeys();
        
        if (rs.next()){
            a.setId(rs.getInt(1));
        }
        
        rs.close();
        comando.close();
        con.close();
    }
    
    public void deletar(Armario a) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        
        PreparedStatement comando = con.prepareStatement("delete from armarios where id = ?");
        
        comando.setInt(1, a.getId());
        comando.execute();
        
        comando.close();
        con.close();
    }
    
    public void atualizar(Armario a) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        
        PreparedStatement comando = con.prepareStatement("update armarios set descricao = ?, status = ?, cliente_id = ? where id = ?");
        
        comando.setString(1, a.getDescricao());
        comando.setString(2, a.getStatus().name());
        
        if (a.getCliente() != null){
            comando.setInt(3, a.getCliente().getId());
        } else {
            comando.setNull(3, java.sql.Types.INTEGER);
        }
        
        comando.setInt(4, a.getId());
        
        comando.executeUpdate();
        
        comando.close();
        con.close();
    }
    
    public Armario consultarById(Armario a) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        
        PreparedStatement comando = con.prepareStatement("select * from armarios where id = ?");
        
        comando.setInt(1, a.getId());
        
        ResultSet rs = comando.executeQuery();
        
        if (rs.next()){
            Armario arm = new Armario();
            
            arm.setId(rs.getInt("id"));
            arm.setDescricao(rs.getString("descricao"));
            arm.setStatus(StatusArmario.valueOf(rs.getString("status")));
            
            int clienteId = rs.getInt("cliente_id");
            
            if (!rs.wasNull()){
                PessoaDAO pessoaDAO = new PessoaDAO();
                Cliente cliente = pessoaDAO.consultarCliente(clienteId);
                
                arm.setCliente(cliente);
            }
            
            rs.close();
            comando.close();
            con.close();
        }
        rs.close();
        comando.close();
        con.close();
        
        return null;
    }
    
    public List<Armario> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        
        PreparedStatement comando = con.prepareStatement("select * from armarios");
        
        ResultSet rs = comando.executeQuery();
                
        List<Armario> larm = new ArrayList<Armario>();
        while(rs.next()){
            Armario arm = new Armario();
            
            arm.setId(rs.getInt("id"));
            arm.setDescricao(rs.getString("descricao"));
            arm.setStatus(StatusArmario.valueOf(rs.getString("status")));
            
            int clienteId = rs.getInt("cliente_id");
            
            if(!rs.wasNull()){
                PessoaDAO pessoaDAO = new PessoaDAO();
                Cliente cliente = pessoaDAO.consultarCliente(clienteId);
                
                arm.setCliente(cliente);
            }
            
            larm.add(arm);
        }
        rs.close();
        comando.close();
        con.close();
        
        return larm;
    }
}

