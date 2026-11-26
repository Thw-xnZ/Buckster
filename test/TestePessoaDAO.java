/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import DAO.PessoaDAO;
import model.Cliente;
import model.Usuario;

/**
 *
 * @author pablo
 */

public class TestePessoaDAO {

    
    public static void main(String[] args) throws Exception {

        /* Cadastrar Cliente Teste
        Cliente cliente = new Cliente();

        cliente.setNome("João Teste");
        cliente.setCurso("Informática");
        cliente.setTelefone("11999999999");

        PessoaDAO dao = new PessoaDAO();

        dao.cadastrar(cliente);

        System.out.println("Cliente cadastrado!");
        System.out.println("ID gerado: " + cliente.getId());
        */
        
        /* Cadastrar Usuario Teste
        Usuario usuario = new Usuario();

        usuario.setNome("Administrador Teste");
        usuario.setEmail("admin@buckster.com");
        usuario.setLogin("admin");
        usuario.setSenha("123456");

        PessoaDAO dao = new PessoaDAO();

        dao.cadastrar(usuario);

        System.out.println("Usuário cadastrado!");
        System.out.println("ID gerado: " + usuario.getId());
        */
        
        PessoaDAO dao = new PessoaDAO();
        
        /* Consultar Cliente
        Cliente cliente = dao.consultarCliente(1);
        
        if (cliente != null) {
            
            System.out.println("Cliente encontrado!");
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Curso: " + cliente.getCurso());
            System.out.println("Telefone: " + cliente.getTelefone());
        } else {
            System.out.println("Cliente não encontrado.");
        }
        */
        
        /* Consultar Usuario
        Usuario usuario = dao.consultarUsuario(2);
        
        if (usuario != null) {
            
            System.out.println("Usuario encontrado!");
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Login: " + usuario.getLogin());
        } else {
            System.out.println("Usuario não encontrado.");
        }
        */
        
        /* Atualiza os dados do cliente
        Cliente cliente = dao.consultarCliente(1);

        if (cliente != null) {

            // Altera os dados
            cliente.setNome("João Atualizado");
            cliente.setCurso("Desenvolvimento de Sistemas");
            cliente.setTelefone("11988887777");

            // Atualiza no banco
            dao.atualizar(cliente);

            System.out.println("Cliente atualizado com sucesso!");

        } else {
            System.out.println("Cliente não encontrado.");
        }
        */
        
        /* Atualiza os dados do Usuario
        Usuario usuario = dao.consultarUsuario(2);

        if (usuario != null) {

            usuario.setNome("Administrador Atualizado");
            usuario.setEmail("admin.atualizado@buckster.com");
            usuario.setLogin("admin atualizado");
            usuario.setSenha("654321");

            dao.atualizar(usuario);

            System.out.println("Usuário atualizado com sucesso!");

        } else {
            System.out.println("Usuário não encontrado.");
        }
        */

        /*
        // Exclui os dados do cliente
        Cliente cliente = dao.consultarCliente(1);

        if (cliente != null) {

            // Exclui o cliente
            dao.excluir(cliente);

            System.out.println("Cliente excluído com sucesso!");

        } else {
            System.out.println("Cliente não encontrado.");
        }
        */
        
        // Exclui os dados do Usuario
        Usuario usuario = dao.consultarUsuario(2);

        if (usuario != null) {

            // Exclui o cliente
            dao.excluir(usuario);

            System.out.println("Usuario excluído com sucesso!");

        } else {
            System.out.println("Usuario não encontrado.");
        }
    }

}