/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classDAO;

import classTO.InstrutorTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import utilitarios.Conexao;

/**
 * Classe que mantém os registros no banco de dados.
 * A classe mantém os registros dos intrutores.
 * @author Almir
 * @version 1.0
 * @see InstrutorTO
 * @see Conexao
 */
public class InstrutorDAO {
    Connection conn;
    
    /**
    * Método construtor. 
    * No método inicializa a variável, realizando a conexão com o banco de dados. 
    */
    public InstrutorDAO() {
       conn = new Conexao().conectar();      
    }
    
   /**
    * Método para criar um registros. 
    * O método salva o registro de um instrutor no banco de dados.
    * @param i - instrutor que será registrado no banco.
    */    
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
    
    /** 
    * Método para alterar um registro. 
    * O método altera os registros de um instruto no banco de dados.
    * @param i - instrutor que terá uma alteração no seu registro no banco.
    */ 
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
    
    /** 
    * Método para alterar um registro. 
    * O método altera o registro sobre a presença de um instrutor no banco de dados.
    * @param i - instrutor que terá uma alteração no seu registro no banco.
    */
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
    * Método de busca. 
    * O método busca todos os registros dos instrutores armazenados no banco de dados.
    * Ordena a busca pela data de adimissão dos instrutores.
    * A data de admissão dos intrutores é um dos requisitos utilizados para selecionar
    * um intrutor.
    * @return List - o retorno é uma lista com instrutores.
    */
    public List<InstrutorTO> getInstrutores(){
 
            List<InstrutorTO> lstA = new LinkedList<InstrutorTO>();
            ResultSet rs;
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM instrutor ORDER BY admissao");
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

    public List<InstrutorTO> getInstrutoresDecolagem(int iddecolagem, Date data){
 
            List<InstrutorTO> lstA = new LinkedList<>();
            ResultSet rs;
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT i.idinstrutor, i.nome, i.cpf, i.admissao, i.presenca, i.peso FROM instrutor i\n" +
                                                                 "INNER JOIN salto s ON s.idinstrutor = i.idinstrutor\n" +
                                                                 "INNER JOIN decolagem d ON s.iddecolagem = ? ORDER BY admissao");
                ppStmt.setInt(1,iddecolagem);
                 ppStmt.setDate(2,data);
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
    * Método para montar um objeto. 
    * O método recebe um resultado do banco e preenche os atributos de um objeto.
    * @param rs - é o resultado de uma solicitação feita ao banco de dados.
    * @return - o retorno é um objeto do tipo InstrutorTO.
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
    
    /** 
    * Método de exclusão. 
    * O método exclui um registro do banco de dados..
    * @param i - instrutor que terá os registros excluídos do banco.
    */
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
