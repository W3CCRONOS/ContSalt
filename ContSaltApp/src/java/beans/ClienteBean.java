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
import java.util.ArrayList;
import java.util.List;
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
       
    /**
    * Método construtor. Neste métodos as variáveis
    * são inicializadas.
    */
    public ClienteBean() {
        this.setClienteTO(new ClienteTO());
        this.setClienteDAO(new ClienteDAO());
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

    public List<ClienteTO> getClientes(){
        return clienteDAO.getClientes();
    }
    
     public void excluir(ClienteTO c){ 
        clienteDAO.excluir(c);
    }
     
    public  void preparaAlteracao(ClienteTO c){
         this.setClienteTO(c);
    }
       
    public void alterar(){       
        clienteDAO.alterar(clienteTO); 
        clienteTO = new ClienteTO();
    }
    
    /**
    * Método para busca de clientes. 
    * O metodo retorna uma lista de clientes, segundo a digitação realizada no formulário.
    * @param consulta String - Texto digitado pelo usuário do sistema.
    * @return List - Lista de clientes.  
    */
    public List<ClienteTO> sugerirCliente(String consulta) {
        List<ClienteTO> ClientesSugeridos = new ArrayList<>();
        List<ClienteTO> allClientes = clienteDAO.getClientes();        
        for (ClienteTO c : allClientes) {
            if (c.getNome().toLowerCase().startsWith(consulta.toLowerCase())) {
               ClientesSugeridos.add(c);
            }
        }
        
        return ClientesSugeridos;
    }

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
}
