<%@page import="modelo.Amigo"%>
<%@page import="servico.AmigoServico"%>
<%@page import="servico.ControleServico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gerenciar Amigos</title>
    <link rel="stylesheet" type="text/css" href="estilo.css" />
</head>
<body>
    <h1>Gerenciar Amigos</h1>

    <p><a href="FrmCadastroAmigo.jsp">+ Novo Amigo</a></p>

    <%
        try {
            AmigoServico servico = ControleServico.getAmigoServico();
            Amigo[] amigos = servico.listar();

            if (amigos == null || amigos.length == 0) {
    %>
        <p class="mensagem">Nenhum amigo cadastrado.</p>
    <%
            } else {
    %>
        <table>
            <tr>
                <th>Id</th>
                <th>Nome</th>
                <th>Telefone</th>
                <th>Editar</th>
                <th>Excluir</th>
            </tr>
            <%
                for (Amigo amigo : amigos) {
            %>
            <tr>
                <td><%= amigo.getId() %></td>
                <td><%= amigo.getNome() %></td>
                <td><%= amigo.getTelefone() %></td>
                <td><a href="FrmCadastroAmigo.jsp?id=<%= amigo.getId() %>">Editar</a></td>
                <td><a href="servlet/AmigoExcluir?id=<%= amigo.getId() %>"
                       onclick="return confirm('Excluir este amigo?');">Excluir</a></td>
            </tr>
            <%
                }
            %>
        </table>
    <%
            }
        } catch (Exception e) {
    %>
        <p class="mensagem" style="color:red;">
            Erro ao listar amigos: <%= e.getMessage() %>
        </p>
        <p>Verifique se o backend (webservice_emprestimo_jaxws_soap_tomcat) está rodando.</p>
    <%
        }
    %>

    <br>
    <a href="index.jsp">Voltar ao menu</a>
</body>
</html>
