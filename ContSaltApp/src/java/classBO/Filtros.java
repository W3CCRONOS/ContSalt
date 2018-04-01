/**
 * @author Almir
 * @version 1.01
 */
package classBO;

import classDAO.InstrutorDAO;
import classDAO.TiposDeSaltosInstrutoresDAO;
import classTO.InstrutorTO;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author almir
 */
public class Filtros {
    private InstrutorDAO intrutorDao;
    private TiposDeSaltosInstrutoresDAO tipSaltInstDao;

    public Filtros() {
        this.setIntrutorDao(new InstrutorDAO());
        this.setTipSaltInstDao(new TiposDeSaltosInstrutoresDAO());
    }
    
     /** Método que retorna uma lista dos instrutores que estão em uma decolagem.
    * @param iddecolagem int - É o número de identificação de uma decolagem.
    * @return List - Uma lista com intrutores.
    */
    public List<InstrutorTO> getInstrutoresDecolagem(int iddecolagem){
        List<InstrutorTO> lstA = new LinkedList<>();
        lstA = intrutorDao.getInstrutoresDecolagem(iddecolagem);
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
}
