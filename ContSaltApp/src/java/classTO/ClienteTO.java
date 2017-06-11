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
    private double peso;
    private String cpf;
    private String celular;

    /*Atributo displayName foi criado para implantar
    inputs autoComplete em paginas .xhtml utilizando o Prime Face*/ 
   private String displayName;
   
   
    public ClienteTO() {
   
    }

   public ClienteTO(int idCliente, String nome, double peso, String cpf, String celular, String displayName) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.peso = peso;
        this.cpf = cpf;
        this.celular = celular;
        this.displayName = displayName;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

   public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;        
    }
        
   
    
    
    /**
    * Método para sobrescrever o método toString da classe String.
    * Este método  foi criado para os inputs autoComplete em paginas .xhtml utilizando o Prime Faces.
    * @return nome String - nome do cliente.
    */    
    @Override
    public String toString() {
        return nome;
    }
}
