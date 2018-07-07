/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classDAO;

import classTO.ClienteTO;
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
 * @see ClienteTO
 * @see Conexao
 */
public class ClienteDAO{
    Connection conn;
    
    /**
    * Método construtor. 
    * No método inicializa a variável, realizando a conexão com o banco de dados. 
    */
    public ClienteDAO() {
        this.conn =  new Conexao().conectar();
    }
    
    /**
    * Método para criar registros. 
    * O método salva o registro de um cliente no banco de dados.
    * @param client - cliente que será registrado no banco.
    */
     public void salvar(ClienteTO client){ 
        try{
            PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO cliente (nome,cpf,peso) VALUES (?,?,?)");
            ppStmt.setString(1,client.getNome());
            ppStmt.setString(2,client.getCpf()); 
            ppStmt.setDouble(3,client.getPeso());           
            ppStmt.execute();
            ppStmt.close();
        }       
        catch(SQLException ex){         
            ex.printStackTrace();
        }
    } 
     
    /** 
    * Método de busca. 
    * O método busca todos os registros dos clientes armazenados no banco de dados.
    * @return List - o retorno é uma lista com clientes.
    */    
    public List<ClienteTO> getClientes(){
            
            List<ClienteTO> lstA = new LinkedList<>();
            ResultSet rs;
            
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM cliente ORDER BY nome");
                rs = ppStmt.executeQuery();
                while(rs.next()){
                    lstA.add(getCliente(rs));
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
    * @return - o retorno é um objeto do tipo ClienteTO.
    */ 
    private ClienteTO getCliente(ResultSet rs) throws SQLException{
        ClienteTO client = new ClienteTO();
        client.setIdCliente(rs.getInt("idcliente"));
        client.setNome(rs.getString("nome"));  
        client.setCpf(rs.getString("cpf"));
        client.setPeso(rs.getDouble("peso"));
        return client;
    }
    
    /** 
    * Método de exclusão. 
    * O método exclui um registro do banco de dados..
    * @param client - cliente que terá os registros excluídos do banco.
    */ 
     public void excluir(ClienteTO client){
        try{
            PreparedStatement ppStmt = conn.prepareStatement("DELETE FROM cliente WHERE idcliente=?");
            ppStmt.setInt(1,client.getIdCliente());
            ppStmt.execute();
            ppStmt.close();
        }
        catch(SQLException EX){
            EX.printStackTrace();
        }
    }
     
    /** 
    * Método para alterar um registro. 
    * O método altera os registros de um cliente no banco de dados.
    * @param client - cliente que terá uma alteração no seu registro no banco.
    */ 
     public void alterar(ClienteTO client){
        try {
            PreparedStatement ppStmt = conn.prepareStatement("UPDATE cliente SET nome=?, cpf=?, peso=? WHERE idcliente=?");
            ppStmt.setString(1, client.getNome());
            ppStmt.setString(2, client.getCpf());
            ppStmt.setDouble(3, client.getPeso());
            ppStmt.setInt(4,client.getIdCliente());
            ppStmt.execute();
            ppStmt.close();
 
        }catch(SQLException EX){
             EX.printStackTrace();
        }        
    }
    
}
