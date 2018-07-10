/**
 * @author Almir
 * @version 1.01
 */
package classBO;

import classDAO.InstrutorDAO;
import classDAO.TaxaSobrepesoDAO;
import classDAO.TipoDeSaltoDAO;
import classDAO.TiposDeSaltosInstrutoresDAO;
import classTO.ClienteTO;
import classTO.DecolagemTO;
import classTO.InstrutorTO;
import classTO.TaxaSobrepesoTO;
import classTO.TipoDeSaltoTO;
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
    private ClienteTO client;
    private DecolagemTO deco;
    private TipoDeSaltoTO tipSalt;

    public Filtros() {
        this.setTipSaltDao(new TipoDeSaltoDAO());
        this.setTaxaSobresoDao(new TaxaSobrepesoDAO());
        
        
        this.setIntrutorDao(new InstrutorDAO());
        this.setTipSaltInstDao(new TiposDeSaltosInstrutoresDAO());
        this.setClient(new ClienteTO());
        this.setDeco(new DecolagemTO());
        this.setTipSalt(new TipoDeSaltoTO());
    }
    
     
    /** Método para buscar o tipo de salto.
    * @param id - É o número de identificação de um tipo de salto.
    * @return TipoDeSaltoTO - Um tipo de salto.
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
    
    /** Método para filtrar a taxa de sobrepeso.
    * @param tipSalt - É o tipo de salto.
    * @param cliente - É o cliente.
    * @return TaxaSobrepesoTO - É a taxa de sobrepeso.
    */    
    public TaxaSobrepesoTO getTaxaDeSobrepeso(TipoDeSaltoTO tipSalt, ClienteTO cliente){
        TaxaSobrepesoTO taxaSobrepeso = new TaxaSobrepesoTO();
        if(tipSalt.getTaxaDeSobrePeso().equals("false")){
            for(TaxaSobrepesoTO taxa : taxaSobresoDao.getTaxasSobrepesos()){
                taxaSobrepeso=taxa;
                break;
            }                      
        }
        else{
            for(TaxaSobrepesoTO taxa : taxaSobresoDao.getTaxasSobrepesos()){
                if(cliente.getPeso()<= taxa.getPeso()){
                    taxaSobrepeso=taxa;
                    break;
                }
            }
        }
        return taxaSobrepeso;    
    }
    
    
    /** Método que retorna uma lista dos instrutores que estão em uma decolagem.
    * @param iddecolagem int - É o número de identificação de uma decolagem.
    * @return List - Uma lista com intrutores.
    */
    public List<InstrutorTO> getInstrutoresDecolagem(int iddecolagem){
        List<InstrutorTO> lstA = new LinkedList<>();
        //lstA = intrutorDao.getInstrutoresDecolagem(iddecolagem);
        return lstA;
    }
    
    /** Método que retorna uma lista dos intrutores que execultam um determindo tipo de salto.
    * @param idTipodeSalto int - É o número de identificação de um tipo de salto.  
    * @return List  - Instrutores*/
    public List<InstrutorTO> filtraInstruoresPorTipodeSalto(int idTipodeSalto){
        List<InstrutorTO> lstA = new LinkedList<>();
        lstA = tipSaltInstDao.getInstrutoresPorTipoDeSalto(idTipodeSalto);        
        return lstA;
    }
    
    /** Método que retorna uma lista com os intrutores presentes.
    *   @return List  - Instrutores presentes */
    public List<InstrutorTO> filtraPresenca(){
        List<InstrutorTO> lstA = new LinkedList<>();
        lstA = intrutorDao.getInstrutores();
        for(int i=0;i<lstA.size();i++){
            if(lstA.get(i).getPresenca().equals("false")){
                lstA.remove(i);
            }
        }
        return lstA;
    }
    
    /** Método que ordena uma lista de intrutores. Os isntrutore
    são odenados pela suas datas de admissão.
    *@param lstA List - Lista com instrutores.   
    *@return List  - Lista de instrutores ordenada*/
    public List<InstrutorTO> OrdenaInstrutores(List<InstrutorTO> lstA){
        List<InstrutorTO> lstB = new LinkedList<>();
        
        return lstA;
    }
    
    public List<InstrutorTO> getInstrutores(){
        List<InstrutorTO> lstA = new LinkedList<>();
        
        return lstA;
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

    public ClienteTO getClient() {
        return client;
    }

    public void setClient(ClienteTO client) {
        this.client = client;
    }

    public DecolagemTO getDeco() {
        return deco;
    }

    public void setDeco(DecolagemTO deco) {
        this.deco = deco;
    }

    public TipoDeSaltoTO getTipSalt() {
        return tipSalt;
    }

    public void setTipSalt(TipoDeSaltoTO tipSalt) {
        this.tipSalt = tipSalt;
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
