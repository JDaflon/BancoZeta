package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ClienteDAO;
import entidade.Cliente;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "ClienteController", urlPatterns = {"/ClienteController"})
public class ClienteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");

        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                try {
                    ArrayList<Cliente> listaClientes = clienteDAO.ListaDeCliente();
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaClientes", listaClientes);
                    rd = request.getRequestDispatcher("/view/cliente/listaCliente.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar clientes (Cliente) ");
                }
                break;
            case "Alterar":
            case "Excluir":
                try {
                    int numConta = Integer.parseInt(request.getParameter("numConta"));
                    cliente = clienteDAO.getCliente(numConta);
                    cliente.setNumConta(numConta);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de cliente");
                }
                break;

        }
        request.setAttribute("cliente", cliente);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher("/view/cliente/formCliente.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int numConta = Integer.parseInt(request.getParameter("numConta"));
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");
        double saldo = 0;
        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty() || email.isEmpty()) {

            Cliente cliente = new Cliente();
            switch (btEnviar) {
                case "Inserir":
                    request.setAttribute("acao", "Inserir");
                    break;
                case "Alterar":
                case "Excluir":
                    try {
                        ClienteDAO clienteDAO = new ClienteDAO();
                        cliente = clienteDAO.getCliente(numConta);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de cliente");
                    }
                    break;
            }

            request.setAttribute("msgError", "É necessário preencher todos os campos");
            request.setAttribute("cliente", cliente);

            rd = request.getRequestDispatcher("/view/cliente/formCliente.jsp");
            rd.forward(request, response);

        } else {

            Cliente cliente = new Cliente(numConta, nome, cpf, senha, email, saldo);
            cliente.setId(id);

            ClienteDAO clienteDAO = new ClienteDAO();

            try {
                switch (btEnviar) {
                    case "Inserir":
                        clienteDAO.Inserir(cliente);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        clienteDAO.Alterar(cliente);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        clienteDAO.Excluir(cliente);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                ArrayList<Cliente> listaClientes = clienteDAO.ListaDeCliente();
                request.setAttribute("listaClientes", listaClientes);

                rd = request.getRequestDispatcher("/view/cliente/listaCliente.jsp");
                rd.forward(request, response);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de cliente");
            }
        }
    }
}
