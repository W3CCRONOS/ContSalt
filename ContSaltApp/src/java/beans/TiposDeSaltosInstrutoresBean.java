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
        this.setIdTiposDeSaltos(idTiposDeSaltos = new int[tDAO.contarTaxasDeSobrePesoArmazenadas()]);
    }
    
    public void tiposDeSaltosDoInstrutor(){
        /*Lista com todas os tipos de saltos armazenados no banco.*/
        List<TipoDeSaltoTO> list = tDAO.getTiposDeSaltos();
        /* .length, informa o comprimento do vetor idTiposDeSaltos.*/       
        for (int i = 0; i < idTiposDeSaltos.length ; i++) { 
            tiDAO.salvarTipoDeSaltoDoInstrutor(idInstrutor, idTiposDeSaltos[i]);
            /*Os tipos de saltos selecionados pelo usuário
            seram removidas da lista(list)*/
            for(TipoDeSaltoTO salto : list){                
                if(salto.getIdTipoDeSalto()==idTiposDeSaltos[i]){
                    list.remove(salto);
                    break;
                }
            }
        }
        /* A lista resultante possui os tipos de salto que o intrutor não realiza.
        Caso existam registros no banco, serão apagados os registros de relacionamentos com o instrutor*/
        for(TipoDeSaltoTO salto : list){
            tiDAO.excluirTipoDeSaltoDoInstrutor(idInstrutor, salto.getIdTipoDeSalto());
        }
        
    }
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
