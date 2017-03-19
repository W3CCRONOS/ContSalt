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
    
    private TaxaSobrepesoTO CTO;
    private TaxaSobrepesoBO cBO;
    private String valor;

    public TaxaSobrepesoBean() {
        this.setCTO(new TaxaSobrepesoTO());
        this.setcBO(new TaxaSobrepesoBO());
        this.setValor(new String());
    }
    
      public  void salvar(){
        removerMascara(valor);        
        cBO.salvar(CTO); 
        CTO = new TaxaSobrepesoTO();
        valor = new String();
    }
    
    public void removerMascara(String str){
        double y=0;
        y=Double.parseDouble(str.replaceAll("\\D", ""));
        y= y * 0.01;
        CTO.setValor(y);        
    }
    
    public List<TaxaSobrepesoTO> getTaxasSobrepesos(){
        return cBO.getTaxasSobrepesos();
    }
    
     public void excluir(TaxaSobrepesoTO c){ 
        cBO.excluir(c);
    }
     
    public  void preparaAlteracao(TaxaSobrepesoTO c){
         this.setCTO(c);
    }
     
    public void alterar(){
        removerMascara(valor); 
        cBO.alterar(CTO);
        CTO = new TaxaSobrepesoTO();
        valor = new String();
    }

    public TaxaSobrepesoTO getCTO() {
        return CTO;
    }

    public void setCTO(TaxaSobrepesoTO CTO) {
        this.CTO = CTO;
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
