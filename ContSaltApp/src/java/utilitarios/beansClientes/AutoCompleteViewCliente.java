/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios.beansClientes;

import classTO.ClienteTO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Almir
 */
@ManagedBean
public class AutoCompleteViewCliente {
    private ClienteTO clienteTO;
    private List<ClienteTO> selectedClientes;
    @ManagedProperty("#{clienteService}")
    private ClienteService service;  

    public AutoCompleteViewCliente() {
        this.clienteTO = new ClienteTO();
        this.selectedClientes = new LinkedList<>();
        this.service = new ClienteService();
    }
    
   
    public List<ClienteTO> completeCliente(String query) {        
        List<ClienteTO> allThemes = service.getClientesService();                
        List<ClienteTO> filteredThemes = new ArrayList<>();
         
        for (int i = 0; i < allThemes.size(); i++) {
            ClienteTO skin = allThemes.get(i);
            if(skin.getNome().toLowerCase().startsWith(query)) {
                filteredThemes.add(skin);
            }
        }
         
        return filteredThemes;
    }
     
    public void onItemSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
    }

    public ClienteTO getClienteTO() {
        return clienteTO;
    }

    public void setClienteTO(ClienteTO clienteTO) {
        this.clienteTO = clienteTO;
    }

    public List<ClienteTO> getSelectedClientes() {
        return selectedClientes;
    }

    public void setSelectedClientes(List<ClienteTO> selectedClientes) {
        this.selectedClientes = selectedClientes;
    }

    public ClienteService getService() {
        return service;
    }

    public void setService(ClienteService service) {
        this.service = service;
    }
    
     
}
