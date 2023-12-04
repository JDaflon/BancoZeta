package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SaqueDAO;
import model.ClienteDAO;
import entidade.Saque;
import entidade.Cliente;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "SaqueController", urlPatterns = {"/SaqueController"})
public class SaqueController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");

        Saque saque = new Saque();
        SaqueDAO saqueDAO = new SaqueDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                try {
                    ArrayList<Saque> listaSaques = saqueDAO.ListaDeSaques();
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaSaques", listaSaques);

                    rd = request.getRequestDispatcher("/view/saque/listaSaques.jsp");
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
                    saque = saqueDAO.getSaque(id);
                    saque.setId(id);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de saque");
                }

        }

        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<Cliente> listaClientes = clienteDAO.ListaDeCliente();

        request.setAttribute("listaClientes", listaClientes);
        request.setAttribute("saque", saque);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher("/view/saque/formSaque.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String btEnviar = request.getParameter("btEnviar");

        int id = Integer.parseInt(request.getParameter("id"));
        int numConta = Integer.parseInt(request.getParameter("numConta"));
        double valor = Double.parseDouble(request.getParameter("valor"));
        double saldo = Double.parseDouble(request.getParameter("saldo"));
        
        
        RequestDispatcher rd;
        if (valor < 0.0) {

            Saque saque = new Saque();
            switch (btEnviar) {
                case "NovoSaque":
                    request.setAttribute("acao", "NovoSaque");
                    break;
            }
            

            request.setAttribute("msgError", "É necessário preencher todos os campos");
            request.setAttribute("saque", saque);
            rd = request.getRequestDispatcher("/view/saque/formSaque.jsp");
            rd.forward(request, response);

        } else {
            if(valor > saldo){
                Saque saque = new Saque();
                switch (btEnviar) {
                    case "NovoSaque":
                        request.setAttribute("acao", "NovoSaque");
                        break;
                }

                request.setAttribute("msgError", "Você não tem saldo suficiente");
                request.setAttribute("saque", saque);
                rd = request.getRequestDispatcher("/view/saque/formSaque.jsp");
                rd.forward(request, response);
            
            }else{

                Saque saque = new Saque(numConta, valor);
                saque.setId(id);

                SaqueDAO saqueDAO = new SaqueDAO();

                try {
                    switch (btEnviar) {
                        case "NovoSaque":
                            saqueDAO.NovoSaque(saque, saldo);
                            request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                            break;
                    }

                    request.setAttribute("saque", saque);

                    rd = request.getRequestDispatcher("/DashboardControllerCliente");
                    rd.forward(request, response);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de saque");
                }
            }
        }
    }
}
