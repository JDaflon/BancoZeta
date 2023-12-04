<%@page import="entidade.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entidade.Saque"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Novo Saque</title>
        <link href="view/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../../menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Cliente clienteLogado = (Cliente) session.getAttribute("cliente");
                        Saque saque = (Saque) request.getAttribute("saque");
                        String acao = (String) request.getAttribute("acao");
                        out.println("<h1>Novo Saque</h1>");


                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="SaqueController" method="POST">
                        <input type="hidden" name="id" value="<%=saque.getId()%>" class="form-control">
                        <input type="hidden" name="numConta" value="<%=clienteLogado.getNumConta()%>" class="form-control">
                        <input type="hidden" name="saldo" value="<%=clienteLogado.getSaldo()%>" class="form-control">
                        <div class="mb-3">
                            <label for="nome" class="form-label">Valor</label>
                            <input type="number" step="0.01" name="valor" value="<%=saque.getValor()%>" class="form-control">
                        </div>
                        <div>
                            <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                            <a href="SaqueController?acao=NovoSaque" class="btn btn-danger">Zerar</a>
                        </div>
                    </form>
                    <div>
                    </div>
                </div>
                <script src="view/bootstrap/bootstrap.bundle.min.js"></script>
                </body>

                </html>