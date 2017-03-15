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
    private double valor;
    private int codigo;

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
    
     public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
}
