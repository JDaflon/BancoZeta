package entidade;

import java.sql.Timestamp;

public class Investimento {
    private int id;
    private int numConta;
    private double valor;
    private String tipo;
    private Timestamp data;

    public Investimento(int numConta, double valor, String tipo, Timestamp data) {
        this.numConta = numConta;
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
    }
    
    public Investimento() {
        this.id = 0;
        this.numConta = 0;
        this.valor = 0;
        this.tipo = "";
        this.data = new Timestamp(0);
    }
    
    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }
    
}
