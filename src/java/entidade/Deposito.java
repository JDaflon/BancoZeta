package entidade;

import java.sql.Timestamp;

public class Deposito {
    private int id;
    private int ContaDepositante;
    private int ContaDepositario;
    private double valor;
    private Timestamp data;

    public Deposito(int ContaDepositante, int ContaDepositario, double valor) {
        this.ContaDepositante = ContaDepositante;
        this.ContaDepositario = ContaDepositario;
        this.valor = valor;
    }
    
    public Deposito() {
        this.id = 0;
        this.ContaDepositante = 0;
        this.ContaDepositario = 0;
        this.valor = 0;
        this.data = new Timestamp(0);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContaDepositante() {
        return ContaDepositante;
    }

    public void setContaDepositante(int ContaDepositante) {
        this.ContaDepositante = ContaDepositante;
    }
    
    public int getContaDepositario() {
        return ContaDepositario;
    }

    public void setContaDepositario(int ContaDepositario) {
        this.ContaDepositario = ContaDepositario;
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
