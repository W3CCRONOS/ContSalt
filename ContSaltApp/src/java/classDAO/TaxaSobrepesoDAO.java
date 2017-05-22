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
            PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO taxasobrepeso (valor,codigo,descricao) VALUES (?,?,?)");
            ppStmt.setDouble(1,taxa.getValor());
            ppStmt.setInt(2,taxa.getCodigo());
            ppStmt.setString(3,taxa.getDescricao());
            System.out.println(taxa.getCodigo());
            System.out.println(taxa.getDescricao());
            System.out.println(taxa.getValor());
            ppStmt.execute();
            ppStmt.close();
        }
        
        catch(SQLException ex){         
            ex.printStackTrace();
        }
    }
     public void alterar(TaxaSobrepesoTO taxa){
        try {
            PreparedStatement ppStmt = conn.prepareStatement("UPDATE taxasobrepeso SET valor =?, codigo =?, descricao =? WHERE idtaxasobrepeso =?");
            ppStmt.setDouble(1, taxa.getValor());
            ppStmt.setInt(2,taxa.getCodigo());
            ppStmt.setString(3,taxa.getDescricao());
            ppStmt.setInt(4, taxa.getIdTaxaSobrepeso());
            ppStmt.execute();
            ppStmt.close(); 
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
                ppStmt.close();
                rs.close(); 
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
            return lstA;
    }
    
    protected TaxaSobrepesoTO getTaxaSobrepeso(ResultSet rs) throws SQLException{
        TaxaSobrepesoTO t = new TaxaSobrepesoTO();
        t.setValor(rs.getDouble("valor"));
        t.setIdTaxaSobrepeso(rs.getInt("idtaxasobrepeso"));    
        t.setCodigo(rs.getInt("codigo"));
        t.setDescricao(rs.getString("descricao"));
        return t;
    }
    
    public void excluir(TaxaSobrepesoTO t){
        try{
            PreparedStatement ppStmt = conn.prepareStatement("DELETE FROM taxasobrepeso WHERE idtaxasobrepeso=?");
            ppStmt.setInt(1,t.getIdTaxaSobrepeso());
            ppStmt.execute();
            ppStmt.close();
        }
        catch(SQLException EX){
            EX.printStackTrace();
        }
    }
    /** 
    * Método para contar quantas taxas de sobre peso estão registradas no banco.
    * @return int - Valor com o total de registro de taxas de sobrepeso armazenados
    * no banco.
    */
    public int contarTaxasDeSobrePesoArmazenadas(){
        int total=0;
        ResultSet rs;
        try{
            PreparedStatement ppStmt =  conn.prepareStatement("SELECT COUNT(idtaxasobrepeso) AS quantidadeDeRegistros FROM taxasobrepeso");
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
