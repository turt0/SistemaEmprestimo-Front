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

@WebServlet("/servlet/EmprestimoIncluir")
public class EmprestimoIncluirServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<title>Cadastro de Emprestimo - Incluir</title>");
        out.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/estilo.css' />");
        out.println("</head><body>");
        out.println("<h1>Cadastro de Empréstimo - Incluir</h1>");

        try {
            int idAmigo = Integer.parseInt(request.getParameter("idAmigo"));
            int idFerramenta = Integer.parseInt(request.getParameter("idFerramenta"));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dtEmprestimo = sdf.parse(request.getParameter("dtEmprestimo"));
            Date dtDevolucaoPrevista = sdf.parse(request.getParameter("dtDevolucaoPrevista"));

            EmprestimoServico servico = ControleServico.getEmprestimoServico();

            // Monta o Amigo e a Ferramenta só com o id (o backend já tem os dados completos)
            Amigo amigo = new Amigo();
            amigo.setId(idAmigo);

            Ferramenta ferramenta = new Ferramenta();
            ferramenta.setId(idFerramenta);

            Emprestimo emprestimo = new Emprestimo(
                    0, dtEmprestimo, dtDevolucaoPrevista, null, amigo, ferramenta);

            boolean sucesso = servico.inserir(emprestimo);
            if (sucesso) {
                out.println("<p class='mensagem'>Empréstimo cadastrado com sucesso.</p>");
                // Aviso passivo: se o amigo já tinha pendência, informa após o cadastro
                if (servico.amigoTemPendencia(idAmigo)) {
                    out.println("<p class='mensagem' style='color:#c0392b;'>");
                    out.println("Aviso: este amigo possui outros empréstimos ainda não devolvidos.");
                    out.println("</p>");
                }
            } else {
                out.println("<p class='mensagem' style='color:red;'>Cadastro não realizado.</p>");
            }
        } catch (Exception e) {
            getServletContext().log("Erro em EmprestimoIncluir", e);
            out.println("<p class='mensagem' style='color:red;'>");
            out.println("Erro: " + e.getMessage());
            out.println("</p>");
        }

        out.println("<br><a href='" + request.getContextPath() + "/FrmCadastroEmprestimo.jsp'>Novo empréstimo</a>");
        out.println(" &nbsp;|&nbsp; <a href='" + request.getContextPath() + "/FrmGerenciarEmprestimo.jsp'>Gerenciar</a>");
        out.println(" &nbsp;|&nbsp; <a href='" + request.getContextPath() + "/index.jsp'>Menu</a>");
        out.println("</body></html>");
    }
}