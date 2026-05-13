
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
    
    /**
     * Retorna o serviço de Amigo.
     */
    public static AmigoServico getAmigoServico() {
        try {
            URL url = new URL(URL_BASE + "/AmigoServico?wsdl");
            QName qName = new QName(NAMESPACE, "AmigoServicoImplService");
            Service service = Service.create(url, qName);
            return service.getPort(AmigoServico.class);
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL do AmigoServico inválida.", e);
        }
    }
    
    /**
     * Retorna o serviço de Ferramenta.
     */
    public static FerramentaServico getFerramentaServico() {
        try {
            URL url = new URL(URL_BASE + "/FerramentaServico?wsdl");
            QName qName = new QName(NAMESPACE, "FerramentaServicoImplService");
            Service service = Service.create(url, qName);
            return service.getPort(FerramentaServico.class);
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL do FerramentaServico inválida.", e);
        }
    }
}
