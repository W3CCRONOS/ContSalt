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
 *
 * @author Almir
 */
public class ClienteDAO {
    
    Connection conn;
    
     public ClienteDAO(){
        conn = new Conexao().conectar();
    }
    
      public List<ClienteTO> getClientes(){
        List<ClienteTO> lstC = new LinkedList<>();
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM cliente");
            ResultSet rs;
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lstC.add(getCliente(rs));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return lstC;
    }
    
    // throws SQLException refere-se ao try cath do método anterior
     //private pq este metodo deve ser visto apenas nesta classe  
       private ClienteTO getCliente(ResultSet rs) throws SQLException {
       ClienteTO cliente =  new ClienteTO(); 
       //carro.setIdcarro(rs.getInt("idcarro"));
       cliente.setNome(rs.getString("nome"));
       cliente.setCpf(rs.getString("cpf"));
       cliente.setIdCliente(rs.getInt("ano"));
       return cliente;
    }
}
//teste atualizado