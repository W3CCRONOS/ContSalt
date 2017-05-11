/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classDAO.TaxasInstrutoresDAO;
import classTO.InstrutorTO;
import classTO.TaxaSobrepesoTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Almir
 */
@Named(value = "taxasInstrutoresBean")
@SessionScoped
public class TaxasInstrutoresBean implements Serializable {

    private int idInstrutor;
    private int idTaxasSobrepeso[];

    public TaxasInstrutoresBean() {
        this.setIdInstrutor(idInstrutor = 0);
        this.setIdTaxasSobrepeso(idTaxasSobrepeso = new int[3]);
        

    }

    public void salvar(){

        System.out.println(idInstrutor);
        
        for (int i = 0; i < idTaxasSobrepeso.length ; i++) { 
           System.out.println(idTaxasSobrepeso[i]);
        }
        
        System.out.println("------------");
        System.out.println("------------");
        System.out.println("------------");
        System.out.println("------------");
        
    }
    
    public int getIdInstrutor() {
        return idInstrutor;
    }

    public void setIdInstrutor(int idInstrutor) {
        this.idInstrutor = idInstrutor;
    }

    public int[] getIdTaxasSobrepeso() {
        return idTaxasSobrepeso;
    }

    public void setIdTaxasSobrepeso(int[] idTaxasSobrepeso) {
        this.idTaxasSobrepeso = idTaxasSobrepeso;
    }
    
}
