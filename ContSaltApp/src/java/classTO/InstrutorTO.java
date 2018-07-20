/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classTO;

import java.sql.Date;

/**
 * Classe para estanciar os instrutores.
 * @author Almir
 * @version 1.0
 */
public class InstrutorTO extends PessoaTO{
    private int idInstrutor;   
    private Date admissao;// Data de admissão do instrutor na empresa.
    /*A variável(presenca) amazenará duas strings: false ou true. 
    "false" significa que o instrutor está ausente e não poderá realizar saltos.
    "true" significa que o instrutor está presente e pode realizar saltos*/
    private String presenca = "false";
    /*Peso máximo que um cliente deverá ter para que o instrutor possa
    realizar o salto - Limite de peso para um cliente*/
    private double peso = 0.0;
    
    
    public int getIdInstrutor() {
        return idInstrutor;
    }

    public void setIdInstrutor(int idInstrutor) {
        this.idInstrutor = idInstrutor;
    }
    public Date getAdmissao() {
        return admissao;
    }

    public void setAdmissao(Date admissao) {
        this.admissao = admissao;
    }

    public String getPresenca() {
        return presenca;
    }

    public void setPresenca(String presenca) {
        this.presenca = presenca;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
}
