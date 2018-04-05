/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classBO.Filtros;
import classDAO.InstrutorDAO;
import classDAO.SaltoDAO;
import classTO.ClienteTO;
import classTO.DecolagemTO;
import classTO.InstrutorTO;
import classTO.SaltoTO;
import classTO.TaxaSobrepesoTO;
import classTO.TipoDeSaltoTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

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
    private InstrutorDAO intrutorDao;
    private ClienteTO cliente;
    private TipoDeSaltoTO tipoDeSalto;
    private DecolagemTO decolagem;
    private TaxaSobrepesoTO taxaSobrepeso;
    private Filtros filtros;
     
    public SaltoBean() {
        this.setSalt(new SaltoTO());        
        this.setSaltDAO(new SaltoDAO());
        this.setCliente(new ClienteTO());
        this.setTipoDeSalto(new TipoDeSaltoTO());
        this.setDecolagem(new DecolagemTO());
        this.setTaxaSobrepeso(new TaxaSobrepesoTO());
        this.setFiltros(new Filtros());
        this.setIntrutorDao(new InstrutorDAO());
    }
    public void selCliente(ClienteTO c){        
        this.setCliente(c);
    }
    public void selTaxaDeSobrepeso(TaxaSobrepesoTO ts){        
        this.setTaxaSobrepeso(ts);
        
    }
    public void selTipoDesalto(TipoDeSaltoTO tipSalt){           
        this.setTipoDeSalto(tipSalt);        
    }
    
    public void selDecolagem(DecolagemTO d){             
        this.setDecolagem(d);
    }
        
    /**
    * Método que retorna uma lista de instrutores. 
    * @return List - Lista de instrutores, objetos InstrutorTO.  
    */
    public List<InstrutorTO> getInstrutores(){       
        
      //  List<InstrutorTO> listInstPorSalt = new LinkedList<>();
       // listInstPorSalt=filtros.filtraInstruoresPorTipodeSalto(tipoDeSalto.getIdTipoDeSalto());
        
        List<InstrutorTO> listInstPorPeso = new LinkedList<>();
        listInstPorPeso= intrutorDao.getInstrutores();      
        
        if(cliente!=null){ 
            for(int i = 0; i < listInstPorPeso.size();i++){             
                if(listInstPorPeso.get(i).getPeso()<cliente.getPeso()){
                    listInstPorPeso.remove(i);                    
                }
            }
                 
        }
         return listInstPorPeso;
       // return listInstPorSalt;
    }

    public void selInstrutor(InstrutorTO d){           
        
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

    public DecolagemTO getDecolagem() {
        return decolagem;
    }

    public void setDecolagem(DecolagemTO decolagem) {
        this.decolagem = decolagem;
    }

    public InstrutorDAO getIntrutorDao() {
        return intrutorDao;
    }

    public void setIntrutorDao(InstrutorDAO intrutorDao) {
        this.intrutorDao = intrutorDao;
    }
    
    
}
