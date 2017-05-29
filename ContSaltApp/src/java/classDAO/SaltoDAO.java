package classDAO;

import classTO.SaltoTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import utilitarios.Conexao;

/**
 *Classe que conecta-se ao banco de dados para criar, alterar, excluir e visualizar os registros dos saltos.
 * @author Almir
 * @version 1.0
 * @see Conexao
 */

public class SaltoDAO {
     Connection conn;

    public SaltoDAO() {
        this.conn =  new Conexao().conectar();
    }
    
    /**
    * Método para salvar registros no banco. Este método salva um novo salto
    * no banco.
    * @param salt SaltoTO - Objeto que representa um salto, que será registrado no banco.
    */
     public void salvar(SaltoTO salt){ 
        try{
            PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO salto (cliente_idcliente,data,taxasobrepeso_idtaxasobrepeso,tipodesalto_idtipodesalto,instrutor_idinstrutor) VALUES (?,?,?,?,?)");
            ppStmt.setInt(1, salt.getIdCliente());
            ppStmt.setDate(2,salt.getDataDoSalto()); 
            ppStmt.setInt(3, salt.getIdTaxaDeSobrepeso());
            ppStmt.setInt(4, salt.getIdTipoDeSalto());
            ppStmt.setInt(5, salt.getIdInstrutor());
            ppStmt.execute();
            ppStmt.close();
        }       
        catch(SQLException ex){         
            ex.printStackTrace();
        }
    }
     
    /**
    * Método para alterar registros no banco. Este método altera um salto
    *registrado no banco.
    * Todo salto é criado utilizando a data atual do sistema. Se houver
    *um UPDATE, a data do salto não será modificada.
    * @param salt SaltoTO - Objeto salto que terá o registrado no banco modificado.
    */
    public void alterar(SaltoTO salt){
        try {
            PreparedStatement ppStmt = conn.prepareStatement("UPDATE salto SET cliente_idcliente =?, taxasobrepeso_idtaxasobrepeso=?, tipodesalto_idtipodesalto=?, instrutor_idinstrutor=? WHERE idsalto =?");
            ppStmt.setInt(1, salt.getIdCliente());
            ppStmt.setInt(2, salt.getIdTaxaDeSobrepeso());
            ppStmt.setInt(3, salt.getIdTipoDeSalto());
            ppStmt.setInt(4, salt.getIdInstrutor());
            ppStmt.setInt(5, salt.getIdSalto());
            ppStmt.execute();
            ppStmt.close();
        }catch(SQLException EX){
            EX.printStackTrace();
        }        
    }

    /**
    * Método para excluir registros no banco. Este método exclui um salto
    *registrado no banco.
    * @param salt SaltoTO - Objeto salto que terá o registrado no banco excluído.
    */
    public void excluir(SaltoTO salt){
        try {
            PreparedStatement ppStmt = conn.prepareStatement("DELETE FROM salto WHERE idsalto=?");
            ppStmt.setInt(1,salt.getIdSalto());
            ppStmt.execute();
            ppStmt.close();
        }catch(SQLException EX){
            EX.printStackTrace();
        }
    }
    
    /** 
    * Método para buscar saltos. Este método busca todos os saltos relalizados
    * em uma determinada data.
    * @param dataDoSalto Date - É data referente a busca.
    * @return List - O retorno é uma lista de objetos do tipo SaltoTO.
    */    
    public List<SaltoTO> getSaltosPorData(Date dataDoSalto){
            
            List<SaltoTO> lstA = new LinkedList<SaltoTO>();
            ResultSet rs;
            
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM salto WHERE data = ?");
                ppStmt.setDate(1,dataDoSalto);
                rs = ppStmt.executeQuery();
                while(rs.next()){
                    lstA.add(getSalto(rs));
                }
                ppStmt.close();
	        rs.close();
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
            return lstA;
    }
    
    /** 
    * Método para montar um objeto salto. Este método recebe um resultado do banco
    * e preenche os atributos de um objeto salto.
    * @param rs ResultSet - É o resultado de uma solicitação feita ao banco de dados, referente a uma busca.
    * @return SaltoTO - O retorno é um objeto do tipo SaltoTO.
    */ 
    private SaltoTO getSalto(ResultSet rs) throws SQLException{
        SaltoTO salt = new SaltoTO();
        salt.setIdSalto(rs.getInt("idsalto"));
        salt.setIdInstrutor(rs.getInt("instrutor_idinstrutor"));  
        salt.setIdCliente(rs.getInt("cliente_idcliente"));
        salt.setIdTaxaDeSobrepeso(rs.getInt("taxasobrepeso_idtaxasobrepeso"));
        salt.setIdTipoDeSalto(rs.getInt("tipodesalto_idtipodesalto"));
        salt.setDataDoSalto(rs.getDate("data"));
        return salt;
    }
}
