
package classDAO;

import classTO.TaxaSobrepesoTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import utilitarios.Conexao;

/**
 *Classe que conecta ao banco de dados para criar ou excluir os registros das taxas de sobre peso que os instrutores realizam.
 * @author Almir
 * @version 1.0
 */
public class TaxasInstrutoresDAO extends TaxaSobrepesoDAO{

    public TaxasInstrutoresDAO() {
        conn = new Conexao().conectar();
    }
    
 /**
 *Método para salvar registros no banco. Este metodo faz uma verificação se não existe
 * registro da taxa de sobrepeso  ligado ao instrutor. Se não houver um registro ele salva no banco.
 * @param idInstrutor int - número da chave extrangeira de um instrutor.
 * @param idTaxaSobrepeso int - número da chave extrangeira de uma taxa de sobrepeso.
 */    
    public void salvarTaxaDoInstrutor(int idInstrutor, int idTaxaSobrepeso){
        /*Verifico se o registro ainda não existe para então salvá-lo*/
        if (verificarSeExisteRegistro(idInstrutor, idTaxaSobrepeso) == 0){                    
            try{
               PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO instrutor_has_taxasobrepeso (idinstrutor,idtaxadesobrepeso) VALUES (?,?)");
                ppStmt.setInt(1,idInstrutor);
                ppStmt.setInt(2,idTaxaSobrepeso);
                ppStmt.execute();
                ppStmt.close();
            }
            catch(SQLException ex){         
                ex.printStackTrace();
            }                
        } 
    }
  
    /** 
    * Método para excluir registros no banco. Este método faz uma verificação se existe
    * registro da taxa de sobrepeso  ligado ao instrutor. Se houver um registro ele exclui do banco.
    * @param idInstrutor int - É o número da chave extrangeira de um instrutor.
    * @param idTaxaSobrepeso int - É o número da chave extrangeira de uma taxa de sobrepeso.
    */
    public void excluirTaxaDoInstrutor(int idInstrutor, int idTaxaSobrepeso){
        /*Verifico se o registro existe para depois deleta-lo*/
        if (verificarSeExisteRegistro(idInstrutor, idTaxaSobrepeso) != 0){
            try{
                PreparedStatement ppStmt =  conn.prepareStatement("DELETE FROM instrutor_has_taxasobrepeso WHERE idinstrutor = ? AND idtaxadesobrepeso = ?");
                ppStmt.setInt(1,idInstrutor);
                ppStmt.setInt(2,idTaxaSobrepeso);
                ppStmt.execute();
                ppStmt.close();
            }
            catch(SQLException ex){         
                ex.printStackTrace();
            }                
        } 
    }
    
    /** 
    * Método para verificar se exite um registros no banco do instrutor com uma taxa de sobrepeso.
    * Se existir um registro, taxa de sobrepeso ligado a um instrutor,
    * este método retornará um valor diferente de zero.
    * @param idInstrutor int - É o número da chave extrangeira de um instrutor.
    * @param idTaxaSobrepeso int - É o número da chave extrangeira de uma taxa de sobre peso.
    * @return total int - Se não for encontrado nenhum registro retornará zero.    
    */
    private int verificarSeExisteRegistro(int idInstrutor, int idTaxaSobrepeso){
        int total = 0;
        ResultSet rs;
        try{ 
            PreparedStatement ppStmt =  conn.prepareStatement("SELECT COUNT(idinstrutor) AS quantidadeDeRegistros FROM instrutor_has_taxasobrepeso WHERE idinstrutor = ? AND idtaxadesobrepeso = ? ");
            ppStmt.setInt(1,idInstrutor);
            ppStmt.setInt(2,idTaxaSobrepeso);
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
    * Método para buscar as taxas de sobre peso que um instrutor realizar.
    * Este método recebe o número de identificação de um instrutor e faz uma busca 
    * sobre quais taxas de sobre peso o instrutor está  cadastrado para fazer.
    * @param idInstrutor int - É o número da chave extrangeira de um instrutor.
    * @return int[] - O retorno é um vetor que contém os números de identificação
    * das taxas de sobre peso que o instrutor realiza.
    */
     public int[] getIdTaxasPorInstrutor(int idInstrutor){        
        ResultSet rs;
        int taxas[] = new int[contarTaxasDeSobrePesoArmazenadas()];
        try{ 
            PreparedStatement ppStmt =  conn.prepareStatement("SELECT idtaxadesobrepeso FROM instrutor_has_taxasobrepeso WHERE idinstrutor = ?");
            ppStmt.setInt(1,idInstrutor);
            rs = ppStmt.executeQuery();
            for(int i = 0; rs.next(); i++ ){                
                taxas[i]=rs.getInt("idtaxadesobrepeso");
            }
            ppStmt.close();
	    rs.close(); 
        }       
        catch(SQLException ex){         
            ex.printStackTrace();
        }       
        return taxas;
    }
    
    public List<TaxaSobrepesoTO> getTaxasDeSobrepesoPorInstrutor(int idInstrutor){
            List<TaxaSobrepesoTO> list = new LinkedList<TaxaSobrepesoTO>();
            ResultSet rs;
            int [] idTaxas = getIdTaxasPorInstrutor(idInstrutor);
            for( int i = 0; i < idTaxas.length; i++){
                try{
                    PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM taxasobrepeso WHERE idtaxasobrepeso = ?");
                    ppStmt.setInt(1,idTaxas[i]);
                    rs = ppStmt.executeQuery();
                    while(rs.next()){
                        list.add(getTaxaSobrepeso(rs));
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
}
