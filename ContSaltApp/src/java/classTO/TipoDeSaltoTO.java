/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classTO;

/**
 *
 * @author Almir
 * 
 */
public class TipoDeSaltoTO {
    
    private int idTipoDeSalto;
    private String nome;
    private double valor = 0.0;
    private String taxaDeSobrePeso = "false";

    
    public int getIdTipoDeSalto() {
        return idTipoDeSalto;
    }

    public void setIdTipoDeSalto(int idTipoDeSalto) {
        this.idTipoDeSalto = idTipoDeSalto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }        

    public String getTaxaDeSobrePeso() {
        return taxaDeSobrePeso;
    }

    public void setTaxaDeSobrePeso(String taxaDeSobrePeso) {
        this.taxaDeSobrePeso = taxaDeSobrePeso;
    }
    
    
}
