package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Investimento;

public class InvestimentoDAO{
    
    public void Inserir(Investimento investimento) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO investimentos (numConta, valor, tipo, data)"
                    + " VALUES (?,?,?,?)");
            sql.setInt(1, investimento.getNumConta());
            sql.setDouble(2, investimento.getValor());
            sql.setString(3, investimento.getTipo());
            sql.setTimestamp(4, investimento.getData());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Investimento getInvestimento(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Investimento investimento = new Investimento();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM investimentos WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    investimento.setId(Integer.parseInt(resultado.getString("ID")));
                    investimento.setValor(Double.parseDouble(resultado.getString("VALOR")));
                    investimento.setTipo(resultado.getString("TIPO"));
                    investimento.setData(resultado.getTimestamp("DATA"));
                }
            }
            return investimento;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Investimento investimento) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE investimentos SET numConta = ?, valor = ?, tipo = ?, data = ? WHERE ID = ?");
            sql.setInt(1, investimento.getNumConta());
            sql.setDouble(2, investimento.getValor());
            sql.setString(3, investimento.getTipo());
            sql.setTimestamp(4, investimento.getData());
            sql.setInt(5, investimento.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Investimento investimento) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM investimentos WHERE ID = ? ");
            sql.setInt(1, investimento.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Investimento> ListaDeInvestimentos() {
        ArrayList<Investimento> meusInvestimentos = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM investimentos order by data";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Investimento investimento = new Investimento(
                            Integer.parseInt(resultado.getString("numConta")),
                            Double.parseDouble(resultado.getString("VALOR")),
                            resultado.getString("TIPO"),
                            resultado.getTimestamp("TIMESTAMP"));
                    investimento.setId(Integer.parseInt(resultado.getString( "ID")));
                    meusInvestimentos.add(investimento);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeInvestimentos) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusInvestimentos;
    }
}
