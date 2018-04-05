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
import java.util.LinkedList;
import java.util.List;
import utilitarios.Conexao;

/**
 *Classe que conecta-se ao banco de dados para acessar os registros das decolagens.
 * @author Almir
 * @version 1.0
 * @see DecolagemTO
 * @see Conexao
 */
public class DecolagemDAO {
    Connection conn;
    private DecolagemTO decoTO;

    public DecolagemDAO() {
        conn = new Conexao().conectar();
        this.setDecoTO(new DecolagemTO());
    }
    /**
    * Método para salvar registros no banco. Este método salva uma nova decolagem
    * no banco.
    * @param deco DecolagemTO - Objeto que representa uma decolagem.
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
    * Método para buscar todas as decolagens registradas no banco.
    * @param deco Date - data.
    * @return List - O retorno é uma lista com decolagens.
    */    
    public List<DecolagemTO> getDecolagens(Date deco){            
            List<DecolagemTO> lstA = new LinkedList<>();
            ResultSet rs;            
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM decolagem WHERE data = ?");
                ppStmt.setDate(1,deco);
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
    * Método para montar um objeto decolagem. Este método recebe um resultado do banco
    * e preenche os atributos de um objeto.
    * @param rs ResultSet - É o resultado de uma solicitação feita ao banco de dados, referente a uma busca.
    * @return DecolagemTO - O retorno é um objeto do tipo ClienteTO.
    */ 
    private DecolagemTO getDecolagem(ResultSet rs) throws SQLException{
        DecolagemTO decolagem = new DecolagemTO();
        decolagem.setIddecolagem(rs.getInt("iddecolagem"));
        decolagem.setNumero(rs.getInt("numero"));  
        decolagem.setStatus(rs.getString("status"));
        decolagem.setData(rs.getDate("data"));
        return decolagem;
    }
    
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
    * Método para fazer alterações em uma decolagem. 
    * @param deco DecolagemTO - Objeto que representa uma decolagem.
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
