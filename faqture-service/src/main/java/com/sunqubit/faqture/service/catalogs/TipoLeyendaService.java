package com.sunqubit.faqture.service.catalogs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.beans.catalogs.TipoLeyenda;
import com.sunqubit.faqture.beans.rest.ApiRestFullResponse;
import com.sunqubit.faqture.beans.rest.RestFullResponseHeader;
import com.sunqubit.faqture.dao.contracts.ITipoLeyendaDao;

@Service
public class TipoLeyendaService {
	
	@Autowired
	private ITipoLeyendaDao tipoLeyendaDao;
	
	public ApiRestFullResponse getAll() {
		Boolean ok = true;
		int code = 200;
		String msg = "Entrega de listado de los tipos de leyendas";
		List<TipoLeyenda> res = null;
		try {
			res = tipoLeyendaDao.getAll();
		} catch (Exception ex) {
			ok = false;
			code = 500;
			msg = "No se puede obtener los tipos de leyendas debido a: " + ex.getMessage();
		} 
		return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
	}
}
