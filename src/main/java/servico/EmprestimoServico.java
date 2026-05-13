
package servico;

import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import modelo.Emprestimo;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface EmprestimoServico {
    
    /**
     * Retorna a lista de todos os empréstimos.
     */
    @WebMethod(operationName = "listar")
    ArrayList<Emprestimo> listar();
    
    /**
     * Insere um novo empréstimo. O id é gerado pelo servidor.
     */
    @WebMethod(operationName = "inserir")
    boolean inserir(@WebParam(name = "emprestimo") Emprestimo emprestimo);
    
    /**
     * Altera um empréstimo existente.
     */
    @WebMethod(operationName = "alterar")
    boolean alterar(@WebParam(name = "emprestimo") Emprestimo emprestimo);
    
    /**
     * Remove um empréstimo pelo seu id.
     */
    @WebMethod(operationName = "deletar")
    boolean deletar(@WebParam(name = "id") int id);
    
    
    /**
     * Registra a devolução de um empréstimo.
     */
    @WebMethod(operationName = "registrarDevolucao")
    boolean registrarDevolucao(@WebParam(name = "id") int id);
    
    /**
     * Retorna o relatorio dos empréstimos.
     */
    @WebMethod(operationName = "gerarResumoRelatorio")
    String gerarResumoRelatorio();
}
