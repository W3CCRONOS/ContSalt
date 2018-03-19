/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classDAO.TaxaSobrepesoDAO;
import classDAO.TaxasInstrutoresDAO;
import classTO.TaxaSobrepesoTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Almir
 */
@Named(value = "taxasInstrutoresBean")
@SessionScoped
public class TaxasInstrutoresBean implements Serializable {
   
    //idInstrutor armazenará a identificação do instrutor selecionado.
    private int idInstrutor;
    //idTaxasSobrepeso e um vetor que armazenará as identificações das taxas selecionadas.
    private int idTaxasSobrepeso[];
    private TaxasInstrutoresDAO tiDAO; 
    private TaxaSobrepesoDAO tDAO;

    public TaxasInstrutoresBean() {
        this.setIdInstrutor(idInstrutor = 0);
        this.setTiDAO( new TaxasInstrutoresDAO());
        this.settDAO(new TaxaSobrepesoDAO());
       /*O vetor é inicializado com a quantidade de registros das taxas de sobrepeso armazenadas no banco*/
        this.setIdTaxasSobrepeso(idTaxasSobrepeso = new int[tDAO.contarTaxasDeSobrePesoArmazenadas()]);
  
     }

    public void taxasDeSobrepesoDoInstrutor(){
        /*Lista com todas as taxas de sobre peso armazenadas no banco.*/
        List<TaxaSobrepesoTO> list = tDAO.getTaxasSobrepesos();
        /* .length, informa o comprimento do vetor idTaxasSobrepeso.*/       
        for (int i = 0; i < idTaxasSobrepeso.length ; i++) { 
            tiDAO.salvarTaxaDoInstrutor(idInstrutor, idTaxasSobrepeso[i]);               
            for(TaxaSobrepesoTO taxa : list){
                /*As taxas selecionadas pelo usuário seram removidas da lista*/
                if(taxa.getIdTaxaSobrepeso()==idTaxasSobrepeso[i]){
                    list.remove(taxa);
                    break;
                }
            }
        }
        /* A lista resultante possui os taxas que o intrutor não realiza.
        Caso existam registros no banco, serão apagados os registros de relacionamentos com o instrutor*/
        for(TaxaSobrepesoTO taxa : list){
            tiDAO.excluirTaxaDoInstrutor(idInstrutor, taxa.getIdTaxaSobrepeso());
        }
        
    }
    
   public List<TaxaSobrepesoTO> getTaxasDeSobrepesoPorInstrutor(int idInstrutor){
        return tiDAO.getTaxasDeSobrepesoPorInstrutor(idInstrutor);
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

    public TaxasInstrutoresDAO getTiDAO() {
        return tiDAO;
    }

    public void setTiDAO(TaxasInstrutoresDAO tiDAO) {
        this.tiDAO = tiDAO;
    }

    public TaxaSobrepesoDAO gettDAO() {
        return tDAO;
    }

    public void settDAO(TaxaSobrepesoDAO tDAO) {
        this.tDAO = tDAO;
    }
    
}
