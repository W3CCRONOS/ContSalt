/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classBO;

import classDAO.TaxaSobrepesoDAO;
import classTO.TaxaSobrepesoTO;
import java.util.List;

/**
 *
 * @author Skydive
 */
public class TaxaSobrepesoBO {
    TaxaSobrepesoDAO taxaDao;
    public TaxaSobrepesoBO() {
        taxaDao = new TaxaSobrepesoDAO();
    }   
    public void salvar(TaxaSobrepesoTO taxasobrepeso)
    {
      taxaDao.salvar(taxasobrepeso); 
    }    
     public void excluir(TaxaSobrepesoTO taxasobrepeso)
    {
      taxaDao.excluir(taxasobrepeso);   
    }     
    public void alterar(TaxaSobrepesoTO taxasobrepeso)
    {
      taxaDao.alterar(taxasobrepeso);   
    } 
    public List<TaxaSobrepesoTO> getTaxasSobrepesos(){
        return taxaDao.getTaxasSobrepesos();
    }
    
}
