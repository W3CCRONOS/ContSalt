/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classBO.Filtros;
import classDAO.SaltoDAO;
import classTO.ClienteTO;
import classTO.SaltoTO;
import classTO.TaxaSobrepesoTO;
import classTO.TipoDeSaltoTO;
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
    private ClienteTO cliente;
    private TipoDeSaltoTO tipoDeSalto;
    private TaxaSobrepesoTO taxaSobrepeso;
    private Filtros filtros;
     
    public SaltoBean() {
        this.setSalt(new SaltoTO());
        this.setCliente(new ClienteTO());
        this.setSaltDAO(new SaltoDAO());
        this.setTipoDeSalto(new TipoDeSaltoTO());
        this.setTaxaSobrepeso(new TaxaSobrepesoTO());
        this.setFiltros(new Filtros());
    }
    public void selCliente(ClienteTO c){
        /* Date d = new Date();      
        java.sql.Date dataSql = new java.sql.Date(d.getTime());
        saltDAO.salvar(salt);
        */
        this.setCliente(c);
        System.out.println(c.getIdCliente());
        filtros.filtraPresenca();
    }
    public void selTipoDesalto(TipoDeSaltoTO d){           
        this.setTipoDeSalto(d);
        System.out.println(d.getIdTipoDeSalto());
    }
    public void selTaxaDeSobrepeso(TaxaSobrepesoTO ts){        
        this.setTaxaSobrepeso(ts);
        System.out.println(ts.getIdTaxaSobrepeso());    
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
    public ClienteTO getCliente() {
        return cliente;
    }
    public void setCliente(ClienteTO cliente) {
        this.cliente = cliente;
    }
    public TipoDeSaltoTO getTipoDeSalto() {
        return tipoDeSalto;
    }
    public void setTipoDeSalto(TipoDeSaltoTO tipoDeSalto) {
        this.tipoDeSalto = tipoDeSalto;
    } 
    public TaxaSobrepesoTO getTaxaSobrepeso() {
        return taxaSobrepeso;
    }
    public void setTaxaSobrepeso(TaxaSobrepesoTO taxaSobrepeso) {
        this.taxaSobrepeso = taxaSobrepeso;
    } 

    public Filtros getFiltros() {
        return filtros;
    }

    public void setFiltros(Filtros filtros) {
        this.filtros = filtros;
    }
    
    
}
