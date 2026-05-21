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

@WebServlet("/servlet/AmigoAlterar")
public class AmigoAlterarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>Cadastro de Amigo - Alterar</title>");
        out.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/estilo.css' />");
        out.println("</head><body>");
        out.println("<h1>Cadastro de Amigo - Alterar</h1>");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String telefone = request.getParameter("telefone");

            Amigo amigo = new Amigo(id, nome, telefone);

            AmigoServico servico = ControleServico.getAmigoServico();
            boolean sucesso = servico.alterar(amigo);

            if (sucesso) {
                out.println("<p class='mensagem'>Alteração realizada com sucesso.</p>");
            } else {
                out.println("<p class='mensagem' style='color:red;'>Alteração não realizada.</p>");
            }
        } catch (Exception e) {
            getServletContext().log("Erro em AmigoAlterar", e);
            out.println("<p class='mensagem' style='color:red;'>");
            out.println("Erro: " + e.getMessage());
            out.println("</p>");
        }

        out.println("<br><a href='" + request.getContextPath() + "/FrmGerenciarAmigo.jsp'>Gerenciar</a>");
        out.println(" &nbsp;|&nbsp; <a href='" + request.getContextPath() + "/index.jsp'>Menu</a>");
        out.println("</body></html>");
    }
}