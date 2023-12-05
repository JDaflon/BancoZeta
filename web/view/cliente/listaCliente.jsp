<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Gerenciar Usuários - Login com DAO</title>
        <link href="view/bootstrap/bootstrap.min.css"  rel="stylesheet"> 

    </head>
    <body>
        <div class="container">
            <jsp:include page="../../menu.jsp" />
            <div class="mt-5">

                <h1>Área Restrita</h1>
                <h2>Lista de Usuários</h2>

                <%  String msgOperacaoRealizada = (String) request.getAttribute("msgOperacaoRealizada");
                    if ((msgOperacaoRealizada != null) && (!msgOperacaoRealizada.isEmpty())) {%>

                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <strong><%= msgOperacaoRealizada%></strong>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

                <% }%>

                <a href="ClienteController?acao=Inserir" class="mb-2 btn btn-primary">Inserir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Conta Nº</th>
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Email</th>
                                <th scope="col">Saldo</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) request.getAttribute("listaClientes");

                                for (Cliente cliente : listaClientes) {
                                    out.println("<tr>");
                                    out.println("<th>" + cliente.getId() + "</th>");
                                    out.println("<td>" + cliente.getNumConta()+ "</td>");
                                    out.println("<td>" + cliente.getNome() + "</td>");
                                    out.println("<td>" + cliente.getCpf() + "</td>");
                                    out.println("<td>" + cliente.getEmail()+ "</td>");
                                    out.println("<td>" + cliente.getSaldo()+ "</td>");%>
                        <td><a href="ClienteController?acao=Alterar&numConta=<%=cliente.getNumConta()%>" class="btn btn-warning">Alterar</a>
                            <a href="ClienteController?acao=Excluir&numConta=<%=cliente.getNumConta()%>" class="btn btn-danger">Excluir</a></td>
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

