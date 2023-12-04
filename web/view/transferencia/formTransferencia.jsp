<%@page import="entidade.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entidade.Transferencia"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Nova Transferencia</title>
        <link href="view/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../../menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Cliente clienteLogado = (Cliente) session.getAttribute("cliente");
                        Transferencia transferencia = (Transferencia) request.getAttribute("transferencia");
                        String acao = (String) request.getAttribute("acao");
                        out.println("<h1>Nova Transferencia</h1>");
                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="TransferenciaController" method="POST">
                        <input type="hidden" name="id" value="<%=transferencia.getId()%>" class="form-control">
                        <input type="hidden" name="ContaOrigem" value="<%=clienteLogado.getNumConta()%>" class="form-control">
                        <input type="hidden" name="saldo" value="<%=clienteLogado.getSaldo()%>" class="form-control">
                        <div class="mb-3">
                            <label for="valor" class="form-label">Valor</label>
                            <input type="number" step="0.01" name="valor" value="<%=transferencia.getValor()%>" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="ContaDestino" class="form-label">Numero da conta para receber a tranferencia</label>
                            <input type="text" name="ContaDestino" value="<%=transferencia.getContaDestino()%>" class="form-control">
                        </div>
                        <div>
                            <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                            <a href="TransferenciaController?acao=NovaTransferencia" class="btn btn-danger">Zerar</a>
                        </div>
                    </form>
                    <div>
                    </div>
                </div>
                <script src="view/bootstrap/bootstrap.bundle.min.js"></script>
                </body>

                </html>