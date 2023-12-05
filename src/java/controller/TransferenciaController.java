package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TransferenciaDAO;
import model.ClienteDAO;
import entidade.Transferencia;
import entidade.Cliente;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "TransferenciaController", urlPatterns = {"/TransferenciaController"})
public class TransferenciaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");

        Transferencia transferencia = new Transferencia();
        TransferenciaDAO transferenciaDAO = new TransferenciaDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                try {
                    ArrayList<Transferencia> listaTransferencias = transferenciaDAO.ListaDeTransferencias();
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaTransferencias", listaTransferencias);

                    rd = request.getRequestDispatcher("/view/transferencia/listaTransferencias.jsp");
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
                    transferencia = transferenciaDAO.getTransferencia(id);
                    transferencia.setId(id);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de transferencia");
                }

        }

        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<Cliente> listaClientes = clienteDAO.ListaDeCliente();

        request.setAttribute("listaClientes", listaClientes);
        request.setAttribute("transferencia", transferencia);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher("/view/transferencia/formTransferencia.jsp");
        rd.forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String btEnviar = request.getParameter("btEnviar");

        int id = Integer.parseInt(request.getParameter("id"));
        int ContaDestino = Integer.parseInt(request.getParameter("ContaDestino"));
        int ContaOrigem = Integer.parseInt(request.getParameter("ContaOrigem"));
        double valor = Double.parseDouble(request.getParameter("valor"));
        double saldo = Double.parseDouble(request.getParameter("saldo"));
        
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = new Cliente();
        try {
            cliente = clienteDAO.getCliente(ContaOrigem);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("Falha em uma query para get de cliente ao fazer transferencia");
        }
        
        RequestDispatcher rd;
        if (valor < 0.0) {

            Transferencia transferencia = new Transferencia();
            switch (btEnviar) {
                case "NovaTransferencia":
                    request.setAttribute("acao", "NovaTransferencia");
                    break;
            }
            

            request.setAttribute("msgError", "É necessário preencher todos os campos");
            request.setAttribute("transferencia", transferencia);
            rd = request.getRequestDispatcher("/view/transferencia/formTransferencia.jsp");
            rd.forward(request, response);

        }else{
            
            if(cliente == null){
                Transferencia transferencia = new Transferencia();
                switch (btEnviar) {
                    case "NovaTransferencia":
                        request.setAttribute("acao", "NovaTransferencia");
                        break;
                }

                request.setAttribute("msgError", "Conta não existe");
                request.setAttribute("transferencia", transferencia);
                rd = request.getRequestDispatcher("/view/transferencia/formTransferencia.jsp");
                rd.forward(request, response);
            }else{
                
                if(valor > saldo){
                    Transferencia transferencia = new Transferencia();
                    switch (btEnviar) {
                        case "NovaTransferencia":
                            request.setAttribute("acao", "NovaTransferencia");
                            break;
                    }

                request.setAttribute("msgError", "Você não tem saldo suficiente");
                request.setAttribute("transferencia", transferencia);
                rd = request.getRequestDispatcher("/view/transferencia/formTransferencia.jsp");
                rd.forward(request, response);
                
                }else{

                    Transferencia transferencia = new Transferencia(ContaDestino, ContaOrigem, valor);
                    transferencia.setId(id);

                    TransferenciaDAO transferenciaDAO = new TransferenciaDAO();

                    try {
                        switch (btEnviar) {
                            case "NovaTransferencia":
                                transferenciaDAO.NovaTransferencia(transferencia, saldo);
                                request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                                break;
                        }

                        request.setAttribute("transferencia", transferencia);

                        rd = request.getRequestDispatcher("/DashboardControllerCliente");
                        rd.forward(request, response);

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de transferencia");
                    }
                }
            }
        }
    }
}
