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
        /*O vetor é inicializado com a quantidade de taxas de sobrepeso registradas no banco*/
        this.setIdTaxasSobrepeso(idTaxasSobrepeso = new int[tDAO.contarTaxasDeSobrePesoArmazenadas()]);
  
     }

    public void taxasDeSobrepesoDoInstrutor(){
        /*A lista com todas as taxas de sobre peso armazenadas no banco.*/
        List<TaxaSobrepesoTO> list = tiDAO.getTaxasSobrepesos();
        /* É possivel saber o tamanho do espaço reservado na memória para o vetor idTaxasSobrepeso,
        mas não é possível saber quantos espaços estão ocupados, então foi utilizado .length .*/       
        for (int i = 0; i < idTaxasSobrepeso.length ; i++) { 
            tiDAO.salvarTaxaDoInstrutor(idInstrutor, idTaxasSobrepeso[i]);               
            for(TaxaSobrepesoTO taxa : list){
                /*Se na lista(listTaxas) houver uma ou mais taxas com as identifcações(IdTaxaSobrepeso) iguais as identificações das taxas selecionadas pelo usuário,
                estas taxas seram removidas da lista(listTaxas)*/
                if(taxa.getIdTaxaSobrepeso()==idTaxasSobrepeso[i]){
                    list.remove(taxa);
                    break;
                }
            }
        }
        /*Após as taxas serem removidas da lista, a lista resultante é das taxas que o intrutor não faz.
        Então, caso estejão cadastradas, será desfeito o relacionamento com o instrutor*/
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
