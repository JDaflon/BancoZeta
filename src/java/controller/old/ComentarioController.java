package controller.old;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.old.ComentarioDAO;
import model.old.CategoriaDAO;
import model.old.UsuarioDAO;
import aplicacao.Comentario;
import aplicacao.Categoria;
import aplicacao.Usuario;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "ComentarioController", urlPatterns = {"/ComentarioController"})
public class ComentarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");

        Comentario comentario = new Comentario();
        ComentarioDAO comentarioDAO = new ComentarioDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                try {
                    ArrayList<Comentario> listaComentarios = comentarioDAO.ListaDeComentarios();
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaComentarios", listaComentarios);

                    rd = request.getRequestDispatcher("/view/comentario/listaComentarios.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar comenatario ");
                }
                break;
            case "Alterar":
            case "Excluir":

                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    comentario = comentarioDAO.get(id);
                    comentario.setId(id);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de comentario");
                }

        }

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ArrayList<Categoria> listaCategorias = categoriaDAO.ListaDeCategoria();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<Usuario> listaUsuarios = usuarioDAO.ListaDeUsuarios();

        request.setAttribute("listaUsuarios", listaUsuarios);
        request.setAttribute("listaCategorias", listaCategorias);
        request.setAttribute("comentario", comentario);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher("/view/comentario/formComentario.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String btEnviar = request.getParameter("btEnviar");

        int id = Integer.parseInt(request.getParameter("id"));
        String textoComentario = request.getParameter("comentario");
        String dataPostagem = request.getParameter("dataPostagem");

        int idUsuario = 0;
        int idCategoria = 0;
        if (!btEnviar.equals("Excluir")) { // na exclusão os valores dos select não são enviados
            idUsuario = Integer.parseInt(request.getParameter("usuario"));
            idCategoria = Integer.parseInt(request.getParameter("categoria"));
        }
        RequestDispatcher rd;
        if (textoComentario.isEmpty() || dataPostagem.isEmpty()) {

            Comentario comentario = new Comentario();
            switch (btEnviar) {
                case "Incluir":
                    request.setAttribute("acao", "Incluir");
                    break;
                case "Alterar":
                case "Excluir":
                    try {
                        ComentarioDAO comentarioDAO = new ComentarioDAO();
                        comentario = comentarioDAO.get(id);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de comentario");
                    }
                    break;
            }

            CategoriaDAO categoriaDAO = new CategoriaDAO();
            ArrayList<Categoria> listaCategorias = categoriaDAO.ListaDeCategoria();

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            ArrayList<Usuario> listaUsuarios = usuarioDAO.ListaDeUsuarios();
            request.setAttribute("listaUsuarios", listaUsuarios);
            request.setAttribute("listaCategorias", listaCategorias);

            request.setAttribute("msgError", "É necessário preencher todos os campos");
            request.setAttribute("comentario", comentario);
            rd = request.getRequestDispatcher("/view/comentario/formComentario.jsp");
            rd.forward(request, response);

        } else {

            Comentario comentario = new Comentario(id, textoComentario, dataPostagem, idUsuario, idCategoria);
            comentario.setId(id);

            ComentarioDAO comentarioDAO = new ComentarioDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        comentarioDAO.Inserir(comentario);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        comentarioDAO.Alterar(comentario);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        comentarioDAO.Excluir(comentario);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                ArrayList<Comentario> listaComentarios = comentarioDAO.ListaDeComentarios();
                request.setAttribute("listaComentarios", listaComentarios);
                rd = request.getRequestDispatcher("/view/comentario/listaComentarios.jsp");
                rd.forward(request, response);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de comentario");
            }
        }
    }
}
