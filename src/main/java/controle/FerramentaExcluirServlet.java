package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servico.FerramentaServico;
import servico.ControleServico;

@WebServlet("/servlet/FerramentaExcluir")
public class FerramentaExcluirServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>Cadastro de Ferramenta - Excluir</title>");
        out.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/estilo.css' />");
        out.println("</head><body>");
        out.println("<h1>Cadastro de Ferramenta - Excluir</h1>");

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            FerramentaServico servico = ControleServico.getFerramentaServico();
            boolean sucesso = servico.deletar(id);

            if (sucesso) {
                out.println("<p class='mensagem'>Exclusão realizada com sucesso.</p>");
            } else {
                out.println("<p class='mensagem' style='color:red;'>");
                out.println("Exclusão não realizada. A ferramenta pode estar em um empréstimo.");
                out.println("</p>");
            }
        } catch (Exception e) {
            getServletContext().log("Erro em FerramentaExcluir", e);
            out.println("<p class='mensagem' style='color:red;'>");
            out.println("Erro: " + e.getMessage());
            out.println("</p>");
        }

        out.println("<br><a href='" + request.getContextPath() + "/FrmGerenciarFerramenta.jsp'>Gerenciar</a>");
        out.println(" &nbsp;|&nbsp; <a href='" + request.getContextPath() + "/index.jsp'>Menu</a>");
        out.println("</body></html>");
    }
}
