<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Administrador" import="entidade.Cliente"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">

                <%
                    // testar se está logado
                    HttpSession sessao = request.getSession(false);
                    if (sessao != null) {
                        Administrador administradorLogado = (Administrador) session.getAttribute("administrador");
                        Cliente clienteLogado = (Cliente) session.getAttribute("cliente");
                        if (administradorLogado != null) { %>
                            <a class="nav-link" href="DashboardControllerAdmin">Dashboard</a>
                            <a class="nav-link" href="ClienteController?acao=Listar">Gerenciar Clientes</a>
                            <a class="nav-link" href="ExtratoController?acao=ExtratoSaldo">Consulta Saldo e Extrato</a>
                            <a class="nav-link" href="AutenticaControllerAdmin?acao=Logout">Logout</a>
                <%  } else {
                            if (clienteLogado != null) { %>
                                <a class="nav-link" href="DashboardControllerCliente">Dashboard</a>
                                <a class="nav-link" href="ExtratoController?acao=Extrato">Extrato</a>
                                <a class="nav-link" href="SaqueController?acao=NovoSaque">Saque</a>
                                <a class="nav-link" href="DepositoController?acao=NovoDeposito">Deposito</a>
                                <a class="nav-link" href="TransferenciaController?acao=NovaTransferencia">Transferencia</a>
                                <a class="nav-link" href="InvestimentoController?acao=NovoInvestimento">Investimentos</a>
                                <a class="nav-link" href="AutenticaControllerCliente?acao=Logout">Logout</a>
                        <%  } else { %>
                                    <a class="nav-link" href="AutenticaControllerCliente?acao=Login">Login Cliente</a>
                                    <a class="nav-link" href="AutenticaControllerAdmin?acao=Login">Login Administrador</a>
                        <%  }}
                    }           %>

            </div>
        </div>
    </div>
</nav>
