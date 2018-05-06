package com.sunqubit.faqture.ws.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.DetalleDocumento;
import com.sunqubit.faqture.core.daos.contracts.IDetalleDocumentoDao;
import com.sunqubit.faqture.core.validators.ValidatorException;
import com.sunqubit.faqture.ws.RestEntitys.ApiRestFullResponse;
import com.sunqubit.faqture.ws.RestEntitys.RestFullResponseHeader;
import com.sunqubit.faqture.ws.validators.DetalleDocValidator;

@Service
public class DetalleDocumentoService {
	
	@Autowired
	private IDetalleDocumentoDao detalleDocumentoDao;
	
	@Autowired
	private DetalleDocValidator detalleDocValidator;
	
	public ApiRestFullResponse insert(DetalleDocumento detalleDocumento) {
		Boolean ok = true;
        int code = 201;
        String msg = "El item de documento fue registrado correctamente";
        
        try {
        	detalleDocValidator.validaDedoCompPago(detalleDocumento.getCpmprobanteDocumento());
        	detalleDocValidator.validaDedoOrden(detalleDocumento.getId());
        	detalleDocValidator.validaDedoDescripcion(detalleDocumento.getDescripcion());
        	
        	detalleDocumentoDao.insert(detalleDocumento);
        } catch (ValidatorException ve) {
            ok = false;
            code = 400;
            msg = "No se puede registrar el item debido a: " + ve.getMessage();
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede registrar el item debido a: " + ex.getMessage();
        }
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), null);
	}
}
