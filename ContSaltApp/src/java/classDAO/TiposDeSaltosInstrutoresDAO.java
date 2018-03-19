
package classDAO;

import classTO.InstrutorTO;
import classTO.TipoDeSaltoTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import utilitarios.Conexao;

/**
 * Classe do padrão DAO.
 * Cria objetos responsáveis por acessar os dados dos tipos de saltos e instrutores.
 * @author Almir
 * @version 1.0
 */
public class TiposDeSaltosInstrutoresDAO extends TipoDeSaltoDAO{
    
    private InstrutorDAO iDao;
    public TiposDeSaltosInstrutoresDAO() {
        this.setiDao(new InstrutorDAO());
        conn = new Conexao().conectar();
    }
    /**
    * Método para salvar registros no banco.
    * O método verifica se ainda não existe um registro do tipo de salto ligado
    * ao instrutor. Se não houver um registro ele cria um no banco.
    * @param idInstrutor int - número da chave extrangeira de um instrutor.
    * @param idTipoDeSalto int - número da chave extrangeira de um tipo de salto.
    */    
    public void salvarTipoDeSaltoDoInstrutor(int idInstrutor, int idTipoDeSalto){
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
    * O método verifica se existe registro do tipo de salto ligados ao instrutor. Se houver um registro ele o exclui do banco.
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
    * Método para verificar se exite um registros no banco de um instrutor com uma tipo de salto.
    * @param idInstrutor int - É o número da chave extrangeira de um instrutor.
    * @param idTipoDeSalto int - É o número da chave extrangeira de uma tipo de salto.
    * @return total int - Retorna a quantidade de registros.    
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
    * Método para buscar os tipos de saltos que um instrutor realizar.
    * Este método recebe a identificação de um instrutor e faz uma busca 
    * procurando quais os tipos de saltos o instrutor realiza.
    * @param idInstrutor int - É o número da chave extrangeira de um instrutor.
    * @return int[] - O retorno é um vetor que contém os números de identificação
    * dos tipos de saltos que o instrutor realiza.
    */
     public int[] getIdTiposDeSaltosPorInstrutor(int idInstrutor){        
        ResultSet rs;
        int taxas[] = new int[contarTaxasDeSobrePesoArmazenadas()];
        try{ 
            PreparedStatement ppStmt =  conn.prepareStatement("SELECT idtipodesalto FROM instrutor_has_tipodesalto WHERE idinstrutor = ?");
            ppStmt.setInt(1,idInstrutor);
            rs = ppStmt.executeQuery();
            for(int i = 0; rs.next(); i++ ){                
                taxas[i]=rs.getInt("idtipodesalto");
            }
            ppStmt.close();
	    rs.close(); 
        }       
        catch(SQLException ex){         
            ex.printStackTrace();
        }       
        return taxas;
    }
    
    public List<TipoDeSaltoTO> getTiposDeSaltosPorInstrutor(int idInstrutor){
            List<TipoDeSaltoTO> list = new LinkedList<TipoDeSaltoTO>();
            ResultSet rs;
            int [] idTiposdeSaltos = getIdTiposDeSaltosPorInstrutor(idInstrutor);
            for( int i = 0; i < idTiposdeSaltos.length; i++){
                try{
                    PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM tipodesalto WHERE idtipodesalto = ?");
                    ppStmt.setInt(1,idTiposdeSaltos[i]);
                    rs = ppStmt.executeQuery();
                    while(rs.next()){
                        list.add(getTipoDeSalto(rs));                        
                    }
                    ppStmt.close();
                    rs.close(); 
                }
                catch(SQLException ex){
                    ex.printStackTrace();
                }
            }            
            return list;
    }
    
    /** 
    * Método para buscar os instrutores que executam um determinado tipo de salto.
    * Este método recebe a identificação de um tipo de salto e faz uma busca no banco
    * por quais instrutores o realizam. Ele retorna uma lista com os instrutores.
    * @param idTipodeSalto int - É o número de identificação de um tipo de salto.
    * @return List - Uma lista com intrutores.
    */
     public List<InstrutorTO> getInstrutoresPorTiposDeSaltos(int idTipodeSalto){
        System.out.println(idTipodeSalto);
        List<InstrutorTO> list = new LinkedList<>();
        ResultSet rs;
        try{ 
            PreparedStatement ppStmt =  conn.prepareStatement("SELECT i.idinstrutor, i.nome, i.cpf, i.admissao, i.presenca FROM instrutor i JOIN instrutor_has_tipodesalto a ON a.idinstrutor = i.idinstrutor AND a.idtipodesalto = ?");
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

    public InstrutorDAO getiDao() {
        return iDao;
    }

    public void setiDao(InstrutorDAO iDao) {
        this.iDao = iDao;
    }
     
     
}
