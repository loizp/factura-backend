package com.sunqubit.faqture.service.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.beans.core.DetalleDocumento;
import com.sunqubit.faqture.beans.rest.ApiRestFullResponse;
import com.sunqubit.faqture.beans.rest.RestFullResponseHeader;
import com.sunqubit.faqture.dao.contracts.IDetalleDocumentoDao;
import com.sunqubit.faqture.dao.validators.ValidatorException;
import com.sunqubit.faqture.service.validators.DetalleDocValidator;

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
