/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classBO.Filtros;
import classDAO.InstrutorDAO;
import classDAO.TaxaSobrepesoDAO;
import classDAO.TipoDeSaltoDAO;

import classTO.ClienteTO;
import classTO.DecolagemTO;
import classTO.InstrutorTO;

import classTO.TaxaSobrepesoTO;
import classTO.TipoDeSaltoTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

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
    
    private ClienteTO cliente;
    private TipoDeSaltoTO tipoDeSalto;
    private int idTipoDeSalto;    
    private TaxaSobrepesoTO taxaSobrepeso;
    private TipoDeSaltoDAO tipoDeSaltoDao;
    private TaxaSobrepesoDAO taxaSobresoDao;
    
    private InstrutorDAO intrutorDao;
    private DecolagemTO decolagem;
    private Filtros filtros;
     
    public SaltoBean() {
        this.setCliente(new ClienteTO());  
        this.setTipoDeSalto(new TipoDeSaltoTO());
        this.setIdTipoDeSalto(idTipoDeSalto = 0);
        this.setTaxaSobrepeso(new TaxaSobrepesoTO());
        this.setTaxaSobresoDao(new TaxaSobrepesoDAO());
        this.setTipoDeSaltoDao(new TipoDeSaltoDAO());
        
        
        this.setDecolagem(new DecolagemTO());    
        this.setFiltros(new Filtros());
        this.setIntrutorDao(new InstrutorDAO());
    }
    
    /**
    * Método de seleção do cliente, do tipo de salto e taxa de sobrepeso.
    */
    public void selectClientTipSaltoTaxaSobrepeso() {
        tipoDeSalto = filtros.getTipoDeSaltoTO(idTipoDeSalto);
        taxaSobrepeso = filtros.getTaxaDeSobrepeso(tipoDeSalto, cliente);        
    }
    
      
    /**
    * Método que retorna uma lista de instrutores. 
    * @return List - Lista de instrutores.  
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

    public int getIdTipoDeSalto() {
        return idTipoDeSalto;
    }

    public void setIdTipoDeSalto(int idTipoDeSalto) {
        this.idTipoDeSalto = idTipoDeSalto;
    }

    public TaxaSobrepesoDAO getTaxaSobresoDao() {
        return taxaSobresoDao;
    }

    public void setTaxaSobresoDao(TaxaSobrepesoDAO taxaSobresoDao) {
        this.taxaSobresoDao = taxaSobresoDao;
    }

    public TipoDeSaltoDAO getTipoDeSaltoDao() {
        return tipoDeSaltoDao;
    }

    public void setTipoDeSaltoDao(TipoDeSaltoDAO tipoDeSaltoDao) {
        this.tipoDeSaltoDao = tipoDeSaltoDao;
    }
    
    
}
