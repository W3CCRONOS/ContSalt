/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classDAO;

import classTO.TipoDeSaltoTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import utilitarios.Conexao;

/**
 *
 * @author Almir
 */
public class TiposDeSaltosInstrutoresDAO extends TipoDeSaltoDAO{

    public TiposDeSaltosInstrutoresDAO() {
        conn = new Conexao().conectar();
    }
     /**
 *Método para salvar registros no banco. Este metodo faz uma verificação se não existe
 * registro de tipos de saltos ligados ao instrutor. Se não houver um registro ele salva no banco.
 * @param idInstrutor int - número da chave extrangeira de um instrutor.
 * @param idTipoDeSalto int - número da chave extrangeira de um tipo de salto.
 */    
    public void salvarTipoDeSaltoDoInstrutor(int idInstrutor, int idTipoDeSalto){
        /*Verifico se o registro ainda não existe para então salvá-lo*/
        if (verificarSeExisteRegistro(idInstrutor, idTipoDeSalto) == 0){                    
            try{
               PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO instrutor_has_tipodesalto (instrutor_idinstrutor,tipodesalto_idtipodesalto) VALUES (?,?)");
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
    * Método para excluir registros no banco. Este método faz uma verificação se existe
    * registros de tipo de saltos  ligados ao instrutor. Se houver um registro ele exclui do banco.
    * @param idInstrutor int - É o número da chave extrangeira de um instrutor.
    * @param idTipoDeSalto int - É o número da chave extrangeira de um tipo de salto.
    */
    public void excluirTipoDeSaltoDoInstrutor(int idInstrutor, int idTipoDeSalto){
        /*Verifico se o registro existe para depois deleta-lo*/
        if (verificarSeExisteRegistro(idInstrutor, idTipoDeSalto) != 0){
            try{
                PreparedStatement ppStmt =  conn.prepareStatement("DELETE FROM instrutor_has_tipodesalto WHERE instrutor_idinstrutor = ? AND tipodesalto_idtipodesalto = ?");
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
    * Se existir um registro, tipo de salto ligado a um instrutor,
    * este método retornará um valor diferente de zero.
    * @param idInstrutor int - É o número da chave extrangeira de um instrutor.
    * @param idTipoDeSalto int - É o número da chave extrangeira de uma tipo de salto.
    * @return total int - Se não for encontrado nenhum registro retornará zero.    
    */
    private int verificarSeExisteRegistro(int idInstrutor, int idTipoDeSalto){
        int total = 0;
        ResultSet rs;
        try{ 
            PreparedStatement ppStmt =  conn.prepareStatement("SELECT COUNT(instrutor_idinstrutor) AS quantidadeDeRegistros FROM instrutor_has_tipodesalto WHERE instrutor_idinstrutor = ? AND tipodesalto_idtipodesalto = ? ");
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
    * Este método recebe o número de identificação de um instrutor e faz uma busca 
    * sobre quais tipos de saltos o instrutor está cadastrado para fazer.
    * @param idInstrutor int - É o número da chave extrangeira de um instrutor.
    * @return int[] - O retorno é um vetor que contém os números de identificação
    * dos tipos de saltos que o instrutor realiza.
    */
     public int[] getIdTiposDeSaltosPorInstrutor(int idInstrutor){        
        ResultSet rs;
        int taxas[] = new int[contarTaxasDeSobrePesoArmazenadas()];
        try{ 
            PreparedStatement ppStmt =  conn.prepareStatement("SELECT tipodesalto_idtipodesalto FROM instrutor_has_tipodesalto WHERE instrutor_idinstrutor = ?");
            ppStmt.setInt(1,idInstrutor);
            rs = ppStmt.executeQuery();
            for(int i = 0; rs.next(); i++ ){                
                taxas[i]=rs.getInt("tipodesalto_idtipodesalto");
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
}
