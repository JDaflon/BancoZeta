<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Cliente"%>
<%@page import="entidade.Extrato"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Extrato</title>
        <link href="view/bootstrap/bootstrap.min.css"  rel="stylesheet"> 

    </head>
    <body>
        <div class="container">
            <jsp:include page="../../menu.jsp" />
            <div class="mt-5">

                <h1>Área Restrita</h1>
                <h2>Extrato</h2>

                <%  String msgOperacaoRealizada = (String) request.getAttribute("msgOperacaoRealizada");
                    if ((msgOperacaoRealizada != null) && (!msgOperacaoRealizada.isEmpty())) {%>

                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <strong><%= msgOperacaoRealizada%></strong>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

                <% }%>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Operacao</th>
                                <th scope="col">Conta Origem</th>
                                <th scope="col">Conta Destino</th>
                                <th scope="col">Valor</th>
                                <th scope="col">Data</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                Cliente clienteLogado = (Cliente) session.getAttribute("cliente");
                                session.setAttribute("conta", clienteLogado);
                                
                                ArrayList<Extrato> extrato = (ArrayList<Extrato>) request.getAttribute("ExtratoSaldo");

                                for (Extrato operacoes : extrato) {
                                    out.println("<tr>");
                                    out.println("<th>" + operacoes.getOperacao() + "</th>");
                                    out.println("<td>" + operacoes.getContaOrigem()+ "</td>");
                                    out.println("<td>" + operacoes.getContaDestino()+ "</td>");
                                    out.println("<td>" + operacoes.getValor()+ "</td>");
                                    out.println("<td>" + operacoes.getData()+ "</td>");%>
                            <%   out.println("</tr>");
                                }
                            %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="view/bootstrap/bootstrap.bundle.min.js"></script>

    </body>
</html>

