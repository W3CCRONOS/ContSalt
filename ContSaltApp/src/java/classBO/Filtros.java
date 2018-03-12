/**
 * @author Almir
 * @version 1.01
 */
package classBO;

import classDAO.InstrutorDAO;
import classTO.InstrutorTO;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author almir
 */
public class Filtros {
    private InstrutorDAO intrutorDao;

    public Filtros() {
        this.setIntrutorDao(new InstrutorDAO());
    }
    
    /** Método para retorno de uma lista com os intrutores que estão presentes.
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
    public InstrutorDAO getIntrutorDao() {
        return intrutorDao;
    }
    public void setIntrutorDao(InstrutorDAO intrutorDao) {
        this.intrutorDao = intrutorDao;
    }
    
}
