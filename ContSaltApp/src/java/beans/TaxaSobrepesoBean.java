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
    private String valor;
    private boolean value1;

    public TaxaSobrepesoBean() {
        this.setCTO(new TaxaSobrepesoTO());
        this.setcDAO(new TaxaSobrepesoDAO());
        this.setValor(new String());
       // this.setValue1(new Boolean("false"));
    }
    
    public  void salvar(){
        CTO.setValor(removerMascara(valor));        
        cDAO.salvar(CTO); 
        CTO = new TaxaSobrepesoTO();
        valor = new String();
    }
    
    public double removerMascara(String str){
        double y=0;
        y=Double.parseDouble(str.replaceAll("\\D", ""));
        return y * 0.01;               
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
        CTO.setValor(removerMascara(valor));        
        cDAO.alterar(CTO); 
        CTO = new TaxaSobrepesoTO();
        valor = new String();           
    }

    public TaxaSobrepesoTO getCTO() {
        return CTO;
    }

    public void setCTO(TaxaSobrepesoTO CTO) {
        this.CTO = CTO;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public TaxaSobrepesoDAO getcDAO() {
        return cDAO;
    }

    public void setcDAO(TaxaSobrepesoDAO cDAO) {
        this.cDAO = cDAO;
    } 

    public boolean isValue1() {
        return value1;
    }

    public void setValue1(boolean value1) {
        this.value1 = value1;
    }
    
}
