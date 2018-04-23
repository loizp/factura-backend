package com.sunqubit.faqture.ws.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.TipoIsc;
import com.sunqubit.faqture.core.daos.contracts.ITipoIscDao;
import com.sunqubit.faqture.ws.RestEntitys.ApiRestFullResponse;
import com.sunqubit.faqture.ws.RestEntitys.RestFullResponseHeader;

@Service
public class TipoIscService {
	
	@Autowired
    private ITipoIscDao tipoIscDao;
	
	public ApiRestFullResponse getAll() {
		Boolean ok = true;
		int code = 200;
		String msg = "Entrega de listado de los tipos de ISC";
		List<TipoIsc> res = null;
		try {
			res = tipoIscDao.getAll();
		} catch (Exception ex) {
			ok = false;
			code = 400;
			msg = "No se puede obtener los tipos de ISC debido a: " + ex.getMessage();
		} 
		return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
	
}
