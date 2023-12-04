package entidade;

public class Cliente{
    
    private int id;
    private int numConta;
    private String nome;
    private String cpf;
    private String senha;
    private String email;
    private double saldo;

    public Cliente(int numConta, String nome, String cpf, String senha, String email, double saldo) {
        this.numConta = numConta;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
        this.saldo = saldo;
    }

    public Cliente(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public Cliente(){
        this.id = 0;
        this.numConta = 0;
        this.nome = "";
        this.cpf = "";
        this.senha = "";
        this.email = "";
        this.saldo = 0.0;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
