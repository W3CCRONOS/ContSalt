/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classDAO;

import classTO.TaxaSobrepesoTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import utilitarios.Conexao;

/**
 *
 * @author Skydive
 */
public class TaxaSobrepesoDAO {
            Connection conn;

    public TaxaSobrepesoDAO() {
       conn = new Conexao().conectar();      
    }
        
    public void salvar(TaxaSobrepesoTO taxa){
 
        try{
            PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO taxasobrepeso (valor) VALUES (?)");
            ppStmt.setDouble(1,taxa.getValor());
            ppStmt.execute();
            System.out.println("Cadastrou");
        }
        
        catch(SQLException ex){         
            ex.printStackTrace();
        }
    }
     public void alterar(TaxaSobrepesoTO taxa){
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE taxasobrepeso SET valor =?, WHERE idtaxasobrepeso=?");
            stmt.setDouble(1, taxa.getValor());
            stmt.setInt(2,taxa.getIdTaxaSobrepeso());
            stmt.execute();
            System.out.println("Alterado");
        }catch(SQLException EX){
             EX.printStackTrace();
        }        
    }
    
    public List<TaxaSobrepesoTO> getTaxasSobrepesos(){
            
            List<TaxaSobrepesoTO> lstA = new LinkedList<TaxaSobrepesoTO>();
            ResultSet rs;
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM taxasobrepeso");
                rs = ppStmt.executeQuery();
                while(rs.next()){
                    lstA.add(getTaxaSobrepeso(rs));
                }
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
            return lstA;
    }
    
    private TaxaSobrepesoTO getTaxaSobrepeso(ResultSet rs) throws SQLException{
        TaxaSobrepesoTO t = new TaxaSobrepesoTO();
        t.setValor(rs.getDouble("valor"));
        t.setIdTaxaSobrepeso(rs.getInt("idtaxasobrepeso"));            
        return t;
    }
    public void excluir(TaxaSobrepesoTO t){
           try {
               PreparedStatement ppStmt = conn.prepareStatement("DELETE FROM taxasobrepeso WHERE idaxasobrepeso=?");
               ppStmt.setInt(1,t.getIdTaxaSobrepeso());
               ppStmt.execute();
               System.out.println("Excluido");
           }catch(SQLException EX){
               EX.printStackTrace();
           }
    }
    
}
