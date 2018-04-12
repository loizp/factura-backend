package com.sunqubit.faqture.ws.services;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.TipoDocumentoIdentidad;
import com.sunqubit.faqture.core.daos.contracts.ITipoDocumentoIdentidadDao;

@Service("tipoDocumentoIdentidadService")
public class TipoDocumentoIdentidadService {
	
	public List<TipoDocumentoIdentidad> getAll(){
		ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-config.xml");
		ITipoDocumentoIdentidadDao tipoDocumentoIdentidadDao = (ITipoDocumentoIdentidadDao) context.getBean("tipoDocumentoIdentidadDao");
		List<TipoDocumentoIdentidad> tiposDocumentosIdentidad = tipoDocumentoIdentidadDao.getAll();
		return tiposDocumentosIdentidad;
	}
}
