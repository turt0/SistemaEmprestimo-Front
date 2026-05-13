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
    
    /**
     * Carrega uma ferramenta específica pelo seu id.
     */
    @WebMethod(operationName = "carregar")
    Ferramenta carregar(@WebParam(name = "id") int id);
    
    /**
     * Insere uma nova ferramenta. O id é gerado pelo servidor.
     */
    @WebMethod(operationName = "inserir")
    boolean inserir(@WebParam(name = "ferramenta") Ferramenta ferramenta);
    
    /**
     * Altera os dados de uma ferramenta existente.
     */
    @WebMethod(operationName = "alterar")
    boolean alterar(@WebParam(name = "ferramenta") Ferramenta ferramenta);
    
    /**
     * Remove uma ferramenta pelo seu id.
     */
    @WebMethod(operationName = "deletar")
    boolean deletar(@WebParam(name = "id") int id);
}
