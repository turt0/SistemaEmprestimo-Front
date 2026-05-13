package servico;
 
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import modelo.Ferramenta;
 

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface FerramentaServico {
    
    /**
     * Retorna a lista de todas as ferramentas cadastradas.
     */
    @WebMethod(operationName = "listar")
    ArrayList<Ferramenta> listar();
    
}
