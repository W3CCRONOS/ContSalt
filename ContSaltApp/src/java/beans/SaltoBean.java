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
    /*Algumas funcionalidade do PrimeFaces(p:selectOneMenu e p:selectOneRadio) enviam para
     o Bean valores inteiros*/
    private int idTipoDeSalto;
    private int idInstrutor;
    private String instruString;
    private TipoDeSaltoTO tipoDeSalto;      
    private TaxaSobrepesoTO taxaSobrepeso;
    private Filtros filtros;
    private InstrutorTO instrutor;
     
    public SaltoBean() {
        this.setCliente(new ClienteTO());  
        this.setTipoDeSalto(new TipoDeSaltoTO());
        this.setIdTipoDeSalto(idTipoDeSalto = 0);
        this.setIdInstrutor(idInstrutor = 0);
        this.setTaxaSobrepeso(new TaxaSobrepesoTO());    
        this.setFiltros(new Filtros());
        this.setInstrutor(new InstrutorTO());
        this.setInstruString(instruString = "false");
    }
    
    /**
    * Método de seleção do cliente e do tipo de salto.
    * O método recebe da interface o cliente e o id do tipo de salto.
    * O cliente é armazenado na variável cliente, o id do tipo de salto na 
    * varável idTipoDeSalto;
    */
    public void selectClientTipSalto(){     
    }
 
    /**
    * Método buca dos intrutores.
    * @return List - Lista de instrutores.  
    */
    public List<InstrutorTO> getInstrutores(){       
        
        List<InstrutorTO> listInst = new LinkedList<>();
        //listInst = filtros.instrutoresPresentes();
        //listInst = filtros.instruoresPorTipodeSalto(listInst, tipoDeSalto);
        // listInst = filtros.instrutoresPorPeso(cliente, taxaSobrepeso, listInst);
        
        //return listInst;
        return filtros.instrutores();
    }  
    
    public ClienteTO getCliente() {
        return cliente;
    }
    public void setCliente(ClienteTO cliente) {
        this.cliente = cliente;
    }
     
    public TipoDeSaltoTO getTipoDeSalto() {
        tipoDeSalto = filtros.getTipoDeSaltoTO(idTipoDeSalto);
        return tipoDeSalto;
    }
    public void setTipoDeSalto(TipoDeSaltoTO tipoDeSalto) {
        this.tipoDeSalto = tipoDeSalto;
    }

    public TaxaSobrepesoTO getTaxaSobrepeso() {
        taxaSobrepeso = filtros.getTaxaDeSobrepeso(tipoDeSalto, cliente);
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

    public int getIdTipoDeSalto() {
        return idTipoDeSalto;
    }

    public void setIdTipoDeSalto(int idTipoDeSalto) {
        this.idTipoDeSalto = idTipoDeSalto;
    }

    public int getIdInstrutor() {
        return idInstrutor;
    }

    public void setIdInstrutor(int idInstrutor) {
        this.idInstrutor = idInstrutor;
    }
    
    public InstrutorTO getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(InstrutorTO instrutor) {
        this.instrutor = instrutor;
    }

    public String getInstruString() {
        return instruString;
    }

    public void setInstruString(String instruString) {
        this.instruString = instruString;
    }
    
    
}
