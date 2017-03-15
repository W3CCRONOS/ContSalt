/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class Conexao 
{
    final private String drive = "com.mysql.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost/escolapqd";
    final private String usuario = "root";
    final private String senha = "";
  
    public Connection conectar()
    {
        Connection conn = null;
        try
        {            
            Class.forName(drive);
            conn = DriverManager.getConnection(url,usuario, senha);
        }
        catch(ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return conn;        
    }
    
}
