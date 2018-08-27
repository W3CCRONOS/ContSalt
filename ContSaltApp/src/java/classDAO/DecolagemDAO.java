/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classDAO;

import classTO.DecolagemTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import utilitarios.Conexao;

/**
 * Classe que mantém os registros no banco de dados.
 * A classe mantém os registros das decolagens.
 * @author Almir
 * @version 1.0
 * @see DecolagemTO
 * @see Conexao
 */
public class DecolagemDAO {
    Connection conn;
    private DecolagemTO decoTO;
  
    /**
    * Método construtor. 
    * No método inicializa a variável, realizando a conexão com o banco de dados. 
    */
    public DecolagemDAO() {
        conn = new Conexao().conectar();
    }
    
    /**
    * Método para criar um registros. 
    * O método salva o registro de uma decolagem no banco de dados.
    * @param deco - decolagem que será registrado no banco.
    */
     public void salvar(DecolagemTO deco){ 
        try{
            PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO decolagem (numero,status,data) VALUES (?,?,?)");
            ppStmt.setInt(1,deco.getNumero());
            ppStmt.setString(2,deco.getStatus());
            ppStmt.setDate(3,deco.getData());
            ppStmt.execute();
            ppStmt.close();
        }       
        catch(SQLException ex){         
            ex.printStackTrace();
        }
    }
     
    /** 
    * Método de busca. 
    * O método busca as decolagens realizadas em um periodo.
    * @param dataInicial  - data inicial do periodo.
    * @param dataFinal  -   data final do epriodo
    * @return List -  lista com decolagens.
    */   
    public List<DecolagemTO> getDecolagensPorPeriodo(Date dataInicial,Date dataFinal){            
            List<DecolagemTO> lstA = new LinkedList<>();
            ResultSet rs;            
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM decolagem WHERE data BETWEEN ('"+dataInicial+"') AND ('"+dataFinal+"')");
                rs = ppStmt.executeQuery();
                while(rs.next()){
                    lstA.add(getDecolagem(rs));
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
    * O método busca os as decolagens realizadas em uma data
    * específica.
    * @param data - data das decolagens.
    * @return List - O retorno é uma lista com decolagens.
    */   
    public List<DecolagemTO> getDecolagens(Date data){            
            List<DecolagemTO> lstA = new LinkedList<>();
            ResultSet rs;            
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM decolagem WHERE data = ? ORDER BY numero");
                ppStmt.setDate(1,data);
                rs = ppStmt.executeQuery();
                while(rs.next()){
                    lstA.add(getDecolagem(rs));
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
    * Método para buscar a última decolagem registradas no banco.
    * @return List - O retorno é uma lista com decolagens.
    */    
    public List<DecolagemTO> getDecolagem(){            
            List<DecolagemTO> lstA = new LinkedList<>();
            ResultSet rs;            
            try{
                PreparedStatement ppStmt = conn.prepareStatement("select * from decolagem where iddecolagem = (select max(iddecolagem) from decolagem)");                             
                rs = ppStmt.executeQuery();
                while(rs.next()){
                    lstA.add(getDecolagem(rs));
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
    * Método para montar um objeto. 
    * O método recebe um resultado do banco e preenche os atributos de um objeto.
    * @param rs - é o resultado de uma solicitação feita ao banco de dados.
    * @return - o retorno é um objeto do tipo DecolagemTO.
    */  
    private DecolagemTO getDecolagem(ResultSet rs) throws SQLException{
        DecolagemTO decolagem = new DecolagemTO();
        decolagem.setIddecolagem(rs.getInt("iddecolagem"));
        decolagem.setNumero(rs.getInt("numero"));  
        decolagem.setStatus(rs.getString("status"));
        decolagem.setData(rs.getDate("data"));
        return decolagem;
    }
    
    /** 
    * Método de exclusão. 
    * O método exclui um registro do banco de dados..
    * @param c - decolagem que terá os registros excluídos do banco.
    */
    public void excluir(DecolagemTO c){
        try{
            PreparedStatement ppStmt = conn.prepareStatement("DELETE FROM decolagem WHERE iddecolagem=?");
            ppStmt.setInt(1,c.getIddecolagem());
            ppStmt.execute();
            ppStmt.close();
        }
        catch(SQLException EX){
            EX.printStackTrace();
        }
    }
    /** 
    * Método para alterar um registro. 
    * O método altera os registros de uma decolagem no banco de dados.
    * @param deco - decolagem que terá uma alteração no seu registro no banco.
    */  
     
     public void alterar(DecolagemTO deco){
        try {
            PreparedStatement ppStmt = conn.prepareStatement("UPDATE decolagem SET numero=?, status=? WHERE iddecolagem=?");
            ppStmt.setInt(1, deco.getIddecolagem());
            ppStmt.setString(2, deco.getStatus());
            ppStmt.setInt(3,deco.getIddecolagem());
            ppStmt.execute();
            ppStmt.close();
 
        }catch(SQLException EX){
             EX.printStackTrace();
        }        
    }
    
    public void aterarStatus(DecolagemTO i){
        try {
            PreparedStatement ppStmt = conn.prepareStatement("UPDATE decolagem SET status =? WHERE iddecolagem =?");
            ppStmt.setString(1,i.getStatus());
            ppStmt.setInt(2, i.getIddecolagem());
            ppStmt.execute();
            ppStmt.close();
        }catch(SQLException EX){
            EX.printStackTrace();
        }        
    }

    public DecolagemTO getDecoTO() {
        return decoTO;
    }

    public void setDecoTO(DecolagemTO decoTO) {
        this.decoTO = decoTO;
    }
     
}
