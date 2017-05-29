/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classDAO.SaltoDAO;
import classTO.SaltoTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *Classe de conexão com a página cadastrar_salto.xhtml.
 * @author Almir
 * @version 1.0 * 
 */
@Named(value = "saltoBean")
@SessionScoped
public class SaltoBean implements Serializable {
    
    private SaltoTO salt;
    private SaltoDAO saltDAO;
     
    public SaltoBean() {
        this.setSalt(new SaltoTO());
        this.setSaltDAO(new SaltoDAO());
    }
    
    public  void salvar(){
        Date d = new Date();      
        java.sql.Date dataSql = new java.sql.Date(d.getTime());
        saltDAO.salvar(salt);
    }
    
    public SaltoTO getSalt() {
        return salt;
    }

    public void setSalt(SaltoTO salt) {
        this.salt = salt;
    }

    public SaltoDAO getSaltDAO() {
        return saltDAO;
    }

    public void setSaltDAO(SaltoDAO saltDAO) {
        this.saltDAO = saltDAO;
    }
    
}
