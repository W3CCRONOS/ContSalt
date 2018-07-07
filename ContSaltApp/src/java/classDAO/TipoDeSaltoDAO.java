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
 * Classe que mantém os registros no banco de dados.
 * A classe mantém os registros dos clientes.
 * @author Almir
 * @version 1.0
 * @see TipoDeSaltoTO
 * @see Conexao
 */
public class TipoDeSaltoDAO {
        Connection conn;

    /**
    * Método construtor. 
    * No método inicializa a variável, realizando a conexão com o banco de dados. 
    */
    public TipoDeSaltoDAO() {
       conn = new Conexao().conectar();      
    }
    
    /**
    * Método para criar registros. 
    * O método salva o registro de um tipo de salto no banco de dados.
    * @param tipodesalto - tipo de salto que será registrado no banco.
    */    
    public void salvar(TipoDeSaltoTO tipodesalto){    
        try{
            PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO tipodesalto (nome,valor,taxadesobrepeso) VALUES (?,?,?)");
            ppStmt.setString(1,tipodesalto.getNome());
            ppStmt.setDouble(2,tipodesalto.getValor());
            ppStmt.setString(3, tipodesalto.getTaxaDeSobrePeso());
            ppStmt.execute();
            ppStmt.close(); 
        }
        
        catch(SQLException ex){         
            ex.printStackTrace();
        }
    }
         
    /** 
    * Método para alterar um registro. 
    * O método altera os registros de um tipo de salto no banco de dados.
    * @param tipodesalto - tipo de salto que terá uma alteração no seu registro no banco.
    */
     public void alterar(TipoDeSaltoTO tipodesalto){
        try {
            PreparedStatement ppStmt = conn.prepareStatement("UPDATE tipodesalto SET nome =?, valor=?, taxadesobrepeso=? WHERE idtipodesalto=?");
            ppStmt.setString(1, tipodesalto.getNome());
            ppStmt.setDouble(2, tipodesalto.getValor());
             ppStmt.setString(3, tipodesalto.getTaxaDeSobrePeso());
            ppStmt.setInt(4,tipodesalto.getIdTipoDeSalto());
            ppStmt.execute();
            ppStmt.close();
 
        }catch(SQLException EX){
             EX.printStackTrace();
        }        
    }
         
    /** 
    * Método de busca. 
    * O método busca todos os registros dos tipos de saltos armazenados no banco de dados.
    * @return List - o retorno é uma lista com tipos de saltos.
    */
    public List<TipoDeSaltoTO> getTiposDeSaltos(){
            
            List<TipoDeSaltoTO> lstA = new LinkedList<TipoDeSaltoTO>();
            ResultSet rs;
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM tipodesalto ORDER BY nome");
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
    
    /** 
    * Método para montar um objeto. 
    * O método recebe um resultado do banco e preenche os atributos de um objeto.
    * @param rs - é o resultado de uma solicitação feita ao banco de dados.
    * @return - o retorno é um objeto do TipoDeSaltoTO.
    */
    protected TipoDeSaltoTO getTipoDeSalto(ResultSet rs) throws SQLException{
        TipoDeSaltoTO tposalt = new TipoDeSaltoTO();
        tposalt.setNome(rs.getString("nome"));
        tposalt.setValor(rs.getDouble("valor"));
        tposalt.setTaxaDeSobrePeso(rs.getString("taxadesobrepeso"));
        tposalt.setIdTipoDeSalto(rs.getInt("idtipodesalto")); 
        return tposalt;
    }
    
    /** 
    * Método de exclusão. 
    * O método exclui um registro do banco de dados..
    * @param c - tipo de salto que terá os registros excluídos do banco.
    */
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
    * Método para contar registros.
    * O método conta quantos tipos de saltos estão registradas no banco.
    * @return int - valor com o total de registros armazenados.
    */
    public int contarTiposDeSaltosArmazenados(){
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
