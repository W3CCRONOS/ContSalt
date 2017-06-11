/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classDAO.TipoDeSaltoDAO;
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
   private TipoDeSaltoDAO cDAO;

    
    public TipoDeSaltoBean() {
        this.setCTO(new TipoDeSaltoTO());
        this.setcDAO(new TipoDeSaltoDAO());
    }
   
    public  void salvar(){
        cDAO.salvar(CTO);
        CTO = new TipoDeSaltoTO();
    }

    public List<TipoDeSaltoTO> getTiposDeSaltos(){
        return cDAO.getTiposDeSaltos();
    }
    
     public void excluir(TipoDeSaltoTO c){ 
        cDAO.excluir(c);
    }
     
    public  void preparaAlteracao(TipoDeSaltoTO c){
         this.setCTO(c);
    }
     
    public void alterar(){       
        cDAO.alterar(CTO); 
        CTO = new TipoDeSaltoTO();
    }

    public TipoDeSaltoTO getCTO() {
        return CTO;
    }

    public void setCTO(TipoDeSaltoTO CTO) {
        this.CTO = CTO;
    }

    public TipoDeSaltoDAO getcDAO() {
        return cDAO;
    }

    public void setcDAO(TipoDeSaltoDAO cDAO) {
        this.cDAO = cDAO;
    }
 
 }
