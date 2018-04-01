/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classDAO;

import classTO.InstrutorTO;
import java.sql.Connection;
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
public class InstrutorDAO {
    Connection conn;
    public InstrutorDAO() {
       conn = new Conexao().conectar();      
    }
        
    public void salvar(InstrutorTO i){
 
        try{
            PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO instrutor (nome,cpf,admissao,presenca,peso) VALUES (?,?,?,?,?)");
            ppStmt.setString(1,i.getNome());
            ppStmt.setString(2,i.getCpf());
            ppStmt.setDate(3, i.getAdmissao());            
            ppStmt.setString(4,i.getPresenca());
            ppStmt.setDouble(5, i.getPeso());
            ppStmt.execute();
            ppStmt.close();
        }
        
        catch(SQLException ex){         
            ex.printStackTrace();
        }
    }
    public void alterar(InstrutorTO i){
        try {
            PreparedStatement ppStmt = conn.prepareStatement("UPDATE instrutor SET nome =?, cpf =?, admissao =?, presenca =?, peso =? WHERE idinstrutor =?");
            ppStmt.setString(1, i.getNome());
            ppStmt.setString(2, i.getCpf());
            ppStmt.setDate(3,i.getAdmissao());
            ppStmt.setString(4,i.getPresenca());
            ppStmt.setDouble(5, i.getPeso());
            ppStmt.setInt(6, i.getIdInstrutor());
            ppStmt.execute();
            ppStmt.close();
        }catch(SQLException EX){
            EX.printStackTrace();
        }        
    }
     
    public void presenca(InstrutorTO i){
        try {
            PreparedStatement ppStmt = conn.prepareStatement("UPDATE instrutor SET presenca =? WHERE idinstrutor =?");
            ppStmt.setString(1,i.getPresenca());
            ppStmt.setInt(2, i.getIdInstrutor());
            ppStmt.execute();
            ppStmt.close();
        }catch(SQLException EX){
            EX.printStackTrace();
        }        
    }
    /** 
    * Método para buscar os instrutores.
    * @return List - Uma lista com intrutores.
    */
    
    public List<InstrutorTO> getInstrutores(){
 
            List<InstrutorTO> lstA = new LinkedList<InstrutorTO>();
            ResultSet rs;
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM instrutor ORDER BY nome");
                rs = ppStmt.executeQuery();
                while(rs.next()){
                    lstA.add(getIntrutor(rs));
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
    * Método para buscar os instrutores que estão em uma decolagem.
    * @param iddecolagem int - É o número de identificação de uma decolagem.
    * @return List - Uma lista com intrutores.
    */
    public List<InstrutorTO> getInstrutoresDecolagem(int iddecolagem){
 
            List<InstrutorTO> lstA = new LinkedList<>();
            ResultSet rs;
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT i.idinstrutor, i.nome, i.cpf, i.admissao, i.presenca, i.peso FROM instrutor i\n" +
                                                                 "INNER JOIN salto s ON s.idinstrutor = i.idinstrutor\n" +
                                                                 "INNER JOIN decolagem d ON s.iddecolagem = ? ORDER BY admissao");
                ppStmt.setInt(1,iddecolagem);
                rs = ppStmt.executeQuery();
                while(rs.next()){
                    lstA.add(getIntrutor(rs));
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
    * Método para montar um objeto do tipo instrutor.
    * @return InstrutorTO - Um intrutor.
    */
    public InstrutorTO getIntrutor(ResultSet rs) throws SQLException{
        InstrutorTO i = new InstrutorTO();
        i.setIdInstrutor(rs.getInt("idinstrutor"));
        i.setNome(rs.getString("nome"));  
        i.setCpf(rs.getString("cpf"));
        i.setAdmissao(rs.getDate("admissao"));
        i.setPresenca(rs.getString("presenca"));
         i.setPeso(rs.getDouble("peso"));
        return i;
    }
    public void excluir(InstrutorTO i){
           try {
               PreparedStatement ppStmt = conn.prepareStatement("DELETE FROM instrutor WHERE idinstrutor=?");
               ppStmt.setInt(1,i.getIdInstrutor());
               ppStmt.execute();
               ppStmt.close();
           }catch(SQLException EX){
               EX.printStackTrace();
           }
    }  
 }
