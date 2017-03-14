/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classBO.TipoDeSaltoBO;
import classTO.TipoDeSaltoTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;



/**
 *
 * @author Almir
 */
@Named(value = "tipoDeSaltoBean")
@SessionScoped
public class TipoDeSaltoBean implements Serializable {

    TipoDeSaltoTO cTO;
    TipoDeSaltoBO cBO;
    String valor;
    
    public TipoDeSaltoBean() {
        this.setcTO(new TipoDeSaltoTO());
        this.setcBO(new TipoDeSaltoBO());
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
    
    public List<TipoDeSaltoTO> getTipoDeSalto(){
        return cBO.getTipoDeSalto();
    }
    
     public void excluir(TipoDeSaltoTO c){ 
        cBO.excluir(c);
    }
     
    public  void preparaAlteracao(TipoDeSaltoTO c){
         this.setcTO(c);
    }
     
    public void alterar(){ 
        cBO.alterar(cTO);
    }

    public TipoDeSaltoTO getcTO() {
        return cTO;
    }

    public void setcTO(TipoDeSaltoTO cTO) {
        this.cTO = cTO;
    }



    public TipoDeSaltoBO getcBO() {
        return cBO;
    }

    public void setcBO(TipoDeSaltoBO cBO) {
        this.cBO = cBO;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}
