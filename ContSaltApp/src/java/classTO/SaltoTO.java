
package classTO;

import java.sql.Date;

/**
 *Classe para estanciar os saltos.
 * @author Almir
 * @version 1.0
 */
public class SaltoTO {
    private int idSalto;
    private int idCliente;
    private int idTipoDeSalto;
    private int idTaxaDeSobrepeso;
    private int idInstrutor;
    private int idDecolagem;

    public int getIdSalto() {
        return idSalto;
    }

    public void setIdSalto(int idSalto) {
        this.idSalto = idSalto;
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

    public int getIdDecolagem() {
        return idDecolagem;
    }

    public void setIdDecolagem(int idDecolagem) {
        this.idDecolagem = idDecolagem;
    }
    
}
