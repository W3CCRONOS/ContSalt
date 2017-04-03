/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classBO;

import classDAO.InstrutorDAO;
import classTO.InstrutorTO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Almir
 */
public class InstrutorBO {
    
    public InstrutorBO() {     
    }
    
    public InstrutorTO passarDataUtilParaDataSql(InstrutorTO i ,Date data){
        java.sql.Date dataSql = new java.sql.Date(data.getTime());
        i.setAdmissao(dataSql);
        return i;
    }
    public Date passarDataSqlParaDataUtil(InstrutorTO i){
        java.util.Date dataUtil = new java.util.Date(i.getAdmissao().getTime());
        return dataUtil;
    }
}
