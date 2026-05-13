
package servico;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class ControleServico {
    
     /**
     * URL base do servidor de webservices. Centralizada aqui para que
     * uma mudança de host/porta exija alteração em apenas um lugar.
     */
    private static final String URL_BASE = "http://localhost:8080/webservice_emprestimo_jaxws_soap_tomcat";
    
    /**
     * Namespace dos serviços. Como as classes do backend estão no pacote
     * "servico", o namespace gerado pelo JAX-WS é "http://servico/".
     */
    private static final String NAMESPACE = "http://servico/";
    
}
