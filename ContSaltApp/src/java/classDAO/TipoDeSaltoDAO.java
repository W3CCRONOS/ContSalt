/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classDAO;

import classTO.TipoDeSaltoTO;
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
public class TipoDeSaltoDAO {
        Connection conn;

    public TipoDeSaltoDAO() {
       conn = new Conexao().conectar();      
    }
        
    public void salvar(TipoDeSaltoTO tipodesalto){
 
        try{
            PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO tipodesalto (nome, valor) VALUES (?,?)");
            ppStmt.setString(1,tipodesalto.getNome());
            ppStmt.setDouble(2,tipodesalto.getValor());
            ppStmt.execute();
            System.out.println("Cadastrou");
        }
        
        catch(SQLException ex){         
            ex.printStackTrace();
        }
    }
     public void alterar(TipoDeSaltoTO tipodesalto){
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE tipodesalto SET nome =?, valor=? WHERE idtipodesalto=?");
            stmt.setString(1, tipodesalto.getNome());
            stmt.setDouble(2, tipodesalto.getValor());
            stmt.setInt(3,tipodesalto.getIdTipoDeSalto());
            stmt.execute();
            System.out.println("Alterado");
        }catch(SQLException EX){
             EX.printStackTrace();
        }        
    }
    
    public List<TipoDeSaltoTO> getTiposDeSaltos(){
            
            List<TipoDeSaltoTO> lstA = new LinkedList<TipoDeSaltoTO>();
            ResultSet rs;
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM tipodesalto");
                rs = ppStmt.executeQuery();
                while(rs.next()){
                    lstA.add(getTipoDeSalto(rs));
                }
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
            return lstA;
    }
    
    private TipoDeSaltoTO getTipoDeSalto(ResultSet rs) throws SQLException{
        TipoDeSaltoTO c = new TipoDeSaltoTO();
        c.setNome(rs.getString("nome"));
        c.setValor(rs.getDouble("valor"));
        c.setIdTipoDeSalto(rs.getInt("idtipodesalto"));            
        return c;
    }
    public void excluir(TipoDeSaltoTO c){
           try {
               PreparedStatement ppStmt = conn.prepareStatement("DELETE FROM tipodesalto WHERE idtipodesalto=?");
               ppStmt.setInt(1,c.getIdTipoDeSalto());
               ppStmt.execute();
               System.out.println("Excluido");
           }catch(SQLException EX){
               EX.printStackTrace();
           }
    }
}
