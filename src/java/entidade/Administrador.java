package entidade;

public class Administrador {
    private int id;
    private String nome;
    private String cpf;
    private String senha;
    private String email;

    public Administrador(String nome, String cpf, String senha, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
    }
    
    public Administrador(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public Administrador() {
        this.id = 0;
        this.nome = "";
        this.cpf = "";
        this.senha = "";
        this.email = "";
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
