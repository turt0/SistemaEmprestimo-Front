package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Amigo;
import servico.AmigoServico;
import servico.ControleServico;

@WebServlet("/servlet/AmigoIncluir")
public class AmigoIncluirServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>Cadastro de Amigo - Incluir</title>");
        out.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/estilo.css' />");
        out.println("</head><body>");
        out.println("<h1>Cadastro de Amigo - Incluir</h1>");

        try {
            String nome = request.getParameter("nome");
            String telefone = request.getParameter("telefone");

            // ID = 0 -> o backend gera o próximo ID
            Amigo amigo = new Amigo(0, nome, telefone);

            AmigoServico servico = ControleServico.getAmigoServico();
            boolean sucesso = servico.inserir(amigo);

            if (sucesso) {
                out.println("<p class='mensagem'>Inclusão realizada com sucesso.</p>");
            } else {
                out.println("<p class='mensagem' style='color:red;'>Inclusão não realizada.</p>");
            }
        } catch (Exception e) {
            getServletContext().log("Erro em AmigoIncluir", e);
            out.println("<p class='mensagem' style='color:red;'>");
            out.println("Erro: " + e.getMessage());
            out.println("</p>");
        }

        out.println("<br><a href='" + request.getContextPath() + "/FrmCadastroAmigo.jsp'>Novo amigo</a>");
        out.println(" &nbsp;|&nbsp; <a href='" + request.getContextPath() + "/FrmGerenciarAmigo.jsp'>Gerenciar</a>");
        out.println(" &nbsp;|&nbsp; <a href='" + request.getContextPath() + "/index.jsp'>Menu</a>");
        out.println("</body></html>");
    }
}