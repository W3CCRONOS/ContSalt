
package classDAO;

import classTO.InstrutorTO;
import classTO.TipoDeSaltoTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import utilitarios.Conexao;

/**
 * Classe para acessar os dados dos tipos de saltos e instrutores.
 * @author Almir
 * @version 1.0
 */
public class TiposDeSaltosInstrutoresDAO{
    
    Connection conn;
    
    private InstrutorDAO iDao;
    private TipoDeSaltoDAO tipSaltDao;
    
    public TiposDeSaltosInstrutoresDAO() {
        this.setiDao(new InstrutorDAO());
        this.setTipSaltDao(new TipoDeSaltoDAO());
        conn = new Conexao().conectar();
    }
    /**
    * O método para salvar registros no banco.
    * Ele verifica se não há um registro especifico de relacionamento, entre um tipo de salto e um instrutor.
    * Se não houver, ele cria.
    * @param idInstrutor int - número da chave extrangeira de um instrutor.
    * @param idTipoDeSalto int - número da chave extrangeira de um tipo de salto.
    */    
    public void salvarTipoDeSaltoDoInstrutor(int idInstrutor, int idTipoDeSalto){
        /*Verifica se o registro ainda não existe para então salvá-lo*/
        if (verificarSeExisteRegistro(idInstrutor, idTipoDeSalto) == 0){                    
            try{
               PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO instrutor_has_tipodesalto (idinstrutor,idtipodesalto) VALUES (?,?)");
                ppStmt.setInt(1,idInstrutor);
                ppStmt.setInt(2,idTipoDeSalto);
                ppStmt.execute();
                ppStmt.close();
            }
            catch(SQLException ex){         
                ex.printStackTrace();
            }                
        } 
    }
  
    /** 
    * Método para excluir registro no banco.
    * O método verifica se existe um registro de relacionamento do tipo de salto ligado ao instrutor. Se houver, ele o exclui.
    * @param idInstrutor int - É o número da chave extrangeira de um instrutor.
    * @param idTipoDeSalto int - É o número da chave extrangeira de um tipo de salto.
    */
    public void excluirTipoDeSaltoDoInstrutor(int idInstrutor, int idTipoDeSalto){
        if (verificarSeExisteRegistro(idInstrutor, idTipoDeSalto) != 0){
            try{
                PreparedStatement ppStmt =  conn.prepareStatement("DELETE FROM instrutor_has_tipodesalto WHERE idinstrutor = ? AND idtipodesalto = ?");
                ppStmt.setInt(1,idInstrutor);
                ppStmt.setInt(2,idTipoDeSalto);
                ppStmt.execute();
                ppStmt.close();
            }
            catch(SQLException ex){         
                ex.printStackTrace();
            }                
        } 
    }
    
    /** 
    * Método para verificar se existe registro de um determinado instrutor com uma tipo de salto especifico.
    * @param idInstrutor int - É o número da chave extrangeira de um instrutor.
    * @param idTipoDeSalto int - É o número da chave extrangeira de uma tipo de salto.
    * @return total int - Retorna 0(zero) para ausência de registro ou 1(um) se houver registro.    
    */
    private int verificarSeExisteRegistro(int idInstrutor, int idTipoDeSalto){
        int total = 0;
        ResultSet rs;
        try{ 
            PreparedStatement ppStmt =  conn.prepareStatement("SELECT COUNT(idinstrutor) AS quantidadeDeRegistros FROM instrutor_has_tipodesalto WHERE idinstrutor = ? AND idtipodesalto = ? ");
            ppStmt.setInt(1,idInstrutor);
            ppStmt.setInt(2,idTipoDeSalto);
            rs = ppStmt.executeQuery();
            /* Se  rs.next() for true...(existe algum registro dentro do ResuslSet)*/
            if(rs.next()){
                /* a variável total, recebe o valor do ResultSet. 
                Se este valor for diferente de zero, então, foi encontrado um registro armazenado no banco*/
                total = rs.getInt("quantidadeDeRegistros"); 
            }
            ppStmt.close();
	    rs.close(); 
        }       
        catch(SQLException ex){         
            ex.printStackTrace();
        }    
        return total;        
    }        
    
    /** 
    * Método para buscar os instrutores que executam um determinado tipo de salto.
    * @param idTipodeSalto int - É o número de identificação de um tipo de salto.
    * @return List - Uma lista com intrutores.
    */
     public List<InstrutorTO> getInstrutoresPorTipoDeSalto(int idTipodeSalto){
        List<InstrutorTO> list = new LinkedList<>();
        ResultSet rs;
        try{ 
            PreparedStatement ppStmt =  conn.prepareStatement("SELECT i.idinstrutor, i.nome, i.cpf, i.admissao, i.presenca FROM instrutor i JOIN instrutor_has_tipodesalto a ON a.idinstrutor = i.idinstrutor AND a.idtipodesalto = ? ORDER BY admissao");
            ppStmt.setInt(1,idTipodeSalto);
            rs = ppStmt.executeQuery();
            while(rs.next()){
                       list.add(iDao.getIntrutor(rs));
            }
            ppStmt.close();
	    rs.close(); 
        }       
        catch(SQLException ex){         
            ex.printStackTrace();
        }
        return list;
    }
     
    
    /** 
    * Método para buscar os tipos de saltos que um determinado instrutor pode executar.
    * @param idInstrutor int - É o número de identificação de um instrutor.
    * @return List - Uma lista com Tipos de Saltos.
    */     
    public List<TipoDeSaltoTO> getTiposDeSaltosPorInstrutor(int idInstrutor){
            List<TipoDeSaltoTO> list = new LinkedList<>();
            ResultSet rs;
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT i.idtipodesalto, i.nome, i.valor FROM tipodesalto i JOIN instrutor_has_tipodesalto a ON a.idtipodesalto = i.idtipodesalto AND a.idinstrutor = ?");
                ppStmt.setInt(1,idInstrutor);
                rs = ppStmt.executeQuery();
                while(rs.next()){
                    list.add(tipSaltDao.getTipoDeSalto(rs));                        
                }
                ppStmt.close();
                rs.close(); 
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
            return list;
    }
   

    public InstrutorDAO getiDao() {
        return iDao;
    }

    public void setiDao(InstrutorDAO iDao) {
        this.iDao = iDao;
    }

    public TipoDeSaltoDAO getTipSaltDao() {
        return tipSaltDao;
    }

    public void setTipSaltDao(TipoDeSaltoDAO tipSaltDao) {
        this.tipSaltDao = tipSaltDao;
    }
     
     
}
