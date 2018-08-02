package classDAO;

import classTO.DecolagemTO;
import classTO.InstrutorTO;
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
 * Classe que mantém os registros no banco de dados.
 * A classe mantém os registros dos clientes.
 * @author Almir
 * @version 1.0
 * @see SaltoTO
 * @see Conexao
 */

public class SaltoDAO {
     Connection conn;

    /**
    * Método construtor. 
    * No método inicializa a variável, realizando a conexão com o banco de dados. 
    */
    public SaltoDAO() {
        this.conn =  new Conexao().conectar();
    }
    
    /**
    * Método para criar um registros. 
    * O método salva o registro de um salto no banco de dados.
    * @param salt - salto que será registrado no banco.
    */
     public void salvar(SaltoTO salt){ 
        try{
            PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO salto (idcliente,idtaxasobrepeso,idtipodesalto,idinstrutor,iddecolagem) VALUES (?,?,?,?,?)");
            ppStmt.setInt(1, salt.getIdCliente());             
            ppStmt.setInt(2, salt.getIdTaxaDeSobrepeso());
            ppStmt.setInt(3, salt.getIdTipoDeSalto());
            ppStmt.setInt(4, salt.getIdInstrutor());
            ppStmt.setInt(5,salt.getIdDecolagem());
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
    * Método de busca. 
    * O método busca todos os saltos relalizados em uma determinada data.
    * @param dataDoSalto - data referente a busca.
    * @return List - o retorno é uma lista com saltos.
    */    
    public List<SaltoTO> getSaltos(){
            
            List<SaltoTO> lstA = new LinkedList<SaltoTO>();
            ResultSet rs;
            
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM salto");
                
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
    * Método de busca. 
    * O método busca todos os saltos em uma decolagem específica.
    * @param decolagem - Id da decolagem que se realizará a busca.
    * @return List - o retorno é uma lista com saltos.
    */    
    public List<SaltoTO> getSaltosPorDecolagem(DecolagemTO decolagem){
            
            List<SaltoTO> lstA = new LinkedList<SaltoTO>();
            ResultSet rs;
            
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM salto WHERE iddecolagem = ?");
                ppStmt.setInt(1,decolagem.getIddecolagem());
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
        salt.setIdInstrutor(rs.getInt("idinstrutor"));  
        salt.setIdCliente(rs.getInt("idcliente"));
        salt.setIdTaxaDeSobrepeso(rs.getInt("idtaxasobrepeso"));
        salt.setIdTipoDeSalto(rs.getInt("idtipodesalto"));
        salt.setIdTipoDeSalto(rs.getInt("iddecolagem"));
        return salt;
    }
    
    
     /** 
    * Método para verificar se existe registro.
    * O método verifica se existe algum registro de um salto numa decolagem específica.
    * @param decolagem - decolagem.
    * @return total int - Retorna 0(zero) para ausência de registro ou 1(um) se houver registro.    
    */
    public int verificarSeExisteRegistro(DecolagemTO decolagem){
        int total = 0;
        ResultSet rs;
        try{ 
            PreparedStatement ppStmt =  conn.prepareStatement("SELECT COUNT(idsalto) AS quantidadeDeRegistros FROM salto WHERE iddecolagem = ?");
            ppStmt.setInt(1,decolagem.getIddecolagem());
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
}
