<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Administrador" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Administrador</title>
        <link href="view/bootstrap/bootstrap.min.css"  rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../menu.jsp" />
            <div class="mt-5">

                <h1>Área Restrita</h1>
                <%
                    Administrador AdministradorLogado = (Administrador) session.getAttribute("administrador");
                    out.println("<h3>Administrador logado com sucesso</h3>");
                    out.println("<h2>Nome: " + AdministradorLogado.getNome() + "</h2>");
                    out.println("<h2>CPF: " + AdministradorLogado.getCpf()+ "</h2>");
                    
                    
                %>


            </div>
        </div>
        <script src="view/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
