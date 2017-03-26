/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classBO.InstrutorBO;
import classTO.InstrutorTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Almir
 */
@Named(value = "instrutorBean")
@SessionScoped
public class InstrutorBean implements Serializable {

    
    private InstrutorTO CTO;
    private InstrutorBO cBO;
    
    public InstrutorBean() {
        this.setCTO(new InstrutorTO());
        this.setcBO(new InstrutorBO());
    }

    
      public  void salvar(){
        String x;
        CTO.setPresenca(0);
        System.out.println(CTO.getNome());
        System.out.println(CTO.getCpf());
        System.out.println(CTO.getAdmi());
        System.out.println(CTO.getPresenca());
       // cBO.salvar(CTO); 
       // CTO = new InstrutorTO();
    }
    
  
    public List<InstrutorTO> getInstrutores(){
        return cBO.getInstrutores();
    }
    
     public void excluir(InstrutorTO c){ 
        cBO.excluir(c);
    }
     
    public  void preparaAlteracao(InstrutorTO c){
         this.setCTO(c);
    }
     
    public void alterar(){   
        cBO.alterar(CTO);
        CTO = new InstrutorTO();
    }

    public InstrutorTO getCTO() {
        return CTO;
    }

    public void setCTO(InstrutorTO CTO) {
        this.CTO = CTO;
    }

    public InstrutorBO getcBO() {
        return cBO;
    }

    public void setcBO(InstrutorBO cBO) {
        this.cBO = cBO;
    }
  
}
