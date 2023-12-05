<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entidade.Cliente"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Cliente</title>
        <link href="view/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../../menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Cliente cliente = (Cliente) request.getAttribute("cliente");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Usuário</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Usuário</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Usuário</h1>");
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="ClienteController" method="POST">
                        <input type="hidden" name="id" value="<%=cliente.getId()%>" class="form-control">
                        <div class="mb-3">
                            <label for="numConta" class="form-label" >Numero da Conta</label>
                            <input type="number" name="numConta" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getNumConta()%>" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" name="nome" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getNome()%>" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="cpf" class="form-label" >CPF</label>
                            <input type="text" name="cpf" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getCpf()%>" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="senha" class="form-label" >Senha</label>
                            <input type="text" name="senha" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getSenha()%>" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label" >Senha</label>
                            <input type="text" name="email" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=cliente.getEmail()%>" class="form-control">
                        </div>
                        <div>
                            <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                            <a href="ClienteController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>
                    <div>
                    </div>
                </div>
                <script src="view/bootstrap/bootstrap.bundle.min.js"></script>
                </body>

                </html>