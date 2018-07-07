/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classBO.Filtros;
import classDAO.ClienteDAO;
import classDAO.InstrutorDAO;

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
    private ClienteDAO clienteDAo;
    
    private TipoDeSaltoTO tipoDeSalto;
    private int idTiposDeSaltos;
    
    private InstrutorDAO intrutorDao;
    private DecolagemTO decolagem;
    private TaxaSobrepesoTO taxaSobrepeso;
    private Filtros filtros;
     
    public SaltoBean() {
        this.setCliente(new ClienteTO());
        this.setClienteDAo(new ClienteDAO());  
        this.setTipoDeSalto(new TipoDeSaltoTO());
        this.setIdTiposDeSaltos(idTiposDeSaltos = 0);
        this.setDecolagem(new DecolagemTO());
        this.setTaxaSobrepeso(new TaxaSobrepesoTO());
        this.setFiltros(new Filtros());
        this.setIntrutorDao(new InstrutorDAO());
    }
    
    /**
    * Método para busca de clientes. 
    * O metodo retorna uma lista de clientes, segundo a digitação realizada no formulário.
    * @param consulta String - Texto digitado pelo usuário do sistema.
    * @return List - Lista de clientes.  
    */
    public List<ClienteTO> sugerirCliente(String consulta) {
        List<ClienteTO> ClientesSugeridos = new ArrayList<>();
        List<ClienteTO> allClientes = clienteDAo.getClientes();        
        for (ClienteTO c : allClientes) {
            if (c.getNome().toLowerCase().startsWith(consulta.toLowerCase())) {
               ClientesSugeridos.add(c);
            }
        }
        
        return ClientesSugeridos;
    }
 
    /**
    * Método que seleciona um cliente e o tipo de salto. 
    */
    public void selecClientTipoDeSalto() {
        
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

    public void selInstrutor(InstrutorTO d){           
        
    }   
    
    public ClienteTO getCliente() {
        return cliente;
    }
    public void setCliente(ClienteTO cliente) {
        this.cliente = cliente;
    }

    public ClienteDAO getClienteDAo() {
        return clienteDAo;
    }

    public void setClienteDAo(ClienteDAO clienteDAo) {
        this.clienteDAo = clienteDAo;
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

    public int getIdTiposDeSaltos() {
        return idTiposDeSaltos;
    }

    public void setIdTiposDeSaltos(int idTiposDeSaltos) {
        this.idTiposDeSaltos = idTiposDeSaltos;
    }

    
}
