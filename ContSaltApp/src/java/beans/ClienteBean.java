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
    private SaltoBean clienteSalto;
    
    /**
    * Método construtor. Neste métodos as variáveis
    * são inicializadas.
    */
    public ClienteBean() {
        this.setClienteTO(new ClienteTO());
        this.setClienteDAO(new ClienteDAO());
        this.setClienteSalto(new SaltoBean());
    } 
     /**
    * Método que cria um registro no banco de dados. Ele envia o objeto instanciado
    * para ser salvo no banco de dados.
    * Por fim, reinicializa as variáveis.
    * @see ClienteTO
    * @see ClienteDAO
    */
    public  void salvar(){
        System.out.println(clienteTO.getNome());
        System.out.println(clienteTO.getPeso());
        System.out.println(clienteTO.getCpf());
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
    
     public List<ClienteTO> completeText(String query) {
               
        return clienteDAO.getNomesClientes(query);
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

    public SaltoBean getClienteSalto() {
        return clienteSalto;
    }

    public void setClienteSalto(SaltoBean clienteSalto) {
        this.clienteSalto = clienteSalto;
    }
    
}
