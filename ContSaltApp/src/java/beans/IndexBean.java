/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classBO.Filtros;
import classBO.Saltos;
import classTO.ClienteTO;
import classTO.DecolagemTO;
import classTO.InstrutorTO;
import classTO.SaltoTO;
import classTO.TipoDeSaltoTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe de conexão para páginas index.xhtml.
 * A classe informará o nome do cliente em um salto e o nome do tipo de salto.
 * @author Almir
 * @version 1.0
 */
@Named(value = "indexBean")
@SessionScoped
public class IndexBean implements Serializable {

    private Saltos saltos;
    private Filtros filtros;
    
    public IndexBean() {
        this.saltos = new Saltos();
    }
    
    /**
    * Buscar Nome do Cliente.
    * O método retorna o nome do cliente que está em um salto.
    * @param instrutor
    * @param decolagem
    * @return - nome do cliente.  
    */
    public String getNomeCliente(InstrutorTO instrutor, DecolagemTO decolagem){ 
        ClienteTO cliente =  new ClienteTO();
        cliente= saltos.getCliente(saltos.getSaltoPorInstrutorDecolagem(instrutor, decolagem));
        return cliente.getNome();
    }
    
    /**
    * Buscar nome do tipo de salto.
    * O método retorna o nome do tipo de salto de um salto.
    * @param instrutor
    * @param decolagem
    * @return - nome do tipo de salto.  
    */
    public String getNomeTipoDeSalto(InstrutorTO instrutor, DecolagemTO decolagem){ 
        TipoDeSaltoTO tipSalt =  new TipoDeSaltoTO();
        tipSalt = saltos.getTipoDeSalto(saltos.getSaltoPorInstrutorDecolagem(instrutor, decolagem));    
        return tipSalt.getNome();
    }
    
    /**
    * Buscar Salto.
    * O método retorna a identificação de um salto.
    * @param instrutor
    * @param decolagem
    * @return - nome do tipo de salto.  
    */
    public int getIdSalto(InstrutorTO instrutor, DecolagemTO decolagem){ 
        SaltoTO salto = new SaltoTO();
        salto = saltos.getSaltoPorInstrutorDecolagem(instrutor, decolagem);    
        return  salto.getIdSalto();
    }
    
    
    /**
    * Método que retorna uma lista dos instrutore presentes.
    * O método informara quais instrutores estão presentes pra realizar saltos.
    * @return  - lLista de instrutores.  
    */

    public List<InstrutorTO> getInstrutoresPresentes(){       
        List<InstrutorTO> listInst = new LinkedList<>();
        listInst = filtros.instrutoresPresentes();
        return listInst;
    }
    
    public String statusDasDecolagens(DecolagemTO deco){
        return saltos.statusDasDecolagens(deco);

    }
    
}
