/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classBO;

import classDAO.ClienteDAO;
import classDAO.DecolagemDAO;
import classDAO.SaltoDAO;
import classDAO.TipoDeSaltoDAO;
import classTO.ClienteTO;
import classTO.DecolagemTO;
import classTO.InstrutorTO;
import classTO.SaltoTO;
import classTO.TipoDeSaltoTO;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Classe Para Visualização dos Saltos.
 * A classe é responsaável pela apresentação dos saltos na página principal do sistema.
 * @author Almir
 * @version 1.0
 * @see ClienteDAO
 * @see SaltoDAO
 * @see TipoDeSaltoDAO
 */
public class Saltos {
    private ClienteDAO clienteDao = new ClienteDAO();
    private SaltoDAO saltoDao = new SaltoDAO();
    private TipoDeSaltoDAO TipSaltDao =  new TipoDeSaltoDAO();
    private DecolagemDAO decolagemDao= new DecolagemDAO();
    private Date dataUtil = new Date();

    /** Buscar Salto.
    * O método retornar um salto que está em uma decolagem e foi realizado  por
    * um instrutor.
    * @param instrutor - instrutor que realizou o salto.
    * @param decolagem - decolagem que contem o salto.
    * @return  - O salto.
    */    
    public SaltoTO getSaltoPorInstrutorDecolagem(InstrutorTO instrutor, DecolagemTO decolagem){        
        /*Retorno o salto de um instrutor em uma decolagem*/        
        return  saltoDao.getSaltosPorInstrutorDecolagem(instrutor, decolagem);  
    } 

    /** Buscar Cliente.
    * O método retornar um cliente que está em um salto.
    * @param salto
    * @return  - cliente.
    */    
    public ClienteTO getCliente(SaltoTO salto){
        ClienteTO cliente1 = new ClienteTO();
        /*Percorro a lista de clientes a procura do cliente no salto*/
        for(ClienteTO cliente2 : clienteDao.getClientes()){
            if(cliente2.getIdCliente()==salto.getIdCliente()){
                cliente1=cliente2;
                break;
            }
        }
        return cliente1;          
    }
    
    /** Buscar Tipo de Salto.
    * O método retornar o tipo de salto que está em um salto.
    * @param salto
    * @return  - tipo de salto.
    */    
    public TipoDeSaltoTO getTipoDeSalto(SaltoTO salto){
        TipoDeSaltoTO tipoSalto1 = new TipoDeSaltoTO();
         /*Percorro a lista de tipos de saltos a procura do tipo de salto*/
        for(TipoDeSaltoTO tipoSalto2 : TipSaltDao.getTiposDeSaltos()){
            if(tipoSalto2.getIdTipoDeSalto()==salto.getIdTipoDeSalto()){
                tipoSalto1=tipoSalto2;
                break;
            }
        }
        return tipoSalto1;          
    }
    
     public String statusDasDecolagens(DecolagemTO deco){      
        if(deco.getStatus().equals("false"))return "";         
        else return "Ok";
     }    
}
