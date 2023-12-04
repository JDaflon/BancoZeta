package controller;

import entidade.Administrador;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AdministradorDAO;

/**
 *
 * @author Leonardo
 */
@WebServlet(name = "AutenticaControllerAdmin", urlPatterns = {"/AutenticaControllerAdmin"})
public class AutenticaControllerAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
       RequestDispatcher rd ;
        switch (acao) {
            case "Login":  // chama form de login
                
                rd = request.getRequestDispatcher("/view/autenticacao/formLoginAdmin.jsp");
                rd.forward(request, response);
                
                break;
                
            case "Logout":
                HttpSession session = request.getSession();
                session.invalidate();
                rd = request.getRequestDispatcher("/view/autenticacao/formLoginAdmin.jsp");
                rd.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        // pegando os parâmetros do request
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");
        if (cpf_user.isEmpty() || senha_user.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "Usuário e/ou senha incorreto");
            rd = request.getRequestDispatcher("/view/autenticacao/formLoginAdmin.jsp");
            rd.forward(request, response);


        } else {
            Administrador administradorObtido;
            Administrador administrador = new Administrador(cpf_user, senha_user);
            AdministradorDAO administradorDAO = new AdministradorDAO();
            try {
               administradorObtido = administradorDAO.Logar(administrador);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para Logar");
            }

            if (administradorObtido.getId() != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("administrador", administradorObtido);
                
                rd = request.getRequestDispatcher("/DashboardControllerAdmin");
                rd.forward(request, response);
                
         
            } else {
                request.setAttribute("msgError", "Usuário e/ou senha incorreto");
                rd = request.getRequestDispatcher("/view/autenticacao/formLoginAdmin.jsp");
                rd.forward(request, response);
                
             
            }
        }
    }

}
