/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import enums.StatusArmario;
/**
 *
 * @author pablo
 */
public class Armario {
    private int id;
    private String descricao;
    private StatusArmario status;
    private Cliente cliente;
    
   public void setId (int id){
       this.id = id;
   }
   
   public int getId(){
       return id;
   }
   
   public void setDescricao (String descricao){
       this.descricao = descricao;
   }
   
   public String getDescricao(){
       return descricao;
   }
   
   public void setStatus(StatusArmario status){
       this.status = status;
   }
   
   public StatusArmario getStatus(){
       return status;
   }
   
   public void setCliente(Cliente cliente){
       this.cliente = cliente;
   }
   
   public Cliente getCliente(){
       return cliente;
   }
}