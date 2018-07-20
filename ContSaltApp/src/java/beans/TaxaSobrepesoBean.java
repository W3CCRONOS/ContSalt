/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classDAO.TaxaSobrepesoDAO;
import classTO.TaxaSobrepesoTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Skydive
 */
@Named(value = "taxaSobrepesoBean")
@SessionScoped
public class TaxaSobrepesoBean implements Serializable {
    
    private TaxaSobrepesoTO CTO;
    private TaxaSobrepesoDAO cDAO;

    public TaxaSobrepesoBean() {
        this.setCTO(new TaxaSobrepesoTO());
        this.setcDAO(new TaxaSobrepesoDAO());
    }
    
    public  void salvar(){
       
        cDAO.salvar(CTO); 
        CTO = new TaxaSobrepesoTO();
    }
  
    public List<TaxaSobrepesoTO> getTaxasSobrepesos(){
        return cDAO.getTaxasSobrepesos();
    }
    
     public void excluir(TaxaSobrepesoTO c){ 
        cDAO.excluir(c);
    }
     
    public  void preparaAlteracao(TaxaSobrepesoTO c){
         this.setCTO(c);
    }
     
    public void alterar(){    
        cDAO.alterar(CTO); 
        CTO = new TaxaSobrepesoTO();           
    }

    public TaxaSobrepesoTO getCTO() {
        return CTO;
    }

    public void setCTO(TaxaSobrepesoTO CTO) {
        this.CTO = CTO;
    }

    public TaxaSobrepesoDAO getcDAO() {
        return cDAO;
    }

    public void setcDAO(TaxaSobrepesoDAO cDAO) {
        this.cDAO = cDAO;
    } 
}
