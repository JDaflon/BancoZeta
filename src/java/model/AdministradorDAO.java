package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Administrador;

public class AdministradorDAO {
    
    public void Inserir(Administrador administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO administradores (nome, cpf, senha, email)"
                    + " VALUES (?,?,?,?)");
            sql.setString(1, administrador.getNome());
            sql.setString(2, administrador.getCpf());
            sql.setString(3, administrador.getSenha());
            sql.setString(4, administrador.getEmail());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Administrador getAdministrador(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Administrador administrador = new Administrador();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM administradores WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    administrador.setId(Integer.parseInt(resultado.getString("ID")));
                    administrador.setNome(resultado.getString("NOME"));
                    administrador.setCpf(resultado.getString("CPF"));
                    administrador.setSenha(resultado.getString("SENHA"));
                    administrador.setEmail(resultado.getString("EMAIL"));
                }
            }
            return administrador;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Administrador administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE administradores SET NOME = ?, CPF = ?, SENHA = ?, EMAIL = ?  WHERE ID = ?");
            sql.setString(1, administrador.getNome());
            sql.setString(2, administrador.getCpf());
            sql.setString(3, administrador.getSenha());
            sql.setString(4, administrador.getEmail());
            sql.setInt(5, administrador.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Administrador administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM administradores WHERE ID = ?");
            sql.setInt(1, administrador.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Administrador> ListaDeAdministrador() {
        ArrayList<Administrador> meusAdministradores = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM administradores order by NOME";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Administrador administrador = new Administrador(
                            resultado.getString("NOME"),
                            resultado.getString("CPF"),
                            resultado.getString("SENHA"),
                            resultado.getString("EMAIL"));
                    administrador.setId(Integer.parseInt(resultado.getString("ID")));
                    
                    meusAdministradores.add(administrador);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeAdm) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusAdministradores;
    }

    public Administrador Logar(Administrador administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM administradores WHERE cpf=? and senha =? LIMIT 1");
            sql.setString(1, administrador.getCpf());
            sql.setString(2, administrador.getSenha());
            ResultSet resultado = sql.executeQuery();
            Administrador administradorObtido = new Administrador();
            if (resultado != null) {
                while (resultado.next()) {
                    administradorObtido.setId(Integer.parseInt(resultado.getString("ID")));
                    administradorObtido.setNome(resultado.getString("NOME"));
                    administradorObtido.setCpf(resultado.getString("CPF"));
                    administradorObtido.setSenha(resultado.getString("SENHA"));
                    administradorObtido.setEmail(resultado.getString("EMAIL"));
                }
            }
            return administradorObtido;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de (login) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
}
