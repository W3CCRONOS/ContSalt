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
            PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO cliente (nome,cpf,peso,celular) VALUES (?,?,?,?)");
            ppStmt.setString(1, client.getNome());
            ppStmt.setString(2,client.getCpf()); 
            ppStmt.setDouble(3,client.getPeso());
            ppStmt.setString(4,client.getCelular());            
            ppStmt.execute();
            ppStmt.close();
        }       
        catch(SQLException ex){         
            ex.printStackTrace();
        }
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
        client.setCelular(rs.getString("celular"));
        return client;
    }
}
