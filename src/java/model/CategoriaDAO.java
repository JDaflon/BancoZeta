package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import aplicacao.Categoria;

/*
--
-- Estrutura da tabela `comentarios`
--
CREATE TABLE IF NOT EXISTS `categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

 */
public class CategoriaDAO {

    public ArrayList<Categoria> ListaDeCategoria() {

        ArrayList<Categoria> asCategorias = new ArrayList();
        Conexao conexao = new Conexao();
        try {

            String sqlString = "select * from Categorias order by descricao";

            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(sqlString);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {

                    Categoria categoria = new Categoria(resultado.getInt("ID"),
                            resultado.getString("DESCRICAO"));

                    asCategorias.add(categoria);

                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (ListaDeCategoria) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return asCategorias;
    }

}
