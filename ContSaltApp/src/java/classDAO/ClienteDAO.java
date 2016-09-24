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
        //conn = new GMCConexao().
    }
     
      public List<ClienteTO> consultar(){
        List<ClienteTO> lstF = new LinkedList<>();
        ResultSet rs;
        try{            
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM filme");
            rs = ppStmt.executeQuery();
            while(rs.next()){
                lstF.add(getFilme(rs));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return lstF;
    }
}
//teste atualizado