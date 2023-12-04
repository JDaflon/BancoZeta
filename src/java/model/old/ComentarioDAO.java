package model.old;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import aplicacao.Comentario;
import servicos.Formatacao;

/*
--
-- Estrutura da tabela `comentarios`
--
CREATE TABLE IF NOT EXISTS `comentarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comentario` varchar(255) NOT NULL,
  `data` date DEFAULT NULL,
  `idcategoria` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuario` (`idusuario`),
  KEY `fk_categorias` (`idcategoria`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

 */
public class ComentarioDAO {

    public void Inserir(Comentario comentario) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Comentarios (comentario, data, idusuario, idcategoria) VALUES (?,?,?,?)");
            sql.setString(1, comentario.getComentario());
            sql.setString(2, comentario.getData());
            sql.setInt(3, comentario.getIdusuario());
            sql.setInt(4, comentario.getIdcategoria());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de insert (comentario) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public Comentario get(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {

            Comentario comentario = new Comentario();
            String sqlString = "select * from comentarios c "
                               + " WHERE c.ID = ? ";

            PreparedStatement sql = conexao.getConexao().prepareStatement(sqlString);
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    comentario.setId(Integer.parseInt(resultado.getString("ID")));
                    comentario.setComentario(resultado.getString("COMENTARIO"));
                    comentario.setData(resultado.getString("DATA"));
                    comentario.setIdusuario(Integer.parseInt(resultado.getString("IDUSUARIO")));
                    comentario.setIdcategoria(Integer.parseInt(resultado.getString("IDCATEGORIA")));
                }
            }
            return comentario;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (get comentario) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Comentario comentario) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Comentarios SET comentario = ?, data = ?, idusuario = ?, idcategoria = ?  WHERE ID = ? ");
            sql.setString(1, comentario.getComentario());
            sql.setString(2, comentario.getData());
            sql.setInt(3, comentario.getIdusuario());
            sql.setInt(4, comentario.getIdcategoria());
            sql.setInt(5, comentario.getId());

            sql.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de update (alterar comentario) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Comentario comentario) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Comentarios WHERE ID = ? ");
            sql.setInt(1, comentario.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de delete (excluir comentario) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Comentario> ListaDeComentarios() {

        ArrayList<Comentario> meusComentarios = new ArrayList();
        Conexao conexao = new Conexao();
        try {

            String sqlString = "SELECT * FROM comentarios"; /*, u.nome, ca.descricao from comentarios c "
                    + "LEFT join usuarios u on u.id = c.idusuario "
                    + "LEFT join categorias ca on ca.id = c.idcategoria";*/

            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(sqlString);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {

                    Comentario comentario = new Comentario(resultado.getInt("ID"),
                            resultado.getString("COMENTARIO"),
                            Formatacao.dataParaUI(resultado.getString("DATA")),
                            resultado.getInt("IDUSUARIO"),
                            resultado.getInt("IDCATEGORIA"));

                    meusComentarios.add(comentario);

                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (ListaDeComentarios) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusComentarios;
    }

}
