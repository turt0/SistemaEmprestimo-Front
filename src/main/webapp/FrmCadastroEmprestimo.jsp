<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="modelo.Amigo"%>
<%@page import="modelo.Ferramenta"%>
<%@page import="modelo.Emprestimo"%>
<%@page import="servico.AmigoServico"%>
<%@page import="servico.FerramentaServico"%>
<%@page import="servico.EmprestimoServico"%>
<%@page import="servico.ControleServico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cadastro de Empréstimo</title>
    <link rel="stylesheet" type="text/css" href="estilo.css" />
</head>
<body>
    <%
        SimpleDateFormat sdfIso = new SimpleDateFormat("yyyy-MM-dd");

        Emprestimo emprestimoEdit = null;
        boolean edicao = false;

        // Em edição, carrega o empréstimo a partir da lista (não há carregar(id) no serviço)
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                EmprestimoServico srvEmp = ControleServico.getEmprestimoServico();
                Emprestimo[] todos = srvEmp.listar();
                if (todos != null) {
                    for (Emprestimo e : todos) {
                        if (e.getId() == id) {
                            emprestimoEdit = e;
                            edicao = true;
                            break;
                        }
                    }
                }
            } catch (Exception e) {
    %>
        <p class="mensagem" style="color:red;">
            Erro ao carregar empréstimo: <%= e.getMessage() %>
        </p>
    <%
            }
        }

        String actionUrl = edicao ? "servlet/EmprestimoAlterar" : "servlet/EmprestimoIncluir";
        String titulo = edicao ? "Editar Empréstimo" : "Novo Empréstimo";

        int idAmigoSel = (emprestimoEdit != null && emprestimoEdit.getAmigo() != null)
                ? emprestimoEdit.getAmigo().getId() : 0;
        int idFerramentaSel = (emprestimoEdit != null && emprestimoEdit.getFerramenta() != null)
                ? emprestimoEdit.getFerramenta().getId() : 0;
        String dtEmprestimoSel = (emprestimoEdit != null && emprestimoEdit.getDtEmprestimo() != null)
                ? sdfIso.format(emprestimoEdit.getDtEmprestimo())
                : sdfIso.format(new Date());
        String dtDevPrevSel = (emprestimoEdit != null && emprestimoEdit.getDtDevolucaoPrevista() != null)
                ? sdfIso.format(emprestimoEdit.getDtDevolucaoPrevista()) : "";
    %>

    <h1><%= titulo %></h1>

    <%
        Amigo[] amigos = null;
        Ferramenta[] ferramentas = null;
        EmprestimoServico srvEmprestimo = null;
        try {
            amigos = ControleServico.getAmigoServico().listar();
            ferramentas = ControleServico.getFerramentaServico().listar();
            srvEmprestimo = ControleServico.getEmprestimoServico();
        } catch (Exception e) {
    %>
        <p class="mensagem" style="color:red;">
            Erro ao carregar dados de amigos/ferramentas: <%= e.getMessage() %>
        </p>
    <%
        }

        if (amigos == null || amigos.length == 0) {
    %>
        <p class="mensagem" style="color:red;">
            Não há amigos cadastrados.
            <a href="FrmCadastroAmigo.jsp">Cadastrar um amigo primeiro</a>.
        </p>
    <%
        } else if (ferramentas == null || ferramentas.length == 0) {
    %>
        <p class="mensagem" style="color:red;">
            Não há ferramentas cadastradas.
            <a href="FrmCadastroFerramenta.jsp">Cadastrar uma ferramenta primeiro</a>.
        </p>
    <%
        } else {
    %>

    <form name="FrmCadastroEmprestimo" method="post" action="<%= actionUrl %>">
        <input type="hidden" name="id" value="<%= emprestimoEdit != null ? emprestimoEdit.getId() : 0 %>">

        <p>
            Amigo:
            <select name="idAmigo" required>
                <option value="">-- selecione --</option>
                <%
                    for (Amigo a : amigos) {
                        // Verifica pendência via SOAP. Se a chamada falhar, segue sem marcar.
                        boolean pendente = false;
                        try {
                            pendente = srvEmprestimo.amigoTemPendencia(a.getId());
                        } catch (Exception ex) {
                            // silencioso: pendência é informativa, não crítica
                        }
                        String marcador = pendente ? " \u26A0 com pendencia" : "";
                %>
                    <option value="<%= a.getId() %>"
                            <%= (a.getId() == idAmigoSel) ? "selected" : "" %>
                            <%= pendente ? "style='color:#c0392b;'" : "" %>>
                        <%= a.getNome() %> (<%= a.getTelefone() %>)<%= marcador %>
                    </option>
                <% } %>
            </select>
        </p>
        <p style="font-size:12px;color:#666;">
            ⚠ indica amigo com empréstimo ainda não devolvido (apenas aviso).
        </p>
        <p>
            Ferramenta:
            <select name="idFerramenta" required>
                <option value="">-- selecione --</option>
                <% for (Ferramenta f : ferramentas) { %>
                    <option value="<%= f.getId() %>"
                            <%= (f.getId() == idFerramentaSel) ? "selected" : "" %>>
                        <%= f.getNome() %> - <%= f.getMarca() %>
                    </option>
                <% } %>
            </select>
        </p>
        <p>
            Data do empréstimo:
            <input type="date" name="dtEmprestimo" required
                   value="<%= dtEmprestimoSel %>">
        </p>
        <p>
            Data prevista de devolução:
            <input type="date" name="dtDevolucaoPrevista" required
                   value="<%= dtDevPrevSel %>">
        </p>
        <p>
            <input type="reset" value="Limpar">
            <input type="submit" value="<%= edicao ? "Salvar" : "Cadastrar" %>">
        </p>
    </form>

    <% } %>

    <br>
    <a href="FrmGerenciarEmprestimo.jsp">Voltar ao gerenciamento</a> &nbsp;|&nbsp;
    <a href="index.jsp">Menu principal</a>
</body>
</html>
