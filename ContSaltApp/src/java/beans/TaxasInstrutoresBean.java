/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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

    private int idInstrutor;
    private int idTaxasSobrepeso[];
    private TaxasInstrutoresDAO tiDAO; 
    private TaxaSobrepesoTO taxaTO;

    public TaxasInstrutoresBean() {
        this.setIdInstrutor(idInstrutor = 0);
        this.setTiDAO( new TaxasInstrutoresDAO());
        /* A variável quantidadeDeRegistros é inicializada com o número 
        total de taxas de sobre peso armazenados no banco .
        */
        int quantidadeDeRegistros = tiDAO.ContarTaxasArmazenadas();
        /*O vetor idTaxasSobrepeso é inicializado, recebendo a variável quantidadeDeRegistros*/
        this.setIdTaxasSobrepeso(idTaxasSobrepeso = new int[quantidadeDeRegistros]);
        this.setTaxaTO(new TaxaSobrepesoTO());
    }

    public void taxasDeSobrepesoDoInstrutor(){
        //Aqui foi criada uma lista(listTaxas) com todas as taxas de sobre peso armazenadas no banco.
        List<TaxaSobrepesoTO> listTaxas = tiDAO.getTaxasSobrepesos();
        /* É possivel saber o tamanho do espaço reservado na memória para o vetor idTaxasSobrepeso,
        mas não é possível saber quantos espaços estão ocupados, então foi utilizado .length .*/       
        for (int i = 0; i < idTaxasSobrepeso.length ; i++) { 
            tiDAO.salvarTaxaDoInstrutor(idInstrutor, idTaxasSobrepeso[i]);            
            /*Foi criado um objeto(taxa) para percorrer a lista*/
            for(TaxaSobrepesoTO taxa : listTaxas){
                /*Se na lista houver um objeto com a identifcação igual a identificação da taxa selecionada pelo usuário,
                este objeto e removido da lista*/
                if(taxa.getIdTaxaSobrepeso()==idTaxasSobrepeso[i]){
                    listTaxas.remove(taxa);
                    break;
                }
            }
        }
        /*Após serem removidos da lista todas as taxas selecionadas, a lista resultante 
        é percorrida novamente e as taxas que o intrutor não faz serão removidas
        do seu cadastro*/
        for(TaxaSobrepesoTO taxa : listTaxas){
            tiDAO.ExcluirTaxaDoInstrutor(idInstrutor, taxa.getIdTaxaSobrepeso());
        }
        
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

    public TaxaSobrepesoTO getTaxaTO() {
        return taxaTO;
    }

    public void setTaxaTO(TaxaSobrepesoTO taxaTO) {
        this.taxaTO = taxaTO;
    }
 
}
