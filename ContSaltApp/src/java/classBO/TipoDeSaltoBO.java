/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classBO;

import classDAO.TipoDeSaltoDAO;
import classTO.TipoDeSaltoTO;
import java.util.List;

/**
 *
 * @author Almir
 */
public class TipoDeSaltoBO {
    TipoDeSaltoDAO tipodesaltoDao;
    public TipoDeSaltoBO() {
        tipodesaltoDao = new TipoDeSaltoDAO();
    }   
    public void salvar(TipoDeSaltoTO tipodesalto)
    {
      tipodesaltoDao.salvar(tipodesalto); 
    }    
     public void excluir(TipoDeSaltoTO tipodesalto)
    {
      tipodesaltoDao.excluir(tipodesalto);   
    }     
    public void alterar(TipoDeSaltoTO tipodesalto)
    {
      tipodesaltoDao.alterar(tipodesalto);   
    } 
    public List<TipoDeSaltoTO> getTipoDeSalto(){
        return tipodesaltoDao.getTiposDeSaltos();
    }
}
