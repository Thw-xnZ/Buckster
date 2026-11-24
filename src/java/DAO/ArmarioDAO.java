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
        PreparedStatement comando = con.prepareStatement("insert into armarios (status, cliente) values (?,?)");
        comando.setObject(1, a.getStatus());
        comando.setObject(2, a.getCliente());
        comando.execute();
        con.close();
    }
    
    public void deletar(Armario a) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("delete from armarios where id = ?");
        comando.setInt(1, a.getId());
        comando.execute();
        con.close();
    }
    
    public void atuualizar(Armario a) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("update armarios set status = ?, cliente = ?, where id = ?");
        comando.setObject(1, a.getStatus());
        comando.setObject(2, a.getCliente());
        comando.setInt(3, a.getId());
        comando.execute();
        con.close();
    }
    
    public Armario consultarById(Armario a) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("select * from armarios where id = ?");
        comando.setInt(1, a.getId());
        ResultSet rs = comando.executeQuery();
        Armario arm = new Armario();
        if (rs.next()){
            arm.setId(rs.getInt("id"));
            arm.setStatus((StatusArmario) rs.getObject("status"));
            arm.setCliente((Cliente) rs.getObject("cliente"));
        }        
        return arm;
    }
    
    public List<Armario> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexaoMySQL();
        PreparedStatement comando = con.prepareStatement("select * from armarios");
        ResultSet rs = comando.executeQuery();
                
        List<Armario> larm = new ArrayList<Armario>();
        while(rs.next()){
            Armario arm = new Armario();
            arm.setId(rs.getInt("id"));
            arm.setStatus((StatusArmario) rs.getObject("status"));
            arm.setCliente((Cliente) rs.getObject("cliente"));
            larm.add(arm);
        }
        return larm;
    }
}

