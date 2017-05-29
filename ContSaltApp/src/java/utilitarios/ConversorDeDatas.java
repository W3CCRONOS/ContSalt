
package utilitarios;

import java.util.Date;

/**
 *Classe que converte datas no formato util.Date para
 * o formato sql.Date e vice-versa.
 * @author Almir
 * @version 1.0
 */
public class ConversorDeDatas {

    /**
    * Método para converte datas no formato util.Date para o formato sql.Date.
    * @param dataUtil Date - Data no formato util.Date.
    * @return dataSql Date - Data no formato sql.Date.
    */
    public java.sql.Date passarDataUtilParaDataSql(Date dataUtil){
        java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
        return  dataSql;
    }
    /**
    * Método para converte datas no formato sql.Date para o formato util.Date.
    * @param dataSql Date - Data no formato sql.Date.
    * @return Date - Data no formato util.Date.
    */
    public Date passarDataSqlParaDataUtil(java.sql.Date  dataSql){
        java.util.Date dataUtil = new java.util.Date(dataSql.getTime());
        return dataUtil;
    }
}
