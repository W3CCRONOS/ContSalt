
package classDAO;

import classTO.InstrutorTO;
import classTO.TaxaSobrepesoTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import utilitarios.Conexao;

/**
 * Classe para acessar os dados das taxas de sobrepeso e instrutores.
 * @author Almir
 * @version 1.0
 */
public class TaxasInstrutoresDAO {

    Connection conn;
    
    private InstrutorDAO iDao;
    private TaxaSobrepesoDAO taxaDao;
    
    public TaxasInstrutoresDAO() {
        conn = new Conexao().conectar();
        this.setiDao(new InstrutorDAO());
        this.setTaxaDao(new TaxaSobrepesoDAO());        
    }
    
    /**
    * O método para salvar registros no banco.
    * Ele verifica se não há um registro especifico de relacionamento, entre uma taxa de sobrepeso e um instrutor.
    * Se não houver, ele cria.
    * @param idInstrutor int - número da chave extrangeira de um instrutor.
    * @param idTaxaSobrepeso int - número da chave extrangeira da taxa de sobrepeso.
    */   
    public void salvarTaxaDoInstrutor(int idInstrutor, int idTaxaSobrepeso){
        /*Verifica se o registro ainda não existe para então salvá-lo*/
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
    * Método para excluir registro no banco.
    *O método verifica se existe um registro de relacionamento de uma taxa de sobrepeso ligada ao instrutor. Se houver, ele o exclui.
    * @param idInstrutor int - É o número da chave extrangeira de um instrutor.
    * @param idTaxaSobrepeso int - É o número da chave extrangeira de um tipo de salto.
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
    * Método para verificar se existe registro de um determinado instrutor com uma taxa especifica.
    * @param idInstrutor int - É o número da chave extrangeira de um instrutor.
    * @param idTaxaSobrepeso int - É o número da chave extrangeira de uma taxa de sobrepeso.
    * @return total int - Retorna 0(zero) para ausência de registro ou 1(um) se houver registro.    
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
    * Método para buscar os instrutores que trabalham com uma determinada taxa de sobrepeso.
    * @param idTaxa int - É o número de identificação de uma taxa de sobrepeso.
    * @return List - Uma lista com intrutores.
    */
     public List<InstrutorTO> getInstrutoresPorTipoDeSalto(int idTaxa){
        List<InstrutorTO> list = new LinkedList<>();
        ResultSet rs;
        try{ 
            PreparedStatement ppStmt =  conn.prepareStatement("SELECT i.idinstrutor, i.nome, i.cpf, i.admissao, i.presenca FROM instrutor i JOIN instrutor_has_taxasobrepeso a ON a.idinstrutor = i.idinstrutor AND a.idtaxadesobrepeso = ?");
            ppStmt.setInt(1,idTaxa);
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
    * Método para buscar as taxas de sobrepeso que um determinado instrutor pode executar.
    * @param idInstrutor int - É o número de identificação de um instrutor.
    * @return List - Uma lista com taxas de sobrepeso.
    */     
    public List<TaxaSobrepesoTO> getTaxasDeSobrepesoPorInstrutor(int idInstrutor){
            List<TaxaSobrepesoTO> list = new LinkedList<>();
            ResultSet rs;
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT i.idtaxasobrepeso, i.valor, i.peso FROM taxasobrepeso i JOIN instrutor_has_taxasobrepeso a ON a.idtipodesalto = i.idtipodesalto AND a.idinstrutor = ?");
                ppStmt.setInt(1,idInstrutor);
                rs = ppStmt.executeQuery();
                while(rs.next()){
                    list.add(taxaDao.getTaxaSobrepeso(rs));                        
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

    public TaxaSobrepesoDAO getTaxaDao() {
        return taxaDao;
    }

    public void setTaxaDao(TaxaSobrepesoDAO taxaDao) {
        this.taxaDao = taxaDao;
    }
    
    
}
