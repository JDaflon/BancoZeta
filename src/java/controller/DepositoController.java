package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DepositoDAO;
import model.ClienteDAO;
import entidade.Deposito;
import entidade.Cliente;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "DepositoController", urlPatterns = {"/DepositoController"})
public class DepositoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");

        Deposito deposito = new Deposito();
        DepositoDAO depositoDAO = new DepositoDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                try {
                    ArrayList<Deposito> listaDepositos = depositoDAO.ListaDeDepositos();
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaDepositos", listaDepositos);

                    rd = request.getRequestDispatcher("/view/deposito/listaDepositos.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar comenatario");
                }
                break;
            case "Alterar":
            case "Excluir":

                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    deposito = depositoDAO.getDeposito(id);
                    deposito.setId(id);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de deposito");
                }

        }

        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<Cliente> listaClientes = clienteDAO.ListaDeCliente();

        request.setAttribute("listaClientes", listaClientes);
        request.setAttribute("deposito", deposito);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher("/view/deposito/formDeposito.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String btEnviar = request.getParameter("btEnviar");

        int id = Integer.parseInt(request.getParameter("id"));
        int ContaDepositante = Integer.parseInt(request.getParameter("ContaDepositante"));
        int ContaDepositario = Integer.parseInt(request.getParameter("ContaDepositario"));
        double valor = Double.parseDouble(request.getParameter("valor"));
        double saldo = Double.parseDouble(request.getParameter("saldo"));
        
        
        RequestDispatcher rd;
        if (valor < 0.0) {

            Deposito deposito = new Deposito();
            switch (btEnviar) {
                case "NovoDeposito":
                    request.setAttribute("acao", "NovoDeposito");
                    break;
            }
            

            request.setAttribute("msgError", "É necessário preencher todos os campos");
            request.setAttribute("deposito", deposito);
            rd = request.getRequestDispatcher("/view/deposito/formDeposito.jsp");
            rd.forward(request, response);

        } else {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = new Cliente();
            try {
                cliente = clienteDAO.getCliente(ContaDepositario);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para get de cliente ao fazer deposito");
            }
            if(cliente == null){
                Deposito deposito = new Deposito();
                switch (btEnviar) {
                    case "NovoDeposito":
                        request.setAttribute("acao", "NovoDeposito");
                        break;
                }

                request.setAttribute("msgError", "Conta não existe");
                request.setAttribute("deposito", deposito);
                rd = request.getRequestDispatcher("/view/deposito/formDeposito.jsp");
                rd.forward(request, response);
            
            }else{

                Deposito deposito = new Deposito(ContaDepositante, ContaDepositario, valor);
                deposito.setId(id);

                DepositoDAO depositoDAO = new DepositoDAO();

                try {
                    switch (btEnviar) {
                        case "NovoDeposito":
                            depositoDAO.NovoDeposito(deposito, saldo);
                            request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                            break;
                    }

                    request.setAttribute("deposito", deposito);

                    rd = request.getRequestDispatcher("/DashboardControllerCliente");
                    rd.forward(request, response);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de deposito");
                }
            }
        }
    }
}
