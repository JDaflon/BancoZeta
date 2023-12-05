package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Extrato;

public class ExtratoDAO {
    
    public ArrayList<Extrato> Extrato(int id) {
        //int id = 1234567;
        ArrayList<Extrato> extrato = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement( "SELECT 'transferencia' as operacao, ContaDestino as conta, valor, transferencias.data from transferencias WHERE ContaOrigem = ?"
                              + " union SELECT 'deposito' as operacao, ContaDepositario as conta, valor, depositos.data from depositos WHERE ContaDepositante = ?"
                              + " union SELECT 'saque' as operacao, numConta as conta, valor, saques.data from saques WHERE numConta = ?"
                              + " order by data desc");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            preparedStatement.setInt(3, id);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Extrato operacao = new Extrato(
                            resultado.getString("OPERACAO"),
                            resultado.getInt("conta"),
                            resultado.getDouble("VALOR"),
                            resultado.getTimestamp("DATA"));
                    extrato.add(operacao);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (Extrato) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return extrato;
    }   

}
