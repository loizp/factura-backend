package pe.gob.sunat.servicio.registro.comppago.factura.gem.servicesqa;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "billService", targetNamespace = "http://service.gem.factura.comppago.registro.servicio.sunat.gob.pe/", wsdlLocation = "https://www.sunat.gob.pe/ol-ti-itcpgem-sqa/billService?wsdl")
public class BillService_Service_sqa extends Service {
	private final static URL BILLSERVICE_WSDL_LOCATION;
    private final static WebServiceException BILLSERVICE_EXCEPTION;
    private final static QName BILLSERVICE_QNAME = new QName("http://service.gem.factura.comppago.registro.servicio.sunat.gob.pe/", "billService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://www.sunat.gob.pe/ol-ti-itcpgem-sqa/billService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        BILLSERVICE_WSDL_LOCATION = url;
        BILLSERVICE_EXCEPTION = e;
    }

    public BillService_Service_sqa() {
        super(__getWsdlLocation(), BILLSERVICE_QNAME);
    }

    public BillService_Service_sqa(WebServiceFeature... features) {
        super(__getWsdlLocation(), BILLSERVICE_QNAME, features);
    }

    public BillService_Service_sqa(URL wsdlLocation) {
        super(wsdlLocation, BILLSERVICE_QNAME);
    }

    public BillService_Service_sqa(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BILLSERVICE_QNAME, features);
    }

    public BillService_Service_sqa(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BillService_Service_sqa(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns BillService
     */
    @WebEndpoint(name = "BillServicePort")
    public BillService getBillServicePort() {
        return super.getPort(new QName("http://service.gem.factura.comppago.registro.servicio.sunat.gob.pe/", "BillServicePort"), BillService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BillService
     */
    @WebEndpoint(name = "BillServicePort")
    public BillService getBillServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.gem.factura.comppago.registro.servicio.sunat.gob.pe/", "BillServicePort"), BillService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BILLSERVICE_EXCEPTION!= null) {
            throw BILLSERVICE_EXCEPTION;
        }
        return BILLSERVICE_WSDL_LOCATION;
    }
}
