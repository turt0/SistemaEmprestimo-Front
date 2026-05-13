
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
    
}
