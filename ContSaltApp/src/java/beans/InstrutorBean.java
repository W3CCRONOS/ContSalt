/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classBO.InstrutorBO;
import classDAO.InstrutorDAO;
import classTO.InstrutorTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Almir
 */
@Named(value = "instrutorBean")
@SessionScoped
public class InstrutorBean implements Serializable {

    private InstrutorTO CTO;
    private InstrutorBO cBO;
    private InstrutorDAO cDAO ;
    private Date data;
    private String s;

    public InstrutorBean() {
        this.setCTO(new InstrutorTO());
        this.setcBO(new InstrutorBO());
        this.setcDAO(new InstrutorDAO());
        this.setData(new Date());
        this.setS(new String());

    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public  void salvar(){
        this.setCTO(cBO.passarDataUtilParaDataSql(CTO, data));
        cDAO.salvar(CTO);
        CTO = new InstrutorTO();
        data = new Date();
    }
      
    public List<InstrutorTO> getInstrutores(){       
        return cDAO.getInstrutores();
    }
    
     public void excluir(InstrutorTO c){ 
        cDAO.excluir(c);
    }
     
    public  void preparaAlteracao(InstrutorTO c){
        /*Passar a data que está em um formato SQL para o formato Util*/
        this.setData(cBO.passarDataSqlParaDataUtil(c));
        this.setCTO(c);
    }
     
    public void alterar(){ 
         this.setCTO(cBO.passarDataUtilParaDataSql(CTO, data));
         cDAO.alterar(CTO);
         CTO = new InstrutorTO();
         data = new Date();
    }
    
    public void presenca(InstrutorTO instrutor){
        this.setCTO(instrutor);
        String presenca = instrutor.getPresenca();
        if(presenca.equals("false")){
            CTO.setPresenca("true");
            cDAO.presenca(CTO);
        }
        else{
            CTO.setPresenca("false");
            cDAO.presenca(CTO);
        }
    }
    
    public InstrutorTO getCTO() {
        return CTO;
    }

    public void setCTO(InstrutorTO CTO) {
        this.CTO = CTO;
    }

    public InstrutorBO getcBO() {
        return cBO;
    }

    public void setcBO(InstrutorBO cBO) {
        this.cBO = cBO;
    }

    public InstrutorDAO getcDAO() {
        return cDAO;
    }

    public void setcDAO(InstrutorDAO cDAO) {
        this.cDAO = cDAO;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
  
}
