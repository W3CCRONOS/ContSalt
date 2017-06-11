/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classTO;

/**
 *
 * @author Almir
 */
public class TaxaSobrepesoTO {
    
    private int idTaxaSobrepeso;
    private double valor = 0.0;
    private String descricao;


    public int getIdTaxaSobrepeso() {
        return idTaxaSobrepeso;
    }

    public void setIdTaxaSobrepeso(int idTaxaSobrepeso) {
        this.idTaxaSobrepeso = idTaxaSobrepeso;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
 
}
