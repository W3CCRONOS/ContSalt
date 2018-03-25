/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classDAO.DecolagemDAO;
import classTO.DecolagemTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *Classe de conexão das páginas .xhtml com o objeto decolagem.
 * @author Almir
 * @version 1.0
 * @see DecolagemTO
 * @see DecolagemDAO
 */
@Named(value = "decolagemBean")
@SessionScoped
public class DecolagemBean implements Serializable {

    private DecolagemTO CTO;
    private DecolagemDAO cDAO;
    public DecolagemBean() {
                //Inicialização de variáveis.
        this.setCTO(new  DecolagemTO());
        this.setcDAO(new DecolagemDAO());
    }
 /**
    * Método que cria um registro de decolagem no banco de dados.
    * @see DecolagemTO
    * @see DecolagemDAO
    */
    public  void salvar(){    
        cDAO.salvar(CTO);
        CTO = new DecolagemTO();
    }
    /**
    * Método que retorna uma lista de registro das decolagens.
    * @return List - Lista de decolagens.
    * @see DecolagemTO
    * @see DecolagemDAO
    */
    public List<DecolagemTO> getInstrutores(){       
        return cDAO.getDecolagens();
    }
    
    /**
    * Método para excluir o registro de uma decolagem no banco de dados.
    * @param deco DecolagemTO - É um objeto do tipo InstrutorTO.
    * @see DecolagemTO
    * @see DecolagemDAO
    */
     public void excluir(DecolagemTO deco){ 
        cDAO.excluir(deco);
    }
    
    /**
    * Método para informar que um salto foi finalizado. 
    * @param deco DecolagemTO - É um objeto do tipo InstrutorTO.
    * @see DecolagemTO
    * @see DecolagemDAO
    */ 
    public void presenca(DecolagemTO deco){
        setCTO(deco);
        if(deco.getStatus().equals("false")){
            CTO.setStatus("true");
            cDAO.aterarStatus(CTO);
        }
        else{
            CTO.setStatus("false");
            cDAO.aterarStatus(CTO);
        }
    }
    public DecolagemTO getCTO() {
        return CTO;
    }

    public void setCTO(DecolagemTO CTO) {
        this.CTO = CTO;
    }

    public DecolagemDAO getcDAO() {
        return cDAO;
    }

    public void setcDAO(DecolagemDAO cDAO) {
        this.cDAO = cDAO;
    }
    
}
