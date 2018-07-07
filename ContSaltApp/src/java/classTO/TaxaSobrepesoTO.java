/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classTO;

/**
 * Classe para estanciar as taxas de sobrepeso.
 * @author Almir
 * @version 1.0
 */
public class TaxaSobrepesoTO {
    
    private int idTaxaSobrepeso;
    private double valor = 0.0;
    private double peso = 0.0;


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

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
  
 
}
