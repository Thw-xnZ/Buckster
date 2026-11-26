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
            String sqlPessoa = "insert into pessoa (nome) values (?)";
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
            
            // Esse if testa se o objeto p referenciado é um cliente, se for, da continuidade
            if (p instanceof Cliente) {
                
                Cliente c = (Cliente) p;
                
                String sqlCliente = "insert into cliente (id, curso, telefone) " + "values (?, ?, ?)";
                
                try (PreparedStatement comando = con.prepareStatement(sqlCliente)) {
                    
                    comando.setInt(1, c.getId());
                    comando.setString(2, c.getCurso());
                    comando.setString(3, c.getTelefone());
                    
                    comando.executeUpdate();
                }
                // se o If anterior não for um cliente, automaticamente vem para o else para testar se é um usuario 
            } else if (p instanceof Usuario) {
                
                Usuario u = (Usuario) p;
                
                String sqlUsuario = "insert into usuario (id, email, login, senha) " + "values (?, ?, ?, ?)";
                
                try (PreparedStatement comando = con.prepareStatement(sqlUsuario)) {
                    
                    comando.setInt(1, u.getId());
                    comando.setString(2, u.getEmail());
                    comando.setString(3, u.getLogin());
                    comando.setString(4, u.getSenha());
                    
                    comando.executeUpdate();
                }
            }
            
            // aqui o commit faz a confirmação dos dados no banco de dados 
            con.commit();
            
        } catch (SQLException ex) {

            // O SQLException se captado por algum erro no processo realiza um rollback, para evitar
            // inconsistencia do banco diante a um erro
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
    public Cliente consultarCliente(int id) throws ClassNotFoundException, SQLException {

    String sql = "select pessoa.id, pessoa.nome, "
                + "cliente.curso, cliente.telefone "
                + "from pessoa "
                + "INNER JOIN cliente on pessoa.id = cliente.id "
                + "where pessoa.id = ?";

    try (Connection con = Conexao.getConexaoMySQL();
         PreparedStatement comando = con.prepareStatement(sql)) {

        comando.setInt(1, id);

        // Aqui ele pega o resultado da busca e coloca dentro do objeto rs, para podermos retirar as informações que desejamos
        // como o id, nome, curso e telefone do cliente  
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
    public Usuario consultarUsuario(int id) throws ClassNotFoundException, SQLException {

    String sql = "select pessoa.id, pessoa.nome, "
                + "usuario.email, usuario.login, usuario.senha "
                + "from pessoa "
                + "INNER JOIN usuario on pessoa.id = usuario.id "
                + "where pessoa.id = ?";

    try (Connection con = Conexao.getConexaoMySQL();
         PreparedStatement comando = con.prepareStatement(sql)) {

        comando.setInt(1, id);

        // Aqui ele pega o resultado da busca e coloca dentro do objeto rs, para podermos retirar as informações que desejamos
        // como o id, nome, email e login do usuario 
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
    
    public void atualizar(Pessoa p) throws ClassNotFoundException, SQLException {

        if (!(p instanceof Cliente) && !(p instanceof Usuario)) {
            throw new SQLException("Tipo de pessoa inválido.");
        }

        try (Connection con = Conexao.getConexaoMySQL()) {

            con.setAutoCommit(false);

            try {
                // Atualiza os dados comuns da pessoa
                String sqlPessoa = "update pessoa set nome = ? where id = ?";

                try (PreparedStatement comando = con.prepareStatement(sqlPessoa)) {
                    comando.setString(1, p.getNome());
                    comando.setInt(2, p.getId());

                    int linhasAfetadas = comando.executeUpdate();

                    if (linhasAfetadas == 0) {
                        throw new SQLException("Pessoa não encontrada.");
                    }
                }

                // Atualiza os dados específicos do Cliente
                if (p instanceof Cliente) {

                    Cliente c = (Cliente) p;

                    String sqlCliente = "update cliente set curso = ?, telefone = ? where id = ?";

                    try (PreparedStatement comando = con.prepareStatement(sqlCliente)) {
                        comando.setString(1, c.getCurso());
                        comando.setString(2, c.getTelefone());
                        comando.setInt(3, c.getId());

                        if (comando.executeUpdate() == 0) {
                            throw new SQLException("Cliente não encontrado.");
                        }
                    }

                // Atualiza os dados específicos do Usuario
                } else if (p instanceof Usuario) {

                    Usuario u = (Usuario) p;

                    String sqlUsuario = "update usuario set email = ?, login = ?, senha = ? where id = ?";

                    try (PreparedStatement comando = con.prepareStatement(sqlUsuario)) {
                        comando.setString(1, u.getEmail());
                        comando.setString(2, u.getLogin());
                        comando.setString(3, u.getSenha());
                        comando.setInt(4, u.getId());

                        if (comando.executeUpdate() == 0) {
                            throw new SQLException("Usuário não encontrado.");
                        }
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
            }
        }
    }
    
    
    public void excluir(Pessoa p) throws ClassNotFoundException, SQLException {

        if (!(p instanceof Cliente) && !(p instanceof Usuario)) {
            throw new SQLException("Tipo de pessoa inválido.");
        }

        try (Connection con = Conexao.getConexaoMySQL()) {

            con.setAutoCommit(false);

            try {

                // Verifica se é um cliente, se for, excluí os dados específicos do Cliente
                if (p instanceof Cliente) {

                    String sqlCliente = "delete from cliente where id = ?";

                    try (PreparedStatement comando = con.prepareStatement(sqlCliente)) {

                        comando.setInt(1, p.getId());

                        if (comando.executeUpdate() == 0) {
                            throw new SQLException("Cliente não encontrado.");
                        }
                    }

                // Se for um Usuario, exclui os dados específicos do Usuario
                } else if (p instanceof Usuario) {

                    String sqlUsuario = "delete from usuario where id = ?";

                    try (PreparedStatement comando = con.prepareStatement(sqlUsuario)) {

                        comando.setInt(1, p.getId());

                        if (comando.executeUpdate() == 0) {
                            throw new SQLException("Usuário não encontrado.");
                        }
                    }
                }

                // Exclui os dados comuns da Pessoa
                String sqlPessoa = "delete from pessoa where id = ?";

                try (PreparedStatement comando = con.prepareStatement(sqlPessoa)) {

                    comando.setInt(1, p.getId());

                    if (comando.executeUpdate() == 0) {
                        throw new SQLException("Pessoa não encontrada.");
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
            }
        }
    }

}
