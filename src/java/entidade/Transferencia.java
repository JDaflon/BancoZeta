package entidade;

import java.sql.Timestamp;

public class Transferencia {
    private int id;
    private int ContaDestino;
    private int ContaOrigem;
    private double valor;
    private Timestamp data;

    public Transferencia(int ContaDestino, int ContaOrigem, double valor) {
        this.ContaDestino = ContaDestino;
        this.ContaOrigem = ContaOrigem;
        this.valor = valor;
    }
    
    public Transferencia() {
        this.id = 0;
        this.ContaDestino = 0;
        this.ContaOrigem = 0;
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

    public int getContaOrigem() {
        return ContaOrigem;
    }

    public void setContaOrigem(int ContaOrigem) {
        this.ContaOrigem = ContaOrigem;
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
