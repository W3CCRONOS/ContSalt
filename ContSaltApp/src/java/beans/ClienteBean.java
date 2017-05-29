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

/**
 *Classe de conexão das páginas .xhtml com o objeto cliente.
 * @author Almir
 * @version 1.0
 * @see ClienteTO
 * @see ClienteDAO
 */
@Named(value = "clienteBean")
@SessionScoped
public class ClienteBean implements Serializable {


    private ClienteTO cTO;
    private ClienteDAO cDAO ;
    
    /**
    * Método construtor. Neste métodos as variáveis
    * são inicializadas.
    */
    public ClienteBean() {
        this.setcTO(new ClienteTO());
        this.setcDAO(new ClienteDAO());        
    }
    
     /**
    * Método que cria um registro no banco de dados. Ele envia o objeto instanciado
    * para ser salvo no banco de dados.
    * Por fim, reinicializa as variáveis.
    * @see ClienteTO
    * @see ClienteDAO
    */
    public  void salvar(){
        cDAO.salvar(cTO);
        cTO = new ClienteTO();
    }

    public ClienteTO getcTO() {
        return cTO;
    }

    public void setcTO(ClienteTO cTO) {
        this.cTO = cTO;
    }

    public ClienteDAO getcDAO() {
        return cDAO;
    }

    public void setcDAO(ClienteDAO cDAO) {
        this.cDAO = cDAO;
    }
    
}
