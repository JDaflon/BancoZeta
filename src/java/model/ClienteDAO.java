package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Cliente;
import entidade.Extrato;

public class ClienteDAO {
    
    public void Inserir(Cliente cliente) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO clientes (numConta, nome, cpf, senha, email, saldo)"
                    + " VALUES (?,?,?,?,?,?)");
            sql.setInt(1, cliente.getNumConta());
            sql.setString(2, cliente.getNome());
            sql.setString(3, cliente.getCpf());
            sql.setString(4, cliente.getSenha());
            sql.setString(5, cliente.getEmail());
            sql.setDouble(6, cliente.getSaldo());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Cliente getCliente(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Cliente cliente = new Cliente();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM clientes WHERE numConta = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    cliente.setId(Integer.parseInt(resultado.getString("ID")));
                    cliente.setNumConta(Integer.parseInt(resultado.getString("NUMCONTA")));
                    cliente.setNome(resultado.getString("NOME"));
                    cliente.setCpf(resultado.getString("CPF"));
                    cliente.setSenha(resultado.getString("SENHA"));
                    cliente.setEmail(resultado.getString("EMAIL"));
                }
            }
            
            return cliente;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Cliente cliente) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE clientes SET nome = ?, cpf = ?, senha = ?, email = ?  WHERE NUMCONTA = ? ");
            sql.setString(1, cliente.getNome());
            sql.setString(2, cliente.getCpf());
            sql.setString(3, cliente.getSenha());
            sql.setString(4, cliente.getEmail());
            sql.setInt(5, cliente.getNumConta());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Cliente cliente) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM clientes WHERE NumConta = ? ");
            sql.setInt(1, cliente.getNumConta());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Cliente> ListaDeCliente() {
        ArrayList<Cliente> meusClientes = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM clientes order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Cliente cliente = new Cliente(
                            Integer.parseInt(resultado.getString("NUMCONTA")),
                            resultado.getString("NOME"),
                            resultado.getString("CPF"),
                            resultado.getString("SENHA"),
                            resultado.getString("EMAIL"),
                            Double.parseDouble(resultado.getString("SALDO")));
                    cliente.setId(Integer.parseInt(resultado.getString("ID")));
                    meusClientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeCliente) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusClientes;
    }
    
    public ArrayList<Extrato> Extrato(int id) {
        ArrayList<Extrato> extrato = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement( "SELECT 'transferencia' as operacao, ContaDestino as conta, valor, transferencias.data from transferencias WHERE ContaOrigem = ?"
                              + " union SELECT 'deposito' as operacao, ContaDepositario as conta, valor, depositos.data from depositos WHERE ContaDepositante = ?"
                              + " union SELECT 'saque' as operacao, numConta as conta, valor, saques.data from saques WHERE numConta = ?"
                              + " order by data desc");
            preparedStatement.setInt(1, id);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Extrato operacao = new Extrato(
                            resultado.getString("OPERACAO"),
                            resultado.getInt("ContaDestino"),
                            resultado.getDouble("VALOR"),
                            resultado.getTimestamp("DATA"));
                    operacao.setId(Integer.parseInt(resultado.getString("ID")));
                    extrato.add(operacao);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeCliente) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return extrato;
    }   

    public Cliente Logar(Cliente cliente) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM clientes WHERE cpf=? and senha =? LIMIT 1");
            sql.setString(1, cliente.getCpf());
            sql.setString(2, cliente.getSenha());
            ResultSet resultado = sql.executeQuery();
            Cliente clienteObtido = new Cliente();
            if (resultado != null) {
                while (resultado.next()) {
                    clienteObtido.setId(Integer.parseInt(resultado.getString("ID")));
                    clienteObtido.setNumConta(Integer.parseInt(resultado.getString("NUMCONTA")));
                    clienteObtido.setNome(resultado.getString("NOME"));
                    clienteObtido.setCpf(resultado.getString("CPF"));
                    clienteObtido.setSenha(resultado.getString("SENHA"));
                    clienteObtido.setEmail(resultado.getString("EMAIL"));
                    clienteObtido.setSaldo(Double.parseDouble(resultado.getString("SALDO")));
                }
            }
            return clienteObtido;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}
