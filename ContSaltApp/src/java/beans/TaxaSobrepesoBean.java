/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classBO.TaxaSobrepesoBO;
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
    
    TaxaSobrepesoTO cTO;
    TaxaSobrepesoBO cBO;
    String valor;

    public TaxaSobrepesoBean() {
        this.setcTO(new TaxaSobrepesoTO());
        this.setcBO(new TaxaSobrepesoBO());
        this.setValor(new String());
    }
    
      public  void salvar(){
        removerMascara(valor);        
        cBO.salvar(cTO);                  
    }
    
    public void removerMascara(String str){
        double y=0;
        y=Double.parseDouble(str.replaceAll("\\D", ""));
        y= y * 0.01;
        cTO.setValor(y);        
    }
    
    public List<TaxaSobrepesoTO> getTaxasSobrepesos(){
        return cBO.getTaxasSobrepesos();
    }
    
     public void excluir(TaxaSobrepesoTO c){ 
        cBO.excluir(c);
    }
     
    public  void preparaAlteracao(TaxaSobrepesoTO c){
         this.setcTO(c);
    }
     
    public void alterar(){ 
        cBO.alterar(cTO);
    }
    
    public TaxaSobrepesoTO getcTO() {
        return cTO;
    }

    public void setcTO(TaxaSobrepesoTO cTO) {
        this.cTO = cTO;
    }

    public TaxaSobrepesoBO getcBO() {
        return cBO;
    }

    public void setcBO(TaxaSobrepesoBO cBO) {
        this.cBO = cBO;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}
