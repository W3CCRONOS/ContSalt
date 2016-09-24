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
    private double pesoMaximo;
    private double valorTaxaSobrePeso;

    public int getIdTaxaSobrepeso() {
        return idTaxaSobrepeso;
    }

    public void setIdTaxaSobrepeso(int idTaxaSobrepeso) {
        this.idTaxaSobrepeso = idTaxaSobrepeso;
    }
    
    
    public double getPesoMaximo() {
        return pesoMaximo;
    }

    public void setPesoMaximo(double pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }

    public double getValorTaxaSobrePeso() {
        return valorTaxaSobrePeso;
    }

    public void setValorTaxaSobrePeso(double valorTaxaSobrePeso) {
        this.valorTaxaSobrePeso = valorTaxaSobrePeso;
    }
    
}
