/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios.beansClientes;

import classDAO.ClienteDAO;
import classTO.ClienteTO;
import java.util.ArrayList;
import java.util.List;
//import javax.inject.Named;
//import javax.enterprise.context.ApplicationScoped;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Almir
 */
//@Named(value = "clienteService")
@ManagedBean(name="clienteService", eager = true)

@ApplicationScoped
public class ClienteService {

   private List<ClienteTO> listClientesServivce;
   private ClienteDAO clienteDAO;
 @PostConstruct
    public void init() {
        listClientesServivce = new ArrayList<>();
        clienteDAO = new ClienteDAO();
       listClientesServivce = clienteDAO.getClientes();
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
     public List<ClienteTO> getClientesService() {
        return listClientesServivce;
    }   
}
