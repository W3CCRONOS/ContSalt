/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classBO.Reatorios;
import classTO.InstrutorTO;
import classTO.SaltoTO;
import classTO.TipoDeSaltoTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author almir
 */
@Named(value = "relatoriosBean")
@SessionScoped
public class RelatoriosBean implements Serializable {

   private int idInstrutor;
   private InstrutorTO instrutor;
   private Date dataUtilInicial;
   private Date dataUtilFinal;
   private Reatorios relatorios;
   private String dataInicial;
   private String dataFinal;
   private List<SaltoTO> saltos;
   private List<TipoDeSaltoTO> tiposDeSaltos;

   public RelatoriosBean() {
       this.setIdInstrutor(0);
       this.setInstrutor(new InstrutorTO());
       this.setDataUtilInicial(new Date());
       this.setDataUtilFinal(new Date());  
       this.setRelatorios(new Reatorios());
       this.setSaltos(new LinkedList<>());
       this.setTiposDeSaltos(new LinkedList<>());
   }
    
    /**
    * Método de seleção do cliente, do tipo de salto e da decolagem.
    * O método recebe da interface o cliente, o id do tipo de salto e decolagem.
    */
    public void buscar(){
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        dataInicial = data.format(dataUtilInicial);         
        data = new SimpleDateFormat("dd/MM/yyyy");
        dataFinal = data.format(dataUtilFinal);
        
        setInstrutor(relatorios.getInstrutor(idInstrutor));//Buscando o instrutor
        this.saltos = relatorios.getSaltos(idInstrutor, dataUtilInicial, dataUtilFinal);//Buscando os saltos
        setTiposDeSaltos(relatorios.getTiposDeSaltos(saltos));
        
    }
    
    /** Método para calcular os valores dos saltos.
    *   O método calcula os valores dos saltos segundo um tipo de salto
    *   @param tipSalt - tipo de salto.
    *   @return valor  - valor dos saltos por tipo de salto.
    */    
    public double getValorPorTipoDeSalto(TipoDeSaltoTO tipSalt){
        return relatorios.getValorPorTipoDeSalto(tipSalt, this.saltos);              
    }
    
    /** Método para calculo total os saltos.
    *   O método calcula a soma total dos saltos com as taxas de sobrepeso realizados.
    *   @return valor  - valor total dos saltos.
    */    
    public double getValorTotalDosSaltos(){
        double somaSaltos = relatorios.getValorDosSalto(this.saltos);
        double somaTaxas = relatorios.somaTaxasDeSoprepeso(this.saltos);
        return somaSaltos+somaTaxas;
    }
    
     /** Método para calcular as taxas de sobrepeso.
    *   O método realiza a soma total das taxas de sobrepeso dos saltos realizados.
    *   @return valor  - valor da soma das taxas de sobrepeso.
    */
    public double somaTaxasDeSoprepeso(){
            return relatorios.somaTaxasDeSoprepeso(saltos);
    }
    
    public int qtdaDeSaltos(){
        return this.saltos.size();
         
    }
    
    public void setIdInstrutor(int idInstrutor) {
        this.idInstrutor = idInstrutor;
    }   

    public int getIdInstrutor() {
        return idInstrutor;
    }

    public InstrutorTO getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(InstrutorTO instrutor) {
        this.instrutor = instrutor;
    }

    public Date getDataUtilInicial() {
        return dataUtilInicial;
    }

    public void setDataUtilInicial(Date dataUtilInicial) {
        this.dataUtilInicial = dataUtilInicial;
    }

    public Date getDataUtilFinal() {
        return dataUtilFinal;
    }

    public void setDataUtilFinal(Date dataUtilFinal) {
        this.dataUtilFinal = dataUtilFinal;
    }

    public Reatorios getRelatorios() {
        return relatorios;
    }

    public void setRelatorios(Reatorios relatorios) {
        this.relatorios = relatorios;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<SaltoTO> getSaltos() {
        return saltos;
    }

    public void setSaltos(List<SaltoTO> saltos) {
        this.saltos = saltos;
    }

    public List<TipoDeSaltoTO> getTiposDeSaltos() {
                System.out.println("Bean relatorio");
        System.out.println(tiposDeSaltos.size());
        for(TipoDeSaltoTO a: tiposDeSaltos) {
            System.out.println(a.getNome());
        }
        return tiposDeSaltos;
    }

    public void setTiposDeSaltos(List<TipoDeSaltoTO> tiposDeSaltos) {
        this.tiposDeSaltos = tiposDeSaltos;
    }
    
    
    
}
