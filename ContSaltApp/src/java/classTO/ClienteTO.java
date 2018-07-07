/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classTO;


/**
 *Classe para estanciar os clientes.
 * @author Almir
 * @version 1.0
 */
public class ClienteTO extends PessoaTO{
    private int idCliente;
    private double peso=0.0;

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


    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

}
