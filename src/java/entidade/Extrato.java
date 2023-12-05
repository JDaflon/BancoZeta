package entidade;

import java.sql.Timestamp;

public class Extrato {
    private int id;
    private int ContaDestino;
    private String operacao;
    private double valor;
    private Timestamp data;

    public Extrato(String operacao, int ContaDestino, double valor, Timestamp data) {
        this.ContaDestino = ContaDestino;
        this.operacao = operacao;
        this.data = data;
        this.valor = valor;
    }
    
    public Extrato() {
        this.id = 0;
        this.ContaDestino = 0;
        this.operacao = "";
        this.valor = 0;
        this.data = new Timestamp(0);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getContaDestino() {
        return ContaDestino;
    }

    public void setContaDestino(int ContaDestino) {
        this.ContaDestino = ContaDestino;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }
    
}
