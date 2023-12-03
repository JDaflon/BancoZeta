<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="aplicacao.Comentario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="aplicacao.Categoria"%>
<%@page import="aplicacao.Usuario"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Comentário</title>
        <link href="view/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../../menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Comentario comentario = (Comentario) request.getAttribute("comentario");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Comentário</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Comentário</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Comentário</h1>");
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="ComentarioController" method="POST">
                        <input type="hidden" name="id" value="<%=comentario.getId()%>" class="form-control">
                        <div class="mb-3">
                            <label for="comentario" class="form-label">Comentario</label>
                            <textarea class="form-control" <%= acao.equals("Excluir") ? "Readonly" : ""%> name="comentario"><%=comentario.getComentario()%></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="dataPostagem" class="form-label" >Data Postagem</label>
                            <input type="date" name="dataPostagem" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=comentario.getData()%>" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="categoria" class="form-label" >Categoria da Postagem</label>
                            <select <%= acao.equals("Excluir") ? "Disabled" : ""%> class="form-select" name="categoria">
                                <%
                                    ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>) request.getAttribute("listaCategorias");
                                    for (Categoria categoria : listaCategorias) { %>
                                     <option  <%= categoria.getId() == comentario.getIdcategoria() ? "selected" :"" %> value="<%=categoria.getId() %>"><%=categoria.getDescricao() %></option>
                              <%  }%>    

                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="usuario" class="form-label" >Usuário da Postagem</label>
                            <select <%= acao.equals("Excluir") ? "Disabled" : ""%> class="form-select" name="usuario">
                                <%
                                    ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) request.getAttribute("listaUsuarios");
                                    for (Usuario usuario : listaUsuarios) { %>
                                     <option <%= usuario.getId() == comentario.getIdusuario()? "selected" :"" %> value="<%=usuario.getId() %>"><%=usuario.getNome() %></option>
                              <%  }%>    

                            </select>
                        </div>
                        
                        
                        
                        <div>
                            <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                            <a href="ComentarioController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>
                    <div>
                    </div>
                </div>
                 <script src="view/bootstrap/bootstrap.bundle.min.js"></script>
                </body>

                </html>