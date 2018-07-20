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
 * Classe que mantém os registros no banco de dados.
 * A classe mantém os registros dos clientes.
 * @author Almir
 * @version 1.0
 * @see TaxaSobrepesoTO
 * @see Conexao
 */
public class TaxaSobrepesoDAO {
            Connection conn;

    /**
    * Método construtor. 
    * No método inicializa a variável, realizando a conexão com o banco de dados. 
    */
    public TaxaSobrepesoDAO() {
       conn = new Conexao().conectar();      
    }
    
    /**
    * Método para criar um registros. 
    * O método salva o registro de uma taxa de sobrepeso no banco de dados.
    * @param taxa - taxa de sobrepeso que será registrado no banco.
    */
    public void salvar(TaxaSobrepesoTO taxa){
 
        try{
            PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO taxasobrepeso (valor, pesomin, pesomax) VALUES (?,?,?)");
            ppStmt.setDouble(1,taxa.getValor());
            ppStmt.setDouble(2,taxa.getPesoMin());
            ppStmt.setDouble(3,taxa.getPesoMax());
            ppStmt.execute();
            ppStmt.close();
        }        
        catch(SQLException ex){         
            ex.printStackTrace();
        }
    }
    
    /** 
    * Método para alterar um registro. 
    * O método altera os registros de uma taxa de sobrepeso no banco de dados.
    * @param taxa - taxa de sobrepeso que terá uma alteração no seu registro no banco.
    */
     public void alterar(TaxaSobrepesoTO taxa){
        try {
            PreparedStatement ppStmt = conn.prepareStatement("UPDATE taxasobrepeso SET valor =?, pesomin =?, pesomax =? WHERE idtaxasobrepeso =?");
            ppStmt.setDouble(1, taxa.getValor());
            ppStmt.setDouble(2,taxa.getPesoMin());
            ppStmt.setDouble(3,taxa.getPesoMax());
            ppStmt.setInt(4, taxa.getIdTaxaSobrepeso());
            ppStmt.execute();
            ppStmt.close(); 
        }catch(SQLException EX){
             EX.printStackTrace();
        }        
    }
     
    /** 
    * Método de busca. 
    * O método busca todos os registros das taxas de sobrepeso armazenados no banco de dados.
    * @return List - o retorno é uma lista com taxa de sobrepeso.
    */ 
    public List<TaxaSobrepesoTO> getTaxasSobrepesos(){           
            List<TaxaSobrepesoTO> lstA = new LinkedList<>();
            ResultSet rs;
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM taxasobrepeso ORDER BY pesomin");
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
    
    
    /** 
    * Método para montar um objeto. 
    * O método recebe um resultado do banco e preenche os atributos de um objeto.
    * @param rs - é o resultado de uma solicitação feita ao banco de dados.
    * @return - o retorno é um objeto do tipo TaxaSobrepeTO.
    */
    protected TaxaSobrepesoTO getTaxaSobrepeso(ResultSet rs) throws SQLException{
        TaxaSobrepesoTO t = new TaxaSobrepesoTO();
        t.setValor(rs.getDouble("valor"));
        t.setIdTaxaSobrepeso(rs.getInt("idtaxasobrepeso"));    
        t.setPesoMin(rs.getDouble("pesomin"));
        t.setPesoMax(rs.getDouble("pesomax"));
        return t;
    }
    
    /** 
    * Método de exclusão. 
    * O método exclui um registro do banco de dados..
    * @param t - taxa de sobrepeso que terá os registros excluídos do banco.
    */
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
    * Método para contar registros.
    * O método conta quantas taxas de sobrepeso estão registradas no banco.
    * @return int - valor com o total de registros armazenados.
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
