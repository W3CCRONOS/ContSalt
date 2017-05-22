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

    //idInstrutor armazenará a identificação do instrutor selecionado.
    private int idInstrutor;
    //idTaxasSobrepeso e um vetor que armazenará as identificações das taxas selecionadas.
    private int idTiposDeSaltos[];
    private TiposDeSaltosInstrutoresDAO tiDAO; 
    private TipoDeSaltoDAO tDAO;
    
    public TiposDeSaltosInstrutoresBean() {
        this.setIdInstrutor(idInstrutor = 0);
        this.setTiDAO( new TiposDeSaltosInstrutoresDAO());
        this.settDAO(new TipoDeSaltoDAO());
        /*O vetor é inicializado com a quantidade de tipos de saltos registrados no banco*/
        this.setIdTiposDeSaltos(idTiposDeSaltos = new int[tDAO.contarTaxasDeSobrePesoArmazenadas()]);
    }
    
    public void tiposDeSaltosDoInstrutor(){
        /*A lista com todas os tipos de saltos armazenados no banco.*/
        List<TipoDeSaltoTO> list = tiDAO.getTiposDeSaltos();
        /* É possivel saber o tamanho do espaço reservado na memória para o vetor idTiposDeSaltos,
        mas não é possível saber quantos espaços estão ocupados, então foi utilizado .length .*/       
        for (int i = 0; i < idTiposDeSaltos.length ; i++) { 
            tiDAO.salvarTipoDeSaltoDoInstrutor(idInstrutor, idTiposDeSaltos[i]);               
            for(TipoDeSaltoTO salto : list){
                /*Se na lista(list) houver uma ou mais tipos de saltos com as identifcações(IdTipoSDeSaltos) iguais as identificações dos saltos selecionadas pelo usuário,
                estas saltos seram removidas da lista(list)*/
                if(salto.getIdTipoDeSalto()==idTiposDeSaltos[i]){
                    list.remove(salto);
                    break;
                }
            }
        }
        /*Após as taxas serem removidas da lista, a lista resultante é das taxas que o intrutor não faz.
        Então, caso estejão cadastradas, será desfeito o relacionamento com o instrutor*/
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
