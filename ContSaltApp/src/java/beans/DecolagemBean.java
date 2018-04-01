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
import java.util.Date;
import java.util.List;
import utilitarios.ConversorDeDatas;

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
    private Date dataUtil;
    private ConversorDeDatas alteraData;
    
    public DecolagemBean() {
                //Inicialização de variáveis.
        this.setCTO(new  DecolagemTO());
        this.setcDAO(new DecolagemDAO());
        this.setDataUtil(new Date());
        this.setAlteraData(new ConversorDeDatas());
    }
 /**
    * Método que cria um registro de decolagem no banco de dados.
    * @see DecolagemTO
    * @see DecolagemDAO
    */
    public  void salvar(){ 
        CTO.setData(alteraData.passarDataUtilParaDataSql(dataUtil));
        cDAO.salvar(CTO);
        CTO = new DecolagemTO(); 
        dataUtil = new Date();         
    }
    /**
    * Método que retorna uma lista de registro das decolagens.
    * @return List - Lista de decolagens.
    * @see DecolagemTO
    * @see DecolagemDAO
    */
    public List<DecolagemTO> getDecolagens(){      
        java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
        return cDAO.getDecolagens(dataSql);
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
    * Método para informar que uma decolagem foi finalizado. 
    * @param deco DecolagemTO - É um objeto do tipo InstrutorTO.
    * @see DecolagemTO
    * @see DecolagemDAO
    */ 
    public void status(DecolagemTO deco){
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

    public Date getDataUtil() {
        return dataUtil;
    }

    public void setDataUtil(Date dataUtil) {
        this.dataUtil = dataUtil;
    }

    public ConversorDeDatas getAlteraData() {
        return alteraData;
    }

    public void setAlteraData(ConversorDeDatas alteraData) {
        this.alteraData = alteraData;
    }
    
}
