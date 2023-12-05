package controller;

import entidade.Cliente;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ExtratoDAO;
import entidade.Extrato;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "ExtratoController", urlPatterns = {"/ExtratoController"})
public class ExtratoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        Cliente clienteLogado = (Cliente) request.getSession().getAttribute("cliente");

        Extrato extrato = new Extrato();
        ExtratoDAO extratoDAO = new ExtratoDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Extrato":
                try {
                    ArrayList<Extrato> listaExtrato = extratoDAO.Extrato(clienteLogado.getNumConta());
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("Extrato", listaExtrato);
                    rd = request.getRequestDispatcher("/view/extrato/listaExtrato.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar extratos (Extrato) ");
                }
                break;

        }
        request.setAttribute("extrato", extrato);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher("/view/extrato/formExtrato.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
