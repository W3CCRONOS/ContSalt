/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classBO;

import classDAO.InstrutorDAO;
import classTO.InstrutorTO;
import java.util.List;

/**
 *
 * @author Almir
 */
public class InstrutorBO {
        InstrutorDAO instrutorDao;
    public InstrutorBO() {
        instrutorDao = new InstrutorDAO();
    }   
    public void salvar(InstrutorTO i)
    {
      instrutorDao.salvar(i); 
    }    
     public void excluir(InstrutorTO i)
    {
      instrutorDao.excluir(i);   
    }     
    public void alterar(InstrutorTO i)
    {
       instrutorDao.alterar(i);   
    } 
    public List<InstrutorTO> getInstrutores(){
        return instrutorDao.getIntrutores();
    }
}
