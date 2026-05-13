package servico;
 
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import modelo.Amigo;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface AmigoServico {
    
    /**
     * Retorna a lista de todos os amigos cadastrados.
     */
    @WebMethod(operationName = "listar")
    ArrayList<Amigo> listar();
    
    /**
     * Carrega um amigo específico pelo seu id.
     * Retorna null se nenhum amigo for encontrado.
     */
    @WebMethod(operationName = "carregar")
    Amigo carregar(@WebParam(name = "id") int id);
    
    /**
     * Insere um novo amigo. O id é gerado pelo servidor.
     */
    @WebMethod(operationName = "inserir")
    boolean inserir(@WebParam(name = "amigo") Amigo amigo);
}
