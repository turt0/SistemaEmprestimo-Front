<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.Emprestimo"%>
<%@page import="servico.EmprestimoServico"%>
<%@page import="servico.ControleServico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gerenciar Empréstimos</title>
    <link rel="stylesheet" type="text/css" href="estilo.css" />
</head>
<body>
    <h1>Gerenciar Empréstimos</h1>

    <p>
        <a href="FrmCadastroEmprestimo.jsp">+ Novo Empréstimo</a>
        &nbsp;|&nbsp;
        <a href="FrmRelatorioEmprestimo.jsp">Ver relatório</a>
    </p>

    <%
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            EmprestimoServico servico = ControleServico.getEmprestimoServico();
            Emprestimo[] emprestimos = servico.listar();

            if (emprestimos == null || emprestimos.length == 0) {
    %>
        <p class="mensagem">Nenhum empréstimo cadastrado.</p>
    <%
            } else {
    %>
        <table>
            <tr>
                <th>Id</th>
                <th>Amigo</th>
                <th>Ferramenta</th>
                <th>Empréstimo</th>
                <th>Devolução prevista</th>
                <th>Devolução real</th>
                <th>Status</th>
                <th>Ações</th>
            </tr>
            <%
                for (Emprestimo e : emprestimos) {
                    boolean ativo = e.getDtDevolucaoReal() == null;
                    String status = ativo ? "ATIVO" : "DEVOLVIDO";
                    String corStatus = ativo ? "#c0392b" : "#27ae60";
            %>
            <tr>
                <td><%= e.getId() %></td>
                <td><%= e.getAmigo().getNome() %></td>
                <td><%= e.getFerramenta().getNome() %></td>
                <td><%= sdf.format(e.getDtEmprestimo()) %></td>
                <td><%= sdf.format(e.getDtDevolucaoPrevista()) %></td>
                <td><%= e.getDtDevolucaoReal() != null ? sdf.format(e.getDtDevolucaoReal()) : "-" %></td>
                <td style="color:<%= corStatus %>;font-weight:bold;"><%= status %></td>
                <td>
                    <% if (ativo) { %>
                        <a href="servlet/EmprestimoDevolver?id=<%= e.getId() %>"
                           onclick="return confirm('Registrar devolução desta ferramenta?');">Devolver</a>
                        &nbsp;|&nbsp;
                    <% } %>
                    <a href="FrmCadastroEmprestimo.jsp?id=<%= e.getId() %>">Editar</a>
                    &nbsp;|&nbsp;
                    <a href="servlet/EmprestimoExcluir?id=<%= e.getId() %>"
                       onclick="return confirm('Excluir este empréstimo?');">Excluir</a>
                </td>
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
            Erro ao listar empréstimos: <%= e.getMessage() %>
        </p>
        <p>Verifique se o backend (webservice_emprestimo_jaxws_soap_tomcat) está rodando.</p>
    <%
        }
    %>

    <br>
    <a href="index.jsp">Voltar ao menu</a>
</body>
</html>
