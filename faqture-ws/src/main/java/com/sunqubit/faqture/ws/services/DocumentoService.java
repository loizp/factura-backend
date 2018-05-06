package com.sunqubit.faqture.ws.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.ComprobantePago;
import com.sunqubit.faqture.core.beans.NotaDC;
import com.sunqubit.faqture.core.daos.contracts.IDocumentoDao;
import com.sunqubit.faqture.core.validators.ValidatorException;
import com.sunqubit.faqture.ws.RestEntitys.ApiRestFullResponse;
import com.sunqubit.faqture.ws.RestEntitys.RestFullResponseHeader;
import com.sunqubit.faqture.ws.validators.ComprobantePagoValidator;
import com.sunqubit.faqture.ws.validators.NotaDCValidator;

@Service
public class DocumentoService {
	
	@Autowired
	private IDocumentoDao documentoDao;

	@Autowired
	private ComprobantePagoValidator comprobantePagoValidator;
	
	@Autowired
	private NotaDCValidator notaDCValidator;
	
	public ApiRestFullResponse insert(ComprobantePago compPago) {
		Boolean ok = true;
        int code = 201;
        String msg = "El comprobante de pago fue registrado correctamente";
        try {
        	compPago.setFechaProceso(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        	compPago.setEstadoProceso("N");
        	comprobantePagoValidator.validaDocuNumero(compPago.getEmpresa(), compPago.getTipoDocumento(), compPago.getNumero());
        	comprobantePagoValidator.validaDocBaseSingle(compPago);
        	comprobantePagoValidator.validaDocuCliente(compPago.getCliente());
        	if(compPago.getClieSucursal() != null)
        		comprobantePagoValidator.validaDocuClieSucursal(compPago.getCliente(), compPago.getClieSucursal());
        	comprobantePagoValidator.validaDocuTipoOperacion(compPago.getTipoOperacion());
        	if(compPago.getVendedor() != null)
        		comprobantePagoValidator.validaDocuVendedor(compPago.getVendedor());
        	if(compPago.getEmailCliente() != null)
        		comprobantePagoValidator.validaDocuEmailCliente(compPago.getEmailCliente());
        	comprobantePagoValidator.validaDocAutoEmision(compPago.getEmpresa().getId(), compPago.getCliente().getId());
        	documentoDao.insert(compPago);
        } catch (ValidatorException ve) {
            ok = false;
            code = 400;
            msg = "No se puede registrar el comprobante de pago debido a: " + ve.getMessage();
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede registrar el comprobante de pago debido a: " + ex.getMessage();
        }
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), null);
	}
	
	public ApiRestFullResponse insert(NotaDC notaDC) {
		Boolean ok = true;
        int code = 201;
        String msg = "La nota fue registrado correctamente";
        
        try {
        	notaDC.setFechaProceso(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        	notaDC.setEstadoProceso("N");
        	
        	notaDCValidator.validaDocuNumero(notaDC.getEmpresa(), notaDC.getTipoDocumento(), notaDC.getNumero());
        	notaDCValidator.validaDocBaseSingle(notaDC);
        	notaDCValidator.validaDocuTipoNota(notaDC.getTipoDocumento(), notaDC.getTipoNota());
        	notaDCValidator.validaDocuSustentoNota(notaDC.getSustentoNota());
        	documentoDao.insert(notaDC);
        } catch (ValidatorException ve) {
            ok = false;
            code = 400;
            msg = "No se puede registrar la nota debido a: " + ve.getMessage();
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede registrar la nota debido a: " + ex.getMessage();
        }
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), null);
	}
	
	public ApiRestFullResponse getC(long id) {
	 	Boolean ok = true;
	 	int code = 200;
	 	String msg = "Entrega del documento solicitado";
	 	ComprobantePago res = null;
	 	try {
	 		res = documentoDao.getCompPago(id);
	 	} catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede obtener el comprobante de pago debido a: " + ex.getMessage();
        }
    	return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
	}
	
	public ApiRestFullResponse getN(long id) {
	 	Boolean ok = true;
	 	int code = 200;
	 	String msg = "Entrega del documento solicitado";
	 	NotaDC res = null;
	 	try {
	 		res = documentoDao.getNotaDC(id);
	 	} catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede obtener el comprobante de pago debido a: " + ex.getMessage();
        }
    	return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
	}
}
