/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import enums.StatusReserva;
/**
 *
 * @author pablo
 */
public class OrdemReserva {
    private int id;
    private Cliente cliente;
    private Armario armario;
    private Usuario usuario;
    private StatusReserva status;
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    
    public Cliente getCliente(){
        return cliente;
    }
    
    public void setArmario(Armario armario){
        this.armario = armario;
    }
    
    public Armario getArmario(){
        return armario;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public void setStatus(StatusReserva status){
        this.status = status;
    }
    
    public StatusReserva getStatus(){
        return status;
    }
    
    public void efetuarReserva(){
        
    }
    
    public void cancelarReserva(){
        
    }
    
}
