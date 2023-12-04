package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Transferencia;

public class TransferenciaDAO{
    
    public void NovaTransferencia(Transferencia transferencia, double saldo) throws Exception {
        Conexao conexao = new Conexao();
        
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE clientes SET saldo = ?  WHERE NUMCONTA = ?");
            sql.setDouble(1, saldo-transferencia.getValor());
            sql.setInt(2, transferencia.getContaOrigem());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (transferencia) saldo incorreta");
        }
        
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE clientes SET saldo = ?  WHERE NUMCONTA = ?");
            sql.setDouble(1, saldo+transferencia.getValor());
            sql.setInt(2, transferencia.getContaDestino());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (transferencia) saldo incorreta");
        }
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO transferencias (ContaDestino, ContaOrigem, valor, data)"
                    + " VALUES (?,?,?, NOW())");
            sql.setInt(1, transferencia.getContaDestino());
            sql.setInt(2, transferencia.getContaOrigem());
            sql.setDouble(3, transferencia.getValor());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Transferencia getTransferencia(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Transferencia transferencia = new Transferencia();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM transferencias WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    transferencia.setId(Integer.parseInt(resultado.getString("ID")));
                    transferencia.setContaDestino(Integer.parseInt(resultado.getString("ContaDestino")));
                    transferencia.setContaOrigem(Integer.parseInt(resultado.getString("ContaOrigem")));
                    transferencia.setValor(Double.parseDouble(resultado.getString("VALOR")));
                    transferencia.setData(resultado.getTimestamp("DATA"));
                }
            }
            return transferencia;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Transferencia transferencia) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE transferencias SET getContaDestino = ?, getContaOrigem = ?, valor = ?, data = ?  WHERE ID = ?");
            sql.setInt(1, transferencia.getContaDestino());
            sql.setInt(2, transferencia.getContaOrigem());
            sql.setDouble(3, transferencia.getValor());
            sql.setTimestamp(4, transferencia.getData());
            sql.setInt(5, transferencia.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Transferencia transferencia) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM transferencias WHERE ID = ? ");
            sql.setInt(1, transferencia.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Transferencia> ListaDeTransferencias() {
        ArrayList<Transferencia> meusTransferencias = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM transferencias order by data";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Transferencia transferencia = new Transferencia(
                            Integer.parseInt(resultado.getString("ContaDestino")),
                            Integer.parseInt(resultado.getString("ContaOrigem")),
                            Double.parseDouble(resultado.getString("VALOR")));
                    transferencia.setId(Integer.parseInt(resultado.getString( "ID")));
                    meusTransferencias.add(transferencia);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeTransferencias) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusTransferencias;
    }

}
