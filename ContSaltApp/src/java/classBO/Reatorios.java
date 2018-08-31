/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classBO;

import classDAO.DecolagemDAO;
import classDAO.InstrutorDAO;
import classDAO.SaltoDAO;
import classDAO.TaxaSobrepesoDAO;
import classDAO.TipoDeSaltoDAO;
import classTO.DecolagemTO;
import classTO.InstrutorTO;
import classTO.SaltoTO;
import classTO.TaxaSobrepesoTO;
import classTO.TipoDeSaltoTO;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import utilitarios.ConversorDeDatas;

/**
 * Classe que desenvolve a busca dos relatorios de saltos.
 * @author Almir
 * @version 1.0
 */
public class Reatorios {
    
    private DecolagemDAO decoDao = new DecolagemDAO();
    private SaltoDAO saltDao = new SaltoDAO();
    private InstrutorDAO instDao =  new InstrutorDAO();
    private TipoDeSaltoDAO tipSaltDao = new TipoDeSaltoDAO();
    private ConversorDeDatas alteraData = new ConversorDeDatas();
    private TaxaSobrepesoDAO taxaDao = new TaxaSobrepesoDAO();
    /** 
    * Método para buscar os saltos de um instrutor em um periodo. 
    * O método busca os saltos realizados por um determinado instrutor
    * em um determinado periodo.
    * @param idInstrutor - identificação de um instrutor
    * @param dataIni - data inicial de um periodo.
    * @param dataFin - data final de um periodo.
    * @return - uma lista de saltos realizados por um instrutor em um periodo.
    */ 
    public List<SaltoTO> getSaltos(int idInstrutor, Date dataIni, Date dataFin){
        List<SaltoTO> lstA = new LinkedList<>();
        List<SaltoTO> lstB = new LinkedList<>();
        List<DecolagemTO> decolagens = new LinkedList<>();
        
        java.sql.Date dataIniSql = alteraData.passarDataUtilParaDataSql(dataIni);
        java.sql.Date dataFinSql = alteraData.passarDataUtilParaDataSql(dataFin);     
        //Todas as decolagens realizadas no periodo.
        decolagens = decoDao.getDecolagensPorPeriodo(dataIniSql, dataFinSql);
        //Todos os saltos realizados pelo instrutor. 
        lstB = saltDao.getSaltosPorInstrutorDecolagem(idInstrutor);
        /*Percorro os saltos separando os que foram realizados nas decolagens dentro periodo*/
        for(SaltoTO salt : lstB){
            for(DecolagemTO deco : decolagens){
                if(deco.getIddecolagem() == salt.getIdDecolagem()){
                    lstA.add(salt);
                }
                    
            }
        }
        /*Retorno os saltos realizados pelo instrutor em um determinado periodo*/
        return lstA;
    }
       
    /** Método para identificar os tipos de saltos de uma lista de saltos.
    *   O método identifica quais são os tipos de saltos que foram realizados em uma lista de saltos.
    *   @param saltos - lista de saltos.
    *   @return listA  - lista de tipos de saltos.
    */
    public List<TipoDeSaltoTO> getTiposDeSaltos(List<SaltoTO> saltos){

        List<TipoDeSaltoTO> lstA = new LinkedList<>();
        List<TipoDeSaltoTO> tiposDeSaltos = new LinkedList<>();
        
        //Todos os tipos de saltos
        tiposDeSaltos = tipSaltDao.getTiposDeSaltos();
       /*Percorro os saltos separando os tipos de saltos de cada salto.*/
        for(SaltoTO salto : saltos){
            for(TipoDeSaltoTO tipSalt : tiposDeSaltos){
                if(salto.getIdTipoDeSalto() == tipSalt.getIdTipoDeSalto()){
                    lstA.add(tipSalt);                    
                }
                    
            }
        }
        
        /*Crio um variável que não armazena conteudos repitidos e adciono a lista dos
        tipos de saltos.*/
        Set<TipoDeSaltoTO> lstC = new HashSet<>();
        for(TipoDeSaltoTO a: lstA) {
            lstC.add(a);
        }        
        lstA = new LinkedList<>();//Reinicio a varável.
        
       /*Repasso a nova lista de tipos de saltos*/
        for(TipoDeSaltoTO a: lstC) {
            lstA.add(a);
        }
        System.out.println("relatorio");
        System.out.println(lstA.size());
        for(TipoDeSaltoTO a: lstA) {
            System.out.println(a.getNome());
        }
        
        return lstA;//Retorno a lista de tipos de saltos.
    } 
    
    /** Método para calcular os valores dos saltos.
    *   O método calcula os valores dos saltos segundo um tipo de salto
    *   @param tipSalto - tipo de salto.
    *   @param saltos - lista de saltos.
    *   @return valor  - valor dos saltos.
    */
    public double getValorPorTipoDeSalto(TipoDeSaltoTO tipSalto,List<SaltoTO> saltos){
        double valor = 0.00;
            for(SaltoTO salt : saltos){
                if(tipSalto.getIdTipoDeSalto() == salt.getIdTipoDeSalto()){
                    valor = valor + tipSalto.getValor();
                }               
            }
            return valor;

    }
    
    /** Método para calcular os valores por tipo de saltos.
    *   O método calcular os valores totais das somas dos saltos.
    *   @param saltos - lista de saltos.
    *   @return valor  - valor total dos saltos.
    */
    public double getValorDosSalto(List<SaltoTO> saltos){
        double valor = 0.00;
        List<TipoDeSaltoTO> tipsSalts = new LinkedList<>();
        tipsSalts = tipSaltDao.getTiposDeSaltos();
             for(SaltoTO salt : saltos){
                for(TipoDeSaltoTO tipSalt: tipsSalts)
                    if(tipSalt.getIdTipoDeSalto() == salt.getIdTipoDeSalto()){
                        valor = valor + tipSalt.getValor();
                    }               
            }
            return valor;
    }
    
    
    
    
    
    
    /** Método para calcular as taxas de sobrepeso.
    *   O método realiza a soma total das taxas de sobrepeso dos saltos realizados.
    *   @param saltos - lista de saltos.
    *   @return valor  - valor da soma das taxas de sobrepeso.
    */
    public double somaTaxasDeSoprepeso(List<SaltoTO> saltos){
        double valor = 0.00;
        List<TaxaSobrepesoTO> taxas = new LinkedList<>();
        taxas = taxaDao.getTaxasSobrepesos();
            for(SaltoTO salt : saltos){
                for(TaxaSobrepesoTO taxa: taxas)
                    if(taxa.getIdTaxaSobrepeso() == salt.getIdTaxaDeSobrepeso()){
                        valor = valor + taxa.getValor();
                    }               
            }
            return valor;

    }
    
    
    public InstrutorTO getInstrutor(int idInstrutor){
       return instDao.getInstrutorPeloId(idInstrutor);
    }
    
    public DecolagemDAO getDecoDao() {
        return decoDao;
    }

    public void setDecoDao(DecolagemDAO decoDao) {
        this.decoDao = decoDao;
    }

    public SaltoDAO getSaltDao() {
        return saltDao;
    }

    public void setSaltDao(SaltoDAO saltDao) {
        this.saltDao = saltDao;
    }

    public ConversorDeDatas getAlteraData() {
        return alteraData;
    }

    public void setAlteraData(ConversorDeDatas alteraData) {
        this.alteraData = alteraData;
    }

    public InstrutorDAO getInstDao() {
        return instDao;
    }

    public void setInstDao(InstrutorDAO instDao) {
        this.instDao = instDao;
    }

    public TipoDeSaltoDAO getTipSaltDao() {
        return tipSaltDao;
    }

    public void setTipSaltDao(TipoDeSaltoDAO tipSaltDao) {
        this.tipSaltDao = tipSaltDao;
    }

    public TaxaSobrepesoDAO getTaxaDao() {
        return taxaDao;
    }

    public void setTaxaDao(TaxaSobrepesoDAO taxaDao) {
        this.taxaDao = taxaDao;
    }
    
    
}
