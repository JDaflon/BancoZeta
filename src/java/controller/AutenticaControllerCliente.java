package controller;

import entidade.Cliente;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ClienteDAO;

/**
 *
 * @author Leonardo
 */
@WebServlet(name = "AutenticaControllerCliente", urlPatterns = {"/AutenticaControllerCliente"})
public class AutenticaControllerCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
       RequestDispatcher rd ;
        switch (acao) {
            case "Login":  // chama form de login
                
                rd = request.getRequestDispatcher("/view/autenticacao/formLoginCliente.jsp");
                rd.forward(request, response);
                
                break;
                
            case "Logout":
                HttpSession session = request.getSession();
                session.invalidate();
                rd = request.getRequestDispatcher("/view/autenticacao/formLoginCliente.jsp");
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
            rd = request.getRequestDispatcher("/view/autenticacao/formLoginCliente.jsp");
            rd.forward(request, response);


        } else {
            Cliente clienteObtido;
            Cliente cliente = new Cliente(cpf_user, senha_user);
            ClienteDAO clienteDAO = new ClienteDAO();
            try {
               clienteObtido = clienteDAO.Logar(cliente);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para Logar");
            }

            if (clienteObtido.getId() != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("cliente", clienteObtido);
                
                rd = request.getRequestDispatcher("/DashboardControllerCliente");
                rd.forward(request, response);
                
         
            } else {
                request.setAttribute("msgError", "Usuário e/ou senha incorreto");
                rd = request.getRequestDispatcher("/view/autenticacao/formLoginCliente.jsp");
                rd.forward(request, response);
                
             
            }
        }
    }

}
