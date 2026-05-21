<%@page import="servico.EmprestimoServico"%>
<%@page import="servico.ControleServico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Relatório de Empréstimos</title>
    <link rel="stylesheet" type="text/css" href="estilo.css" />
</head>
<body>
    <h1>Relatório de Empréstimos</h1>

    <%
        try {
            EmprestimoServico servico = ControleServico.getEmprestimoServico();
            String resumo = servico.gerarResumoRelatorio();
    %>
        <pre style="background:#fff;padding:14px;border:1px solid #999;font-family:monospace;font-size:13px;">
<%= resumo %>
        </pre>
    <%
        } catch (Exception e) {
    %>
        <p class="mensagem" style="color:red;">
            Erro ao gerar relatório: <%= e.getMessage() %>
        </p>
    <%
        }
    %>

    <br>
    <a href="FrmGerenciarEmprestimo.jsp">Voltar ao gerenciamento</a> &nbsp;|&nbsp;
    <a href="index.jsp">Menu principal</a>
</body>
</html>
