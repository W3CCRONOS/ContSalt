
package classTO;

import java.sql.Date;

/**
 *Classe para estanciar os saltos.
 * @author Almir
 * @version 1.0
 */
public class SaltoTO {
    private int idSalto;
    private Date dataDoSalto;
    private int idCliente;
    private int idTipoDeSalto;
    private int idTaxaDeSobrepeso;
    private int idInstrutor;

    public int getIdSalto() {
        return idSalto;
    }

    public void setIdSalto(int idSalto) {
        //ksja
        this.idSalto = idSalto;
    }

    public Date getDataDoSalto() {
        return dataDoSalto;
    }

    public void setDataDoSalto(Date dataDoSalto) {
        this.dataDoSalto = dataDoSalto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdTipoDeSalto() {
        return idTipoDeSalto;
    }

    public void setIdTipoDeSalto(int idTipoDeSalto) {
        this.idTipoDeSalto = idTipoDeSalto;
    }

    public int getIdTaxaDeSobrepeso() {
        return idTaxaDeSobrepeso;
    }

    public void setIdTaxaDeSobrepeso(int idTaxaDeSobrepeso) {
        this.idTaxaDeSobrepeso = idTaxaDeSobrepeso;
    }

    public int getIdInstrutor() {
        return idInstrutor;
    }

    public void setIdInstrutor(int idInstrutor) {
        this.idInstrutor = idInstrutor;
    }
    
    
}
