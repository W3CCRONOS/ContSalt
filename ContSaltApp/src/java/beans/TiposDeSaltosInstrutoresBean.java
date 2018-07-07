/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classDAO.TipoDeSaltoDAO;
import classDAO.TiposDeSaltosInstrutoresDAO;
import classTO.TipoDeSaltoTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Almir
 */
@Named(value = "tiposDeSaltosInstrutoresBean")
@SessionScoped
public class TiposDeSaltosInstrutoresBean implements Serializable {

    private int idInstrutor;
    private int idTiposDeSaltos[];
    private TiposDeSaltosInstrutoresDAO tiDAO; 
    private TipoDeSaltoDAO tDAO;
    
    public TiposDeSaltosInstrutoresBean() {
        this.setIdInstrutor(idInstrutor = 0);
        this.setTiDAO( new TiposDeSaltosInstrutoresDAO());
        this.settDAO(new TipoDeSaltoDAO());
        /*O vetor é inicializado com a quantidade de registros de tipos de saltos armazenados no banco*/
        this.setIdTiposDeSaltos(idTiposDeSaltos = new int[tDAO.contarTiposDeSaltosArmazenados()]);
    }
    
    /** 
    * Método que registra os tipos de saltos dos instrutores.
    * O método registra quais são os tipos de saltos que um instrutor realiza.
    */     
    public void tiposDeSaltosDoInstrutor(){
        List<TipoDeSaltoTO> list = tDAO.getTiposDeSaltos();       
        for (int i = 0; i < idTiposDeSaltos.length ; i++) { 
            /*Se ainda não houver um registro no banco, será criado um novo registro*/
            if(tiDAO.verificarSeExisteRegistro(idInstrutor, idTiposDeSaltos[i]) == 0){ 
                tiDAO.salvarTipoDeSaltoDoInstrutor(idInstrutor, idTiposDeSaltos[i]);
            }
            /*Os tipos de saltos selecionados pelo usuário
            seram removidas da lista(list)*/
            for(TipoDeSaltoTO salto : list){                
                if(salto.getIdTipoDeSalto()==idTiposDeSaltos[i]){
                    list.remove(salto);
                    break;
                }
            }
        }
        /* A lista (list) resultante possui os tipos de salto que o intrutor não realiza.*/        
        for(TipoDeSaltoTO tipSalt : list){
             /*Se houver registro no banco, excluí-lo-ei*/
             if(tiDAO.verificarSeExisteRegistro(idInstrutor, tipSalt.getIdTipoDeSalto()) != 0){
                tiDAO.excluirTipoDeSaltoDoInstrutor(idInstrutor, tipSalt.getIdTipoDeSalto());
            }
        }
        
    }
        /** 
    * Método que informa os tipos de saltos de um instrutor.
    * O método retona uma lista com todos os tipos de saltos que um instrutor realiza.
    * @param idInstrutor int - É identificado(chave primária no banco de dados) de um instrutor.
    * @return List - O retorno é uma lista de todos os tipos de saltos que o instrutor realiza.
    */  
   public List<TipoDeSaltoTO> getTiposDeSaltosPorInstrutor(int idInstrutor){
        return tiDAO.getTiposDeSaltosPorInstrutor(idInstrutor);
    }
    
    public int getIdInstrutor() {
        return idInstrutor;
    }

    public void setIdInstrutor(int idInstrutor) {
        this.idInstrutor = idInstrutor;
    }

    public int[] getIdTiposDeSaltos() {
        return idTiposDeSaltos;
    }

    public void setIdTiposDeSaltos(int[] idTiposDeSaltos) {
        this.idTiposDeSaltos = idTiposDeSaltos;
    }

    public TiposDeSaltosInstrutoresDAO getTiDAO() {
        return tiDAO;
    }

    public void setTiDAO(TiposDeSaltosInstrutoresDAO tiDAO) {
        this.tiDAO = tiDAO;
    }

    public TipoDeSaltoDAO gettDAO() {
        return tDAO;
    }

    public void settDAO(TipoDeSaltoDAO tDAO) {
        this.tDAO = tDAO;
    }
    
}
