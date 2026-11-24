/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Pessoa;
import model.Cliente;
import model.Usuario;
import util.Conexao;

/**
 *
 * @author pablo
 */

public class PessoaDAO {
    
    public void cadastrar(Pessoa p) throws ClassNotFoundException, SQLException {
        if (!(p instanceof Cliente) && !(p instanceof Usuario)) {
            throw new SQLException("Tipo de pessoa inválido.");
        }
        
        try (Connection con = Conexao.getConexaoMySQL()) {
            
            con.setAutoCommit(false);
            
           try {
            // Cadastrar os dados principais da pessoa
            String sqlPessoa = "INSERT INTO pessoa (nome) VALUES (?)";
            try (PreparedStatement comando = con.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS)) {
                
                comando.setString(1, p.getNome());
                comando.executeUpdate();
                
                // Comando para recuperar o ID gerado pelo MySQL
                try (ResultSet rs = comando.getGeneratedKeys()) {
                    if (rs.next()) {
                        p.setId(rs.getInt(1));
                    } else {
                        throw new SQLException("Não foi possivel recuperar o ID.");
                    }
                }
            }
            // Cadastrar os dados especificos da pessoa (Usuario ou Cliente)
            if (p instanceof Cliente) {
                
                Cliente c = (Cliente) p;
                
                String sqlCliente = "INSERT INTO cliente (id, curso, telefone) " + "VALUES (?, ?, ?)";
                
                try (PreparedStatement comando = con.prepareStatement(sqlCliente)) {
                    
                    comando.setInt(1, c.getId());
                    comando.setString(2, c.getCurso());
                    comando.setString(3, c.getTelefone());
                    
                    comando.executeUpdate();
                }
            } else if (p instanceof Usuario) {
                
                Usuario u = (Usuario) p;
                
                String sqlUsuario = "INSERT INTO usuario (id, email, login, senha) " + "VALUES (?, ?, ?, ?)";
                
                try (PreparedStatement comando = con.prepareStatement(sqlUsuario)) {
                    
                    comando.setInt(1, u.getId());
                    comando.setString(2, u.getEmail());
                    comando.setString(3, u.getLogin());
                    comando.setString(4, u.getSenha());
                    
                    comando.executeUpdate();
                }
            }
            
            con.commit();
            
        } catch (SQLException ex) {

            try {
                con.rollback();
            } catch (SQLException erroRollback) {
                ex.addSuppressed(erroRollback);
            }

            throw ex;
        } finally {
            con.close();
        }
    }
}
    public Cliente consultarCliente(int id)
        throws ClassNotFoundException, SQLException {

    String sql = "SELECT pessoa.id, pessoa.nome, "
                + "cliente.curso, cliente.telefone "
                + "FROM pessoa "
                + "INNER JOIN cliente ON pessoa.id = cliente.id "
                + "WHERE pessoa.id = ?";

    try (Connection con = Conexao.getConexaoMySQL();
         PreparedStatement comando = con.prepareStatement(sql)) {

        comando.setInt(1, id);

        try (ResultSet rs = comando.executeQuery()) {

            if (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCurso(rs.getString("curso"));
                cliente.setTelefone(rs.getString("telefone"));

                return cliente;
            }
        }
    }

    return null;
}
    public Usuario consultarUsuario(int id)
        throws ClassNotFoundException, SQLException {

    String sql = "SELECT pessoa.id, pessoa.nome, "
                + "usuario.email, usuario.login, usuario.senha "
                + "FROM pessoa "
                + "INNER JOIN usuario ON pessoa.id = usuario.id "
                + "WHERE pessoa.id = ?";

    try (Connection con = Conexao.getConexaoMySQL();
         PreparedStatement comando = con.prepareStatement(sql)) {

        comando.setInt(1, id);

        try (ResultSet rs = comando.executeQuery()) {

            if (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));

                return usuario;
            }
        }
    }

    return null;
}
}
