package com.sunqubit.faqture.ws.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.TipoNota;
import com.sunqubit.faqture.core.daos.contracts.ITipoNotaDao;
import com.sunqubit.faqture.ws.RestEntitys.ApiRestFullResponse;
import com.sunqubit.faqture.ws.RestEntitys.RestFullResponseHeader;

@Service
public class TipoNotaService {
	
	@Autowired
    private ITipoNotaDao tipoNotaDao;
	
	public ApiRestFullResponse getAll() {
		Boolean ok = true;
		int code = 200;
		String msg = "Entrega de listado de los tipos de notas";
		List<TipoNota> res = null;
		try {
			res = tipoNotaDao.getAll();
		} catch (Exception ex) {
			ok = false;
			code = 400;
			msg = "No se puede obtener los tipos de notas a: " + ex.getMessage();
		} 
		return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
	
	
}
