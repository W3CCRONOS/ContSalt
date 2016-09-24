/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classTO;

/**
 *
 * @author Almir
 */
import java.util.GregorianCalendar;
import java.util.List;
public class InstrutorTO {
    private int idInstrutor;
    private String nomeInstrutor;
    private GregorianCalendar dataAdmissao;
    private boolean disponibilidadeDiaSalto;
    private List<TipoDeSaltoTO> aptidaoParaSaltos;

    public int getIdInstrutor() {
        return idInstrutor;
    }

    public void setIdInstrutor(int idInstrutor) {
        this.idInstrutor = idInstrutor;
    }
    
    
    public String getNomeInstrutor() {
        return nomeInstrutor;
    }

    public void setNomeInstrutor(String nomeInstrutor) {
        this.nomeInstrutor = nomeInstrutor;
    }

    public GregorianCalendar getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(GregorianCalendar dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public boolean isDisponibilidadeDiaSalto() {
        return disponibilidadeDiaSalto;
    }

    public void setDisponibilidadeDiaSalto(boolean disponibilidadeDiaSalto) {
        this.disponibilidadeDiaSalto = disponibilidadeDiaSalto;
    }

    public List<TipoDeSaltoTO> getAptidaoParaSaltos() {
        return aptidaoParaSaltos;
    }

    public void setAptidaoParaSaltos(List<TipoDeSaltoTO> aptidaoParaSaltos) {
        this.aptidaoParaSaltos = aptidaoParaSaltos;
    }

}
