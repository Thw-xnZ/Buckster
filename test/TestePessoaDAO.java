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
    }

}