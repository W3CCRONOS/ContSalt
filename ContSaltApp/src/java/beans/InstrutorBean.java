/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import classDAO.InstrutorDAO;
import classTO.InstrutorTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import utilitarios.ConversorDeDatas;

/**
 *Classe de conexão das páginas .xhtml com o objeto instrutor.
 * @author Almir
 * @version 1.0
 * @see InstrutorTO
 * @see InstrutorDAO
 */
@Named(value = "instrutorBean")
@SessionScoped
public class InstrutorBean implements Serializable {

    private InstrutorTO CTO;
    private InstrutorDAO cDAO ;
    private Date dataUtil;
    private ConversorDeDatas alteraData;

    public InstrutorBean() {
        //Inicialização de variáveis.
        this.setCTO(new InstrutorTO());
        this.setcDAO(new InstrutorDAO());
        this.setDataUtil(new Date());
        this.setAlteraData(new ConversorDeDatas());
    }
    /**
    * Método que cria um registro no banco de dados. Antes, ele converte a
    * data no formato util.Date para o formato sql.Date.
    * Depois da conversão ele envia o objeto instanciado para ser salvo no banco de dados.
    * Por fim, reinicializa as variáveis.
    */
    public  void salvar(){
        CTO.setAdmissao(alteraData.passarDataUtilParaDataSql(dataUtil));
        cDAO.salvar(CTO);
        CTO = new InstrutorTO();
        dataUtil = new Date();
    }
    
    /**
    * Método que retorna uma lista de registro dos instrutore armazenados
    * no banco de dados.
    * @return List - Lista de instrutores, objetos InstrutorTO.  
    */
    public List<InstrutorTO> getInstrutores(){       
        return cDAO.getInstrutores();
    }

    
    /**
    * Método para excluir o registro de um instrutor.
    * @param instrutor InstrutorTO - É um objeto do tipo InstrutorTO.
    * @see InstrutorTO
    * @see InstrutorDAO
    */
     public void excluir(InstrutorTO instrutor){ 
        cDAO.excluir(instrutor);
    }
    
    /**
    * Método que pegar um objeto do tipo InstrutorTO, apresentado dentro de uma 
    * tabela numa página .xhtml. Antes, ele converte a data no formato sql.Date
    * para o formato util.Date.
    * @param instrutor InstrutorTO - É um objeto do tipo InstrutorTO.
    * @see InstrutorTO
    * @see ConversorDeDatas
    */
    public  void preparaAlteracao(InstrutorTO instrutor){
       setDataUtil(alteraData.passarDataSqlParaDataUtil(instrutor.getAdmissao()));
       setCTO(instrutor);
    }
    /**
    * Método que altera um registro no banco de dados. Antes, ele converte a
    * data no formato util.Date para o formato sql.Date.
    * Depois da conversão ele envia o objeto instanciado para ser alterado no banco de dados.
    * Por fim, reinicializa as variáveis.
    * @see InstrutorTO
    * @see InstrutorDAO
    * @see ConversorDeDatas
    */ 
    public void alterar(){ 
         CTO.setAdmissao(alteraData.passarDataUtilParaDataSql(dataUtil));
         cDAO.alterar(CTO);
         CTO = new InstrutorTO();
         dataUtil = new Date();
    }
    
    /**
    * Método para informar a presença de um instrutor. Para realizar um salto o
    * sitema precisará verificar quais instrutores estão presentes.
    * Através deste método o usuário alterar as presenças dos instrutores.
    * @param instrutor InstrutorTO - É um objeto do tipo InstrutorTO.
    * @see InstrutorTO
    * @see InstrutorDAO
    * @see ConversorDeDatas
    */ 
    public void presenca(InstrutorTO instrutor){
        setCTO(instrutor);
        String presenca = instrutor.getPresenca();
        if(presenca.equals("false")){
            CTO.setPresenca("true");
            cDAO.presenca(CTO);
        }
        else{
            CTO.setPresenca("false");
            cDAO.presenca(CTO);
        }
    }
    
    public InstrutorTO getCTO() {
        return CTO;
    }

    public void setCTO(InstrutorTO CTO) {
        this.CTO = CTO;
    }

    public InstrutorDAO getcDAO() {
        return cDAO;
    }

    public void setcDAO(InstrutorDAO cDAO) {
        this.cDAO = cDAO;
    }

    public Date getDataUtil() {
        return dataUtil;
    }

    public void setDataUtil(Date dataUtil) {
        this.dataUtil = dataUtil;
    }

    public ConversorDeDatas getAlteraData() {
        return alteraData;
    }

    public void setAlteraData(ConversorDeDatas alteraData) {
        this.alteraData = alteraData;
    }    
}
