package com.sunqubit.faqture.ws.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.TipoDocumento;
import com.sunqubit.faqture.core.daos.contracts.ItipoDocumentoDao;
import com.sunqubit.faqture.ws.RestEntitys.ApiRestFullResponse;
import com.sunqubit.faqture.ws.RestEntitys.RestFullResponseHeader;

@Service
public class TipoDocumentoService {

	@Autowired
	private ItipoDocumentoDao tipoDocumentoDao;
	
	public ApiRestFullResponse getAll() {
		Boolean ok = true;
		int code = 200;
		String msg = "Entrega de listado de los tipos de documentos";
		List<TipoDocumento> res = null;
		try {
			res = tipoDocumentoDao.getAll();
		} catch (Exception ex) {
			ok = false;
			code = 500;
			msg = "No se puede obtener los tipos de documentos debido a: " + ex.getMessage();
		} 
		return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
	
}
