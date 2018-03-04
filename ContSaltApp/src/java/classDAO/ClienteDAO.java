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
 *Classe que conecta-se ao banco de dados para criar, alterar, excluir ou visualizar os registros dos clientes.
 * @author Almir
 * @version 1.0
 * @see ClienteTO
 * @see Conexao
 */
public class ClienteDAO {
    Connection conn;
    
    /**
    * Método construtor. Neste métodos as variáveis
    * são inicializadas.
    */
    public ClienteDAO() {
        this.conn =  new Conexao().conectar();
    }
    /**
    * Método para salvar registros no banco. Este método salva um novo cliente
    * no banco.
    * @param client ClienteTO - Objeto que representa um cliente, que será registrado no banco.
    */
     public void salvar(ClienteTO client){ 
        try{
            PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO cliente (nome,cpf,peso) VALUES (?,?,?)");
            ppStmt.setString(1, client.getNome());
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
    * Método para buscar todos os clientes. Este método busca todos os saltos relalizados
    * em uma determinada data.
    * @return List - O retorno é uma lista de objetos do tipo ClienteTO.
    */    
    public List<ClienteTO> getClientes(){
            
            List<ClienteTO> lstA = new LinkedList<>();
            ResultSet rs;
            
            try{
                PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM cliente");
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
    * Método para montar um objeto cliente. Este método recebe um resultado do banco
    * e preenche os atributos de um objeto.
    * @param rs ResultSet - É o resultado de uma solicitação feita ao banco de dados, referente a uma busca.
    * @return ClienteTO - O retorno é um objeto do tipo ClienteTO.
    */ 
    private ClienteTO getCliente(ResultSet rs) throws SQLException{
        ClienteTO client = new ClienteTO();
        client.setIdCliente(rs.getInt("idcliente"));
        client.setNome(rs.getString("nome"));  
        client.setCpf(rs.getString("cpf"));
        client.setPeso(rs.getDouble("peso"));
        return client;
    }
     public void excluir(ClienteTO c){
        try{
            PreparedStatement ppStmt = conn.prepareStatement("DELETE FROM cliente WHERE idcliente=?");
            ppStmt.setInt(1,c.getIdCliente());
            ppStmt.execute();
            ppStmt.close();
        }
        catch(SQLException EX){
            EX.printStackTrace();
        }
    }
     
     public void alterar(ClienteTO cliente){
        try {
            PreparedStatement ppStmt = conn.prepareStatement("UPDATE cliente SET nome=?, cpf=?, peso=? WHERE idcliente=?");
            ppStmt.setString(1, cliente.getNome());
            ppStmt.setString(2, cliente.getCpf());
            ppStmt.setDouble(3, cliente.getPeso());
            ppStmt.setInt(4,cliente.getIdCliente());
            ppStmt.execute();
            ppStmt.close();
 
        }catch(SQLException EX){
             EX.printStackTrace();
        }        
    }
}
