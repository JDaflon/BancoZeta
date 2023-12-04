package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Saque;

public class SaqueDAO{
    
    public void NovoSaque(Saque saque, double saldo) throws Exception {
        Conexao conexao = new Conexao();
        
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE clientes SET saldo = ?  WHERE NUMCONTA = ?");
            sql.setDouble(1, saldo-saque.getValor());
            sql.setInt(2, saque.getNumConta());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (saque) saldo incorreta");
        }
        
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO saques (numConta, valor, data)"
                    + " VALUES (?,?, NOW())");
            sql.setInt(1, saque.getNumConta());
            sql.setDouble(2, saque.getValor());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Saque getSaque(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Saque saque = new Saque();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM saques WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    saque.setId(Integer.parseInt(resultado.getString("ID")));
                    saque.setNumConta(Integer.parseInt(resultado.getString("numConta")));
                    saque.setValor(Double.parseDouble(resultado.getString("VALOR")));
                    saque.setData(resultado.getTimestamp("DATA"));
                }
            }
            return saque;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Saque saque) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE saques SET numConta = ?, valor = ?, data = ?  WHERE ID = ?");
            sql.setInt(1, saque.getNumConta());
            sql.setDouble(2, saque.getValor());
            sql.setTimestamp(3, saque.getData());
            sql.setInt(4, saque.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Saque saque) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM saques WHERE ID = ? ");
            sql.setInt(1, saque.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Saque> ListaDeSaques() {
        ArrayList<Saque> meusSaques = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM saques order by data";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Saque saque = new Saque(
                            Integer.parseInt(resultado.getString("numConta")),
                            Double.parseDouble(resultado.getString("VALOR")));
                    saque.setId(Integer.parseInt(resultado.getString( "ID")));
                    meusSaques.add(saque);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeSaques) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusSaques;
    }
}
