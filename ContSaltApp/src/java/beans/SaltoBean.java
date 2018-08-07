/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classBO.Filtros;
import classDAO.InstrutorDAO;
import classDAO.SaltoDAO;
import classDAO.TaxaSobrepesoDAO;
import classDAO.TipoDeSaltoDAO;

import classTO.ClienteTO;
import classTO.DecolagemTO;
import classTO.InstrutorTO;
import classTO.SaltoTO;

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
    /*Algumas funcionalidade do PrimeFaces(p:selectOneMenu) envia para
     o Bean valores inteiros*/
    private int idTipoDeSalto;
    private int idDecolagem;
    private TipoDeSaltoTO tipoDeSalto; 
    private DecolagemTO decolagem;
    private TaxaSobrepesoTO taxaSobrepeso;
    private Filtros filtros;
    private InstrutorTO instrutor;
    private SaltoTO salto;
    private SaltoDAO saltoDao = new SaltoDAO();
     
    public SaltoBean() {
        this.setCliente(new ClienteTO());  
        this.setTipoDeSalto(new TipoDeSaltoTO());
        this.setDecolagem(new DecolagemTO());
        this.setIdTipoDeSalto(idTipoDeSalto = 0);
        this.setIdDecolagem(idDecolagem = 0);
        this.setTaxaSobrepeso(new TaxaSobrepesoTO());    
        this.setFiltros(new Filtros());
        this.setInstrutor(new InstrutorTO());
        this.setSalto(new SaltoTO());
    }
    
    /**
    * Método de seleção do cliente, do tipo de salto e da decolagem.
    * O método recebe da interface o cliente, o id do tipo de salto e decolagem.
    */
    public void selectClientTipSaltoDecolage(){ 

    }
    
    /**
    * Método de seleção do instrutor.
    * O método recebe da interface o cliente, o id do tipo de salto e decolagem.
    */
    public void selectInstrutor(InstrutorTO isntrutor){
        this.instrutor=isntrutor;
    }
 
    /**
    * Método de busca dos intrutores.
    * O método retorna uma lista ordenada com instrutores aptos para realização do salto.
    * @return List - Lista de instrutores.  
    */
    public List<InstrutorTO> getInstrutores(){               
        List<InstrutorTO> listInst = new LinkedList<>();
        
            // Com a variável, idTipoDeSalto, identifico o tipo de salto.
        tipoDeSalto = filtros.getTipoDeSaltoTO(idTipoDeSalto);
            /*Filtro para taxa de sobrepeso*/
        taxaSobrepeso = filtros.getTaxaDeSobrepeso(tipoDeSalto, cliente);
            // Com a variável, idDecolagem, identifico a decolagem.
        decolagem = filtros.getDecolagemTO(idDecolagem);
       
        
        /*A variável listInst recebe os instrutores presentes no dia do salto, ordenados 
        pela data de admissão*/
        listInst = filtros.instrutoresPresentes();

        /*Filtro os instrutores que estão aptos a realizar o tipo de salto*/
        listInst = filtros.instruoresPorTipodeSalto(listInst, tipoDeSalto); 

        /*Filtro os instrutores pelo peso do cliente.*/
        listInst = filtros.instrutoresPorPeso(cliente, taxaSobrepeso, listInst);

        /*Filtro os instrutores que já estão em algum salto dentro da decolagem.*/
        listInst = filtros.instroresPorDecolagem(listInst, decolagem);
        //Retorno lista de istrutores filtrados.
        return listInst;        
    }
            
    /**
    * Método que retorna uma lista dos instrutore presentes.
    * A presença dos intrutores todo dia precisa ser verificada para que
    * seus nomes possam ser selecionados para efetuar os saltos.
    * @return  - Lista de instrutores.  
    */
    public List<InstrutorTO> getInstrutoresPresentes(){       
        List<InstrutorTO> listInst = new LinkedList<>();
        listInst = filtros.instrutoresPresentes();
        return listInst;
    }
    
    public void cancelar(){
        this.setCliente(new ClienteTO());  
        this.setTipoDeSalto(new TipoDeSaltoTO());
        this.setDecolagem(new DecolagemTO());
        this.setIdTipoDeSalto(idTipoDeSalto = 0);
        this.setIdDecolagem(idDecolagem = 0);
        this.setTaxaSobrepeso(new TaxaSobrepesoTO());    
        this.setFiltros(new Filtros());
        this.setInstrutor(new InstrutorTO());
        this.setSalto(new SaltoTO());
    }
    
    public void salvar(){
        salto.setIdCliente(cliente.getIdCliente());           
        salto.setIdInstrutor(instrutor.getIdInstrutor());
        salto.setIdDecolagem(decolagem.getIddecolagem());
        salto.setIdTaxaDeSobrepeso(taxaSobrepeso.getIdTaxaSobrepeso());
        salto.setIdTipoDeSalto(tipoDeSalto.getIdTipoDeSalto());
        saltoDao.salvar(salto);
        cancelar();        
    }
    
    /**
    * Método de buca das decolagens.
    * O método retorna as decolagem disponíveis para o salto
    * @return List - Lista de decolagem.  
    */
    public List<DecolagemTO> getDecolagems(){       
        return filtros.decolagens();
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
    public int getIdTipoDeSalto() {
        return idTipoDeSalto;
    }
    public void setIdTipoDeSalto(int idTipoDeSalto) {
        this.idTipoDeSalto = idTipoDeSalto;
    }    
    public InstrutorTO getInstrutor() {
        return instrutor;
    }
    public void setInstrutor(InstrutorTO instrutor) {
        this.instrutor = instrutor;
    }
    public int getIdDecolagem() {
        return idDecolagem;
    }
    public void setIdDecolagem(int idDecolagem) {
        this.idDecolagem = idDecolagem;
    }
    public DecolagemTO getDecolagem() {
        return decolagem;
    }
    public void setDecolagem(DecolagemTO decolagem) { 
        this.decolagem = decolagem;
    }
    public SaltoTO getSalto() {
        return salto;
    }
    public void setSalto(SaltoTO salto) {
        this.salto = salto;
    }     
}
