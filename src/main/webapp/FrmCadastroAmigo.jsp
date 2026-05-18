<%@page import="modelo.Amigo"%>
<%@page import="servico.AmigoServico"%>
<%@page import="servico.ControleServico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cadastro de Amigo</title>
    <link rel="stylesheet" type="text/css" href="estilo.css" />
</head>
<body>
    <%
        Amigo amigo = null;
        boolean edicao = false;

        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                AmigoServico servico = ControleServico.getAmigoServico();
                amigo = servico.carregar(id);
                edicao = (amigo != null && amigo.getId() != 0);
            } catch (Exception e) {
    %>
        <p class="mensagem" style="color:red;">
            Erro ao carregar amigo: <%= e.getMessage() %>
        </p>
    <%
            }
        }

        String actionUrl = edicao ? "servlet/AmigoAlterar" : "servlet/AmigoIncluir";
        String titulo = edicao ? "Editar Amigo" : "Novo Amigo";
    %>

    <h1><%= titulo %></h1>

    <form name="FrmCadastroAmigo" method="post" action="<%= actionUrl %>">
        <input type="hidden" name="id" value="<%= amigo != null ? amigo.getId() : 0 %>">

        <p>
            Nome:
            <input type="text" name="nome" size="50" maxlength="100" required
                   value="<%= amigo != null ? amigo.getNome() : "" %>">
        </p>
        <p>
            Telefone:
            <input type="text" name="telefone" size="20" maxlength="20"
                   value="<%= amigo != null ? amigo.getTelefone() : "" %>">
        </p>
        <p>
            <input type="reset" value="Limpar">
            <input type="submit" value="<%= edicao ? "Salvar" : "Cadastrar" %>">
        </p>
    </form>

    <br>
    <a href="FrmGerenciarAmigo.jsp">Voltar ao gerenciamento</a> &nbsp;|&nbsp;
    <a href="index.jsp">Menu principal</a>
</body>
</html>
