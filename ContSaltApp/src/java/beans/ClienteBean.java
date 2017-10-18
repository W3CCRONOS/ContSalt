/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classDAO.ClienteDAO;
import classTO.ClienteTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
//import javax.annotation.PostConstruct;

/**
 *Classe de conexão das páginas .xhtml com o objeto cliente.
 * @author Almir
 * @version 1.0
 * @see ClienteTO
 * @see ClienteDAO
 */
//sadfs
@Named(value = "clienteBean")
@SessionScoped
public class ClienteBean implements Serializable {


    private ClienteTO clienteTO;
    private ClienteDAO clienteDAO ;
    private String nomeCliente;
    private List<ClienteTO> listClientes;
    
    /**
    * Método construtor. Neste métodos as variáveis
    * são inicializadas.
    */
    public ClienteBean() {
        this.setClienteTO(new ClienteTO());
        this.setClienteDAO(new ClienteDAO());
    }
      
    public List<ClienteTO> getClientes() {
        return listClientes;
    } 
     public List<ClienteTO> completeCompany(){
        return clienteDAO.getClientes();
    }
    
    
     /**
    * Método que cria um registro no banco de dados. Ele envia o objeto instanciado
    * para ser salvo no banco de dados.
    * Por fim, reinicializa as variáveis.
    * @see ClienteTO
    * @see ClienteDAO
    */
    public  void salvar(){
        clienteDAO.salvar(clienteTO);
        clienteTO = new ClienteTO();
    }
        
  /*  public List<ClienteTO> getClientes(){
        return clienteDAO.getClientes();
    } 
   */  

    public ClienteTO getClienteTO() {
        return clienteTO;
    }

    public void setClienteTO(ClienteTO clienteTO) {
        this.clienteTO = clienteTO;
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    
}
