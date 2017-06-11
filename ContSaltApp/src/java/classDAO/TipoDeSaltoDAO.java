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
            PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO tipodesalto (nome,valor,taxadesobrepeso,pessoas) VALUES (?,?,?,?)");
            ppStmt.setString(1,tipodesalto.getNome());
            ppStmt.setDouble(2,tipodesalto.getValor());
            ppStmt.setString(3,tipodesalto.getTaxadesobrepeso());
            ppStmt.setInt(4,tipodesalto.getPessoas());
            ppStmt.execute();
            ppStmt.close(); 
        }
        
        catch(SQLException ex){         
            ex.printStackTrace();
        }
    }
     public void alterar(TipoDeSaltoTO tipodesalto){
        try {
            PreparedStatement ppStmt = conn.prepareStatement("UPDATE tipodesalto SET nome =?, valor=?, taxadesobrepeso=?, pessoas=? WHERE idtipodesalto=?");
            ppStmt.setString(1, tipodesalto.getNome());
            ppStmt.setDouble(2, tipodesalto.getValor());
            ppStmt.setString(3, tipodesalto.getTaxadesobrepeso());
            ppStmt.setInt(4,tipodesalto.getPessoas());
            ppStmt.setInt(5,tipodesalto.getIdTipoDeSalto());
            ppStmt.execute();
            ppStmt.close();
 
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
                ppStmt.close();
                rs.close();
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
            return lstA;
    }
    
    protected TipoDeSaltoTO getTipoDeSalto(ResultSet rs) throws SQLException{
        TipoDeSaltoTO tposalt = new TipoDeSaltoTO();
        tposalt.setNome(rs.getString("nome"));
        tposalt.setValor(rs.getDouble("valor"));
        tposalt.setTaxadesobrepeso(rs.getString("taxadesobrepeso"));
        tposalt.setPessoas(rs.getInt("pessoas"));
        tposalt.setIdTipoDeSalto(rs.getInt("idtipodesalto")); 
        return tposalt;
    }
    
    public void excluir(TipoDeSaltoTO c){
        try{
            PreparedStatement ppStmt = conn.prepareStatement("DELETE FROM tipodesalto WHERE idtipodesalto=?");
            ppStmt.setInt(1,c.getIdTipoDeSalto());
            ppStmt.execute();
            ppStmt.close();
        }
        catch(SQLException EX){
            EX.printStackTrace();
        }
    }
    /** 
    * Método para contar quantas tipos de saltos estão registradas no banco.
    * @return int - Valor com o total de registro armazenados no banco.
    */
    public int contarTaxasDeSobrePesoArmazenadas(){
        int total=0;
        ResultSet rs;
        try{
            PreparedStatement ppStmt =  conn.prepareStatement("SELECT COUNT(idtipodesalto) AS quantidadeDeRegistros FROM tipodesalto");
            rs = ppStmt.executeQuery();
            if(rs.next()){
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
