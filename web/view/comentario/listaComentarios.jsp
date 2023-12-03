<%@page import="java.util.ArrayList"%>
<%@page import="aplicacao.Comentario"%>
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
                <h2>Lista de Comentários</h2>

                <%  String msgOperacaoRealizada = (String) request.getAttribute("msgOperacaoRealizada");
                    if ((msgOperacaoRealizada != null) && (!msgOperacaoRealizada.isEmpty())) {%>

                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <strong><%= msgOperacaoRealizada%></strong>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

                <% }%>

                <a href="ComentarioController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Comentario</th>
                                <th scope="col">Data</th>
                                <th scope="col">Categoria</th>
                                <th scope="col">Usuário</th>
                                <th scope="col">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Comentario> listaComentarios = (ArrayList<Comentario>) request.getAttribute("listaComentarios");

                                for (Comentario comentario : listaComentarios) {
                                    out.println("<tr>");
                                    out.println("<th>" + comentario.getId() + "</th>");
                                    out.println("<td>" + comentario.getComentario()+ "</td>");
                                    out.println("<td>" + comentario.getData()+ "</td>");
                                    out.println("<td>" + comentario.getNomeususario()+ "</td>");
                                    out.println("<td>" + comentario.getNomeCategoria()+ "</td>");%>
                        <td><a href="ComentarioController?acao=Alterar&id=<%=comentario.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="ComentarioController?acao=Excluir&id=<%=comentario.getId()%>" class="btn btn-danger">Excluir</a></td>
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

