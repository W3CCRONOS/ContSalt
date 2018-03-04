/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classTO;


/**
 *Classe que representa um cliente de uma escola/club
 * de paraquedismo.
 * @author Almir
 * @version 1.0
 */
public class ClienteTO {
    private int idCliente;
    private String nome;
    private double peso=0.0;
    private String cpf;

    public ClienteTO() {
   
    }

   public ClienteTO(int idCliente, String nome, double peso, String cpf, String celular, String displayName) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.peso = peso;
        this.cpf = cpf;
    }   
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
