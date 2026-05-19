<%@page import="modelo.Ferramenta"%>
<%@page import="servico.FerramentaServico"%>
<%@page import="servico.ControleServico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cadastro de Ferramenta</title>
    <link rel="stylesheet" type="text/css" href="estilo.css" />
</head>
<body>
    <%
        Ferramenta ferramenta = null;
        boolean edicao = false;

        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                FerramentaServico servico = ControleServico.getFerramentaServico();
                ferramenta = servico.carregar(id);
                edicao = (ferramenta != null && ferramenta.getId() != 0);
            } catch (Exception e) {
    %>
        <p class="mensagem" style="color:red;">
            Erro ao carregar ferramenta: <%= e.getMessage() %>
        </p>
    <%
            }
        }

        String actionUrl = edicao ? "servlet/FerramentaAlterar" : "servlet/FerramentaIncluir";
        String titulo = edicao ? "Editar Ferramenta" : "Nova Ferramenta";
    %>

    <h1><%= titulo %></h1>

    <form name="FrmCadastroFerramenta" method="post" action="<%= actionUrl %>">
        <input type="hidden" name="id" value="<%= ferramenta != null ? ferramenta.getId() : 0 %>">

        <p>
            Nome:
            <input type="text" name="nome" size="50" maxlength="100" required
                   value="<%= ferramenta != null ? ferramenta.getNome() : "" %>">
        </p>
        <p>
            Marca:
            <input type="text" name="marca" size="30" maxlength="50"
                   value="<%= ferramenta != null ? ferramenta.getMarca() : "" %>">
        </p>
        <p>
            Custo (R$):
            <input type="number" name="custo" step="0.01" min="0" size="15" required
                   value="<%= ferramenta != null ? ferramenta.getCusto() : "" %>">
        </p>
        <p>
            <input type="reset" value="Limpar">
            <input type="submit" value="<%= edicao ? "Salvar" : "Cadastrar" %>">
        </p>
    </form>

    <br>
    <a href="FrmGerenciarFerramenta.jsp">Voltar ao gerenciamento</a> &nbsp;|&nbsp;
    <a href="index.jsp">Menu principal</a>
</body>
</html>
