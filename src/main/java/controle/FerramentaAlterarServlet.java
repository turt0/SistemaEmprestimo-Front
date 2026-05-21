package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Ferramenta;
import servico.FerramentaServico;
import servico.ControleServico;

@WebServlet("/servlet/FerramentaAlterar")
public class FerramentaAlterarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>Cadastro de Ferramenta - Alterar</title>");
        out.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/estilo.css' />");
        out.println("</head><body>");
        out.println("<h1>Cadastro de Ferramenta - Alterar</h1>");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String marca = request.getParameter("marca");
            double custo = Double.parseDouble(request.getParameter("custo"));

            Ferramenta ferramenta = new Ferramenta(id, nome, marca, custo);

            FerramentaServico servico = ControleServico.getFerramentaServico();
            boolean sucesso = servico.alterar(ferramenta);

            if (sucesso) {
                out.println("<p class='mensagem'>Alteração realizada com sucesso.</p>");
            } else {
                out.println("<p class='mensagem' style='color:red;'>Alteração não realizada.</p>");
            }
        } catch (Exception e) {
            getServletContext().log("Erro em FerramentaAlterar", e);
            out.println("<p class='mensagem' style='color:red;'>");
            out.println("Erro: " + e.getMessage());
            out.println("</p>");
        }

        out.println("<br><a href='" + request.getContextPath() + "/FrmGerenciarFerramenta.jsp'>Gerenciar</a>");
        out.println(" &nbsp;|&nbsp; <a href='" + request.getContextPath() + "/index.jsp'>Menu</a>");
        out.println("</body></html>");
    }
}
