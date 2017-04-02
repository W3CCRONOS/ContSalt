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
            PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO instrutor (nome,cpf,admissao,presenca) VALUES (?,?,?,?)");
            ppStmt.setString(1,i.getNome());
            ppStmt.setString(2,i.getCpf());
            ppStmt.setDate(3, i.getAdmissao());            
            ppStmt.setInt(4,i.getPresenca());
             System.out.println(i.getNome());
             System.out.println(i.getCpf());
            //System.out.println(i.getAdmissao());
            System.out.println(i.getPresenca());
            ppStmt.execute();
            System.out.println("Cadastrou");
        }
        
        catch(SQLException ex){         
            ex.printStackTrace();
        }
    }
     public void alterar(InstrutorTO i){
        try {
            PreparedStatement ppStmt = conn.prepareStatement("UPDATE instrutor SET nome =?, admissao =?, presenca =? WHERE idinstrutor =?");
            ppStmt.setString(1, i.getNome());
            ppStmt.setDate(2,i.getAdmissao());
            ppStmt.setInt(3,i.getPresenca());
            ppStmt.setInt(4, i.getIdInstrutor());
            ppStmt.execute();
            System.out.println("Alterado");
        }catch(SQLException EX){
             EX.printStackTrace();
        }        
    }
    
    public List<InstrutorTO> getIntrutores(){
            
            List<InstrutorTO> lstA = new LinkedList<InstrutorTO>();
            ResultSet rs;
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM instrutor");
                rs = ppStmt.executeQuery();
                while(rs.next()){
                    lstA.add(getIntrutor(rs));
                }
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
            return lstA;
    }
    
    private InstrutorTO getIntrutor(ResultSet rs) throws SQLException{
        InstrutorTO i = new InstrutorTO();
        i.setIdInstrutor(rs.getInt("idinstrutor"));
        i.setNome(rs.getString("nome"));            
        i.setAdmissao(rs.getDate("admissao"));
        i.setPresenca(rs.getInt("presenca"));
        return i;
    }
    public void excluir(InstrutorTO i){
           try {
               PreparedStatement ppStmt = conn.prepareStatement("DELETE FROM instrutor WHERE idinstrutor=?");
               ppStmt.setInt(1,i.getIdInstrutor());
               ppStmt.execute();
               System.out.println("Excluido");
           }catch(SQLException EX){
               EX.printStackTrace();
           }
    }
    
}
