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

   private TipoDeSaltoTO CTO;
   private TipoDeSaltoBO cBO;
   private String valor;
    
    public TipoDeSaltoBean() {
        this.setCTO(new TipoDeSaltoTO());
        this.setcBO(new TipoDeSaltoBO());
        this.setValor(new String());
    }
    
    public  void salvar(){
        removerMascara(valor);        
        cBO.salvar(CTO); 
        CTO = new TipoDeSaltoTO();
        valor = new String();                
    }
    
    public void removerMascara(String str){
        double y=0;
        //O método replaceAll("\\D", "") remove todos os carcteres != números
        y=Double.parseDouble(str.replaceAll("\\D", ""));
        y= y * 0.01;
        CTO.setValor(y);        
    }
    
 
    
    public List<TipoDeSaltoTO> getTiposDeSaltos(){
        return cBO.getTipoDeSalto();
    }
    
     public void excluir(TipoDeSaltoTO c){ 
        cBO.excluir(c);
    }
     
    public  void preparaAlteracao(TipoDeSaltoTO c){
         this.setCTO(c);
    }
     
    public void alterar(){ 
        cBO.alterar(CTO);
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

    public TipoDeSaltoTO getCTO() {
        return CTO;
    }

    public void setCTO(TipoDeSaltoTO CTO) {
        this.CTO = CTO;
    }
    
 }
