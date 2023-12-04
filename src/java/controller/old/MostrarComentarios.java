package controller.old;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.old.ComentarioDAO;
import aplicacao.Comentario;


@WebServlet(name = "MostrarComentarios", urlPatterns = {"/MostrarComentarios"})
public class MostrarComentarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                   ComentarioDAO comentarioDAO = new ComentarioDAO();
            try {
                ArrayList<Comentario> listaComentarios = comentarioDAO.ListaDeComentarios();
                request.setAttribute("listaComentarios", listaComentarios);
                RequestDispatcher rd = request.getRequestDispatcher("/view/comentario/mostraComentarios.jsp");
                rd.forward(request, response);
                
                
            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar usuarios (mostrarComentarios) ");
            }
    }

}
