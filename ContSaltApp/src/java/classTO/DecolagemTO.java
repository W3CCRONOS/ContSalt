/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classTO;

import java.sql.Date;

/**
 * Classe para estanciar as decolagens.
 * @author Almir
 * @version 1.0
 */
public class DecolagemTO {
    private int iddecolagem;
    private int numero;
    private String status = "false";
     private Date data;

    public int getIddecolagem() {
        return iddecolagem;
    }

    public void setIddecolagem(int iddecolagem) {
        this.iddecolagem = iddecolagem;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    
}
