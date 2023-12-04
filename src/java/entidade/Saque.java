package entidade;

import java.sql.Timestamp;

public class Saque {
    private int id;
    private int numConta;
    private double valor;
    private Timestamp data;

    public Saque(int numConta, double valor) {
        this.numConta = numConta;
        this.valor = valor;
    }
    
    public Saque() {
        this.id = 0;
        this.numConta = 0;
        this.valor = 0;
        this.data = new Timestamp(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }
    
}
