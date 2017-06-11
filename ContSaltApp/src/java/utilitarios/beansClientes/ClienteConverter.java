/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios.beansClientes;
import classTO.ClienteTO;

import javax.faces.application.FacesMessage;
import javax.faces.convert.ConverterException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author Almir
 */
@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter{
        public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                ClienteService service = (ClienteService) fc.getExternalContext().getApplicationMap().get("clienteService");
                return service.getClientesService().get(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid cliente."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((ClienteTO) object).getIdCliente());
        }
        else {
            return null;
        }
    } 
}
