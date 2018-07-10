/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import classDAO.ClienteDAO;
import classTO.ClienteTO;
import static java.lang.Integer.parseInt;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("clienteconverter")

/**
 *
 * @author almir
 */
public class ClienteConverter implements Converter{
    private ClienteDAO cDao = new ClienteDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        int codigo = 0;   	
    	try {
            codigo = parseInt(value);                
    	} catch (NumberFormatException e) { }        
    	if (codigo != 0) {
            List<ClienteTO> allClientes = cDao.getClientes();   
            for (ClienteTO c : allClientes) {
                if (codigo == c.getIdCliente() ){
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
			ClienteTO c = (ClienteTO) value;
			return String.valueOf(c.getIdCliente());
		}
		return null;
    }

    public ClienteDAO getcDao() {
        return cDao;
    }

    public void setcDao(ClienteDAO cDao) {
        this.cDao = cDao;
    }
    
}
