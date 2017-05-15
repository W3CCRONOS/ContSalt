
package classDAO;

import classTO.InstrutorTO;
import classTO.TaxaSobrepesoTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilitarios.Conexao;

/**
 *Classe que conecta ao banco de dados para criar ou excluir os registros das taxas de sobre peso que os instrutores realizam.
 * @author Almir
 * @version 1.0
 * @see TaxaSobrepesoDAO
 */
public class TaxasInstrutoresDAO extends TaxaSobrepesoDAO{

    public TaxasInstrutoresDAO() {
        conn = new Conexao().conectar();
    }
      
      
     
 /**
 *Método para salvar registros no banco. Ele verifica se já existe uma determinda taxa de sobrepeso ligada a um instrutor específico.
 * Se não hover, ele cria este registro.
 * @param idInstrutor int - número da chave extrangeira de um instrutor.
 * @param idTaxaSobrepeso int - número da chave extrangeira de uma taxa de sobrepeso.
 */    
    public void salvarTaxaDoInstrutor(int idInstrutor, int idTaxaSobrepeso){
        int total = 0;
        ResultSet rs;
        try{ 
            /*Esse SELECT verifica se já exixte um registro com os parametros pasado para
             o método*/
            PreparedStatement ppStmt1 =  conn.prepareStatement("SELECT COUNT(instrutor_idinstrutor) AS quantidadeDeRegistros FROM taxasobrepeso_has_instrutor WHERE instrutor_idinstrutor = ? AND taxasobrepeso_idtaxasobrepeso = ? ");
            ppStmt1.setInt(1,idInstrutor);
            ppStmt1.setInt(2,idTaxaSobrepeso);
            rs = ppStmt1.executeQuery();
            if(rs.next()){  // Se  rs.next() for true...(existe algum registro dentro do ResuslSet)           
                total = rs.getInt("quantidadeDeRegistros"); // total   recebe o valor do ResultSet   
                /*Se não existe um registro com os parametros informados para
                    o método, então, o registro será criado*/
                if (total == 0){                    
                    try{
                        PreparedStatement ppStmt2 =  conn.prepareStatement("INSERT INTO taxasobrepeso_has_instrutor (instrutor_idinstrutor,taxasobrepeso_idtaxasobrepeso) VALUES (?,?)");
                        ppStmt2.setInt(1,idInstrutor);
                        ppStmt2.setInt(2,idTaxaSobrepeso);
                        ppStmt2.execute();
                        ppStmt2.close();
                    }
                    catch(SQLException ex){         
                        ex.printStackTrace();
                    }                
                } 
            }
            ppStmt1.close();
	    rs.close();                    
        }       
        catch(SQLException ex){         
            ex.printStackTrace();
        }
    }
    
    /** 
    * Método para excluir registros no banco. Ele verifica se existe um determinado registro de taxa de sobrepeso liagdo a um instrutor. Se houver
    * ele o exclui.
    * @param idInstrutor int - número da chave extrangeira de um instrutor.
    * @param idTaxaSobrepeso int - número da chave extrangeira de uma taxa de sobrepeso.
    */
    public void ExcluirTaxaDoInstrutor(int idInstrutor, int idTaxaSobrepeso){
        int total = 0;
        ResultSet rs;
        try{ 
            /*Esse SELECT verifica se já existe um registro com os parametros pasado para
             o método*/
            PreparedStatement ppStmt1 =  conn.prepareStatement("SELECT COUNT(instrutor_idinstrutor) AS quantidadeDeRegistros FROM taxasobrepeso_has_instrutor WHERE instrutor_idinstrutor = ? AND taxasobrepeso_idtaxasobrepeso = ? ");
            ppStmt1.setInt(1,idInstrutor);
            ppStmt1.setInt(2,idTaxaSobrepeso);
            rs = ppStmt1.executeQuery();
            if(rs.next()){  // Se  rs.next() for true...(existe algum registro dentro do ResuslSet)            
                total = rs.getInt("quantidadeDeRegistros"); // total   recebe o valor do ResultSet   
                /*Se existe um registro com os parametros informados para
                    o método, então, o registro será excluido*/
                if (total != 0){
                    try{
                        PreparedStatement ppStmt2 =  conn.prepareStatement("DELETE FROM taxasobrepeso_has_instrutor WHERE instrutor_idinstrutor = ? AND taxasobrepeso_idtaxasobrepeso = ?");
                        ppStmt2.setInt(1,idInstrutor);
                        ppStmt2.setInt(2,idTaxaSobrepeso);
                        ppStmt2.execute();
                        ppStmt2.close();
                    }
                    catch(SQLException ex){         
                        ex.printStackTrace();
                    }                
                } 
            }
            ppStmt1.close();
	    rs.close();                    
        }       
        catch(SQLException ex){         
            ex.printStackTrace();
        }
    
    }
    
    /** 
    * Método para contar quantas taxas de sobre peso estão registradas no banco.
    * @return int - Valor com o total de registro de taxas de sobrepeso armazenados
    * no banco.
    */
    public int ContarTaxasArmazenadas(){
        int total=0;
        ResultSet rs;
        try{
            PreparedStatement ppStmt =  conn.prepareStatement("SELECT COUNT(idtaxasobrepeso) AS quantidadeDeRegistros FROM taxasobrepeso");
            rs = ppStmt.executeQuery();
            if(rs.next()){
               total = rs.getInt("quantidadeDeRegistros");
            }
	    rs.close();
        }        
        catch(SQLException ex){         
        ex.printStackTrace();        
        }
       return total;        
    }       
}   
 