<%@page import="modelo.Ferramenta"%>
<%@page import="servico.FerramentaServico"%>
<%@page import="servico.ControleServico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gerenciar Ferramentas</title>
    <link rel="stylesheet" type="text/css" href="estilo.css" />
</head>
<body>
    <h1>Gerenciar Ferramentas</h1>

    <p><a href="FrmCadastroFerramenta.jsp">+ Nova Ferramenta</a></p>

    <%
        try {
            FerramentaServico servico = ControleServico.getFerramentaServico();
            Ferramenta[] ferramentas = servico.listar();

            if (ferramentas == null || ferramentas.length == 0) {
    %>
        <p class="mensagem">Nenhuma ferramenta cadastrada.</p>
    <%
            } else {
                double totalGasto = servico.getTotalGasto();
    %>
        <table>
            <tr>
                <th>Id</th>
                <th>Nome</th>
                <th>Marca</th>
                <th>Custo (R$)</th>
                <th>Editar</th>
                <th>Excluir</th>
            </tr>
            <%
                for (Ferramenta ferramenta : ferramentas) {
            %>
            <tr>
                <td><%= ferramenta.getId() %></td>
                <td><%= ferramenta.getNome() %></td>
                <td><%= ferramenta.getMarca() %></td>
                <td><%= String.format("%.2f", ferramenta.getCusto()) %></td>
                <td><a href="FrmCadastroFerramenta.jsp?id=<%= ferramenta.getId() %>">Editar</a></td>
                <td><a href="servlet/FerramentaExcluir?id=<%= ferramenta.getId() %>"
                       onclick="return confirm('Excluir esta ferramenta?');">Excluir</a></td>
            </tr>
            <%
                }
            %>
        </table>
        <p><strong>Custo total: R$ <%= String.format("%.2f", totalGasto) %></strong></p>
    <%
            }
        } catch (Exception e) {
    %>
        <p class="mensagem" style="color:red;">
            Erro ao listar ferramentas: <%= e.getMessage() %>
        </p>
        <p>Verifique se o backend (webservice_emprestimo_jaxws_soap_tomcat) está rodando.</p>
    <%
        }
    %>

    <br>
    <a href="index.jsp">Voltar ao menu</a>
</body>
</html>
