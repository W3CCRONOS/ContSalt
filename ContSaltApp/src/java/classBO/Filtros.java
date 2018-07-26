/**
 * @author Almir
 * @version 1.01
 */
package classBO;

import classDAO.DecolagemDAO;
import classDAO.InstrutorDAO;
import classDAO.SaltoDAO;
import classDAO.TaxaSobrepesoDAO;
import classDAO.TipoDeSaltoDAO;
import classDAO.TiposDeSaltosInstrutoresDAO;
import classTO.ClienteTO;
import classTO.DecolagemTO;
import classTO.InstrutorTO;
import classTO.SaltoTO;
import classTO.TaxaSobrepesoTO;
import classTO.TipoDeSaltoTO;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author almir
 */
public class Filtros {
    private TipoDeSaltoDAO tipSaltDao;
    private TaxaSobrepesoDAO taxaSobresoDao;      
    private InstrutorDAO intrutorDao;
    private TiposDeSaltosInstrutoresDAO tipSaltInstDao;
    private DecolagemDAO decoDao;
    private SaltoDAO saltoDao;
    private Date dataUtil;

    public Filtros() {
        this.setTipSaltDao(new TipoDeSaltoDAO());
        this.setTaxaSobresoDao(new TaxaSobrepesoDAO());
        this.decoDao = new DecolagemDAO();
        this.saltoDao = new SaltoDAO();
        this.setIntrutorDao(new InstrutorDAO());
        this.setTipSaltInstDao(new TiposDeSaltosInstrutoresDAO());
        this.dataUtil = new Date();
    }
    
     
    /** Método para buscar um tipo de salto.
    * @param id - É o número de identificação de um tipo de salto.
    * @return - Um tipo de salto.
    */
    public TipoDeSaltoTO getTipoDeSaltoTO(int id){
            TipoDeSaltoTO tipSalt = new TipoDeSaltoTO();
         for(TipoDeSaltoTO tipo : tipSaltDao.getTiposDeSaltos()){
                if(tipo.getIdTipoDeSalto() == id ){
                    tipSalt = tipo;
                    break;
                }                                  
            }
        return tipSalt;         
    }
    
    
     public List<InstrutorTO> getInstrutores(){       
        
     return intrutorDao.getInstrutores();
    }  
    
    
    /** Método para uma decolagem.
    * @param id - É o número de identificação de uma decolagem.
    * @return  - Uma decolagem.
    */
    public DecolagemTO getDecolagemTO(int id){
        DecolagemTO decolagem1 = new DecolagemTO();
         /* A variável para data(dataSql) recebe a data atual do sistema*/
        java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
         for(DecolagemTO decolagem2 : decoDao.getDecolagens(dataSql)){
                if(decolagem2.getIddecolagem()== id ){
                    decolagem1 = decolagem2;
                    break;
                }                                  
            }
        return decolagem1;         
    }
    
    /** Filtrar a taxa de sobrepeso.
    * O método recebe o cliente e o tipo de salto. Segundo o peso do cliente e o tipo de salto
    * faz-se a triagem da taxa de sobrepeso
    * @param tipSalt - É o tipo de salto.
    * @param cliente - É o cliente.
    * @return TaxaSobrepesoTO - É a taxa de sobrepeso.
    */
    
    public TaxaSobrepesoTO getTaxaDeSobrepeso(TipoDeSaltoTO tipSalt, ClienteTO cliente){
        TaxaSobrepesoTO taxaSobrepeso = new TaxaSobrepesoTO();
        /* Se O tipo de salto NÃO gerar taxa de sobrepeso,
                    independente do peso do cliente.   */
        if(tipSalt.getTaxaDeSobrePeso().equals("false")){
            //Busco todas as taxas de sobrepeso, ordenadas pelo peso
            for(TaxaSobrepesoTO taxa : taxaSobresoDao.getTaxasSobrepesos()){
                // Pego a primeira que contém seu valor igual a zero.
                taxaSobrepeso = taxa;
                break;
            }                      
        }
        /*Senão*/
        else{
            //Busco novamente todas as taxas de sobrepeso, ordenadas pelo peso            
            for(TaxaSobrepesoTO taxa : taxaSobresoDao.getTaxasSobrepesos()){
                /*Pego a taxa de sobrepeso que o peso do cliente estiver entre o
                peso mínimo e máximo da taxa*/
                if(cliente.getPeso()>= taxa.getPesoMin() && cliente.getPeso()<=taxa.getPesoMax()){
                    taxaSobrepeso=taxa;
                    break;
                }
            }
        }
        return taxaSobrepeso;    
    }
    
    /** Filtrar decolagem.
    * O método retornar as decolagens NÃO FINALIZADAS na data do salto. 
    * Um salto deve estar dentro de uma decolagem. E uma decolagem pode conter vários saltos.
    * @return list  - decolagens.
    */
    public List<DecolagemTO> decolagens(){
        
        List<DecolagemTO> list =new LinkedList<>();//Lista para decolagens.
        /* A variável para data(dataSql) recebe a data atual do sistema*/
        java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
        /*Percorro todas as decolagem criadas na data do salto e separo as decolagem que ainda não foram
        finalizadas.*/
        for(DecolagemTO deco : decoDao.getDecolagens(dataSql)){
             if(deco.getStatus().equals("false")){
                 list.add(deco);
             }
        }
        
        return list; //Retorno das decolagem ainda não finalizadas. 
    }    
    
    /** Filtro dos intrutores presentes.
    *   O método retorna uma lista com os instrutores presentes.
    *   Instrutores ausentes não podem realizar saltos.
    *   @return listA  - Instrutores presentes */
    public List<InstrutorTO> instrutoresPresentes(){
        List<InstrutorTO> lstA = new LinkedList<>();
        /*Percorre-se a lista geral dos instrutores cadastrados*/
        for(InstrutorTO instrutor : intrutorDao.getInstrutores()){
            /*Separo os instrutores que possuem o valor "true" na variável(presenca).*/
            if(instrutor.getPresenca().equals("true")){
                lstA.add(instrutor);
            }
        }
        return lstA;
    } 

    
    /** Filtro dos intrutores por tipo de salto.
    *   O método retorna uma lista de instrutores que execultam o tipo de salto informado.
    *   @param tipSalto  - tipo de salto.  
    *   @param instrutores   - instrutores presentes.  
    *   @return List  - instrutores por tipo de salto*/
    public List<InstrutorTO> instruoresPorTipodeSalto(List<InstrutorTO> instrutores, TipoDeSaltoTO tipoDeSalto){
        List<InstrutorTO> lstA = new LinkedList<>();                
        /* Percorre-se a lista com todos os istrutores que execultam
        o tipo de salto informado*/       
        for(InstrutorTO inst1 : tipSaltInstDao.getInstrutoresPorTipoDeSalto(tipoDeSalto.getIdTipoDeSalto())){
            /*Percorre-se a lista com os istrutores recebidos*/
            for (InstrutorTO inst2 : instrutores) {
                /*Compara-se os elementos das listas para que os elementos iguais
                sejam retornados.*/
                if(inst1.getIdInstrutor()==inst2.getIdInstrutor()){
                    lstA.add(inst1);
                }
            }
        }
        return lstA;
    }
    
    
    /** Filtro dos instrutores por peso.
     * O método recebe uma lista de instrutores e identifica quais intrutores podem
     * realizar o salto devido ao peso do cliente.
     * Como parametro de análise é utilizado o peso do cliente e o tipo de salto.
     * @param cliente - É o cliente.
     * @param taxa  - É a taxa de sobrepeso do salto.
     * @param instrutores - Uma lista de instrutores.
     * @return List  - Lista de instrutores
     */

    public List<InstrutorTO> instrutoresPorPeso(ClienteTO cliente, TaxaSobrepesoTO taxa, List<InstrutorTO> instrutores ){
        List<InstrutorTO> lstA = new LinkedList<>(); 
        /*Se a o valor da taxa de sobrepeso for igual a 0 (zero),
        significa que o cliente possui o peso baixo, o suficiente para todos os instrutores. 
        Ou o tipo de salto permite que o instrutor realize o salto, independente do peso do cliente.*/
        if(taxa.getValor()==0){
            lstA=instrutores;
        }
        /*Mas, se o valor da taxa de sobrepeso for diferente de 0 (zero), percorre-se
        a lista de istrutores, comparando o limite de peso dos instrutores com
        o peso do cliente*/
        else{
            for(InstrutorTO inst1: instrutores){
                if(inst1.getPeso() <= cliente.getPeso()) lstA.add(inst1);
            }
        }
        return lstA;
    }
    
    
        /**
     * Método que retorna uma lista dos instrutores que estão em uma decolagem.
     * @param iddecolagem int - É o número de identificação de uma decolagem.
     * @return List - Uma lista com intrutores.
    
    public List<InstrutorTO> getInstrutoresDecolagem(int iddecolagem){
        List<InstrutorTO> lstA = new LinkedList<>();
        //lstA = intrutorDao.getInstrutoresDecolagem(iddecolagem);
        return lstA;
    }
    * 
    *     /** Filtro dos instrutores por decolagem.
     * O método filtra os instrutores que já estão realizando algum salto
     * dentro da decolagem.
    * 
    * 
    */
    
    /** Filtro dos instrutores por decolagem.
    *   O método filtra os instrutores que já estão realizando algum salto
    *   dentro da decolagem.
    *   @param decolagem  - decolagem.  
    *   @param instrutores   - instrutores.  
    *   @return List  - instrutores que não estão na decolagem.
    */
    public List<InstrutorTO> instroresPorDecolagem(List<InstrutorTO> instrutores, DecolagemTO decolagem){
        List<InstrutorTO> lstA = new LinkedList<>(); 
        /*Verifico se existe algum salto na decolagem*/
        if(saltoDao.verificarSeExisteRegistro(decolagem) == 0){
            /*Se o resultdo for 0(zero), retorno a propria lista de instrutores sem alterações*/
            return instrutores;
        }
        else{//Senão
            /* Percorre-se a lista com todos os saltos que estão na decolagem*/
            for(SaltoTO salto : saltoDao.getSaltosPorDecolagem(decolagem.getIddecolagem())){
                /*Percorre-se a lista com os istrutores recebidos*/
                for (InstrutorTO instrutor : instrutores) {
                    /*Compara se o instrutor não está em nenhum salto dentro da decolagem.
                    Se ele não tiver salvo ele em uma lista*/
                    if(instrutor.getIdInstrutor()!=salto.getIdInstrutor()){
                        lstA.add(instrutor);
                    }
                }
            }  
            //retorna a lista com instrutores que não estão na decolagem.
            return lstA;
        }
    }
    
    public InstrutorDAO getIntrutorDao() {
        return intrutorDao;
    }
    public void setIntrutorDao(InstrutorDAO intrutorDao) {
        this.intrutorDao = intrutorDao;
    }

    public TiposDeSaltosInstrutoresDAO getTipSaltInstDao() {
        return tipSaltInstDao;
    }

    public void setTipSaltInstDao(TiposDeSaltosInstrutoresDAO tipSaltInstDao) {
        this.tipSaltInstDao = tipSaltInstDao;
    }

    public TipoDeSaltoDAO getTipSaltDao() {
        return tipSaltDao;
    }

    public void setTipSaltDao(TipoDeSaltoDAO tipSaltDao) {
        this.tipSaltDao = tipSaltDao;
    }

    public TaxaSobrepesoDAO getTaxaSobresoDao() {
        return taxaSobresoDao;
    }

    public void setTaxaSobresoDao(TaxaSobrepesoDAO taxaSobresoDao) {
        this.taxaSobresoDao = taxaSobresoDao;
    }
    
    
}
