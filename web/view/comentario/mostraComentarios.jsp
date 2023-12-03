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
        <title>Exemplo MVC</title>
        <link href="view/bootstrap/bootstrap.min.css"  rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../menu.jsp" />
            <div class="mt-5">

                <h1>Área Pública</h1>
                <h2>Lista de Comentários</h2>
                <%
                    ArrayList<Comentario> listaComentarios = (ArrayList<Comentario>) request.getAttribute("listaComentarios");

                    for (Comentario comentario : listaComentarios) {

                %>
                <div class="card mb-3">
                    <div class="card-body">
                        <h5 class="card-title"><%= comentario.getData() %> - <%= "Comentarista: "+comentario.getNomeususario()%></h5>
                        <h6 class="card-subtitle mb-2 text-muted"><%= "Categoria: "+comentario.getNomeCategoria()%></h6>

                        <p class="card-text"><%=comentario.getComentario()%></p>
                    </div>
                </div>
                <%     }%>

            </div>
        </div>
          <script src="view/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>



