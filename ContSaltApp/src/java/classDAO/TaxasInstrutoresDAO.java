/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classDAO;

import classTO.InstrutorTO;
import classTO.TaxaSobrepesoTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utilitarios.Conexao;

/**
 *
 * @author Almir
 */
public class TaxasInstrutoresDAO {
     Connection conn;
      

    public TaxasInstrutoresDAO() {
        conn = new Conexao().conectar();
    }
      
      
      
    public void salvarTaxa(InstrutorTO i, TaxaSobrepesoTO t){
 
        try{
            PreparedStatement ppStmt =  conn.prepareStatement("INSERT INTO taxasobrepeso_has_instrutor (instrutor_idinstrutor,taxasobrepeso_idtaxasobrepeso) VALUES (?,?)");
            ppStmt.setInt(1,i.getIdInstrutor());
            ppStmt.setInt(2,t.getIdTaxaSobrepeso());
            ppStmt.execute();
            System.out.println("Cadastrou");
        }
        
        catch(SQLException ex){         
            ex.printStackTrace();
        }
    }
}

    
    
    
 