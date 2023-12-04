<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Cliente" %>

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

                <h1>Área Restrita</h1>
                <%
                    Cliente clienteLogado = (Cliente) session.getAttribute("cliente");
                    out.println("<h3>Cliente logado com sucesso</h3>");
                    out.println("<h2>Nome: " + clienteLogado.getNome() + "</h2>");
                    out.println("<h2>CPF: " + clienteLogado.getCpf()+ "</h2>");
                    out.println("<h2>Numero da conta: " + clienteLogado.getNumConta() + "</h2>");
                    out.println("<h2>Saldo: R$ " + clienteLogado.getSaldo() + "</h2>");
                    
                    
                %>


            </div>
        </div>
        <script src="view/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
