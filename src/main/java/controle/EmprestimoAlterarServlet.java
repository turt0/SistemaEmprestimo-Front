package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Amigo;
import modelo.Emprestimo;
import modelo.Ferramenta;
import servico.ControleServico;
import servico.EmprestimoServico;

@WebServlet("/servlet/EmprestimoAlterar")
public class EmprestimoAlterarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>Cadastro de Emprestimo - Alterar</title>");
        out.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/estilo.css' />");
        out.println("</head><body>");
        out.println("<h1>Cadastro de Empréstimo - Alterar</h1>");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int idAmigo = Integer.parseInt(request.getParameter("idAmigo"));
            int idFerramenta = Integer.parseInt(request.getParameter("idFerramenta"));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dtEmprestimo = sdf.parse(request.getParameter("dtEmprestimo"));
            Date dtDevolucaoPrevista = sdf.parse(request.getParameter("dtDevolucaoPrevista"));

            Amigo amigo = new Amigo();
            amigo.setId(idAmigo);

            Ferramenta ferramenta = new Ferramenta();
            ferramenta.setId(idFerramenta);

            // Na alteração, preserva o status da devolução real (não mexe nela aqui)
            Emprestimo emprestimo = new Emprestimo(
                    id, dtEmprestimo, dtDevolucaoPrevista, null, amigo, ferramenta);

            EmprestimoServico servico = ControleServico.getEmprestimoServico();
            boolean sucesso = servico.alterar(emprestimo);

            if (sucesso) {
                out.println("<p class='mensagem'>Alteração realizada com sucesso.</p>");
            } else {
                out.println("<p class='mensagem' style='color:red;'>Alteração não realizada.</p>");
            }
        } catch (Exception e) {
            getServletContext().log("Erro em EmprestimoAlterar", e);
            out.println("<p class='mensagem' style='color:red;'>");
            out.println("Erro: " + e.getMessage());
            out.println("</p>");
        }

        out.println("<br><a href='" + request.getContextPath() + "/FrmGerenciarEmprestimo.jsp'>Gerenciar</a>");
        out.println(" &nbsp;|&nbsp; <a href='" + request.getContextPath() + "/index.jsp'>Menu</a>");
        out.println("</body></html>");
    }
}
