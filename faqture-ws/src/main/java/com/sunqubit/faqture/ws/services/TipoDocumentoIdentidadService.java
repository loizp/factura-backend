package com.sunqubit.faqture.ws.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.TipoDocumentoIdentidad;
import com.sunqubit.faqture.core.daos.contracts.ITipoDocumentoIdentidadDao;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TipoDocumentoIdentidadService {

    @Autowired
    private ITipoDocumentoIdentidadDao tipoDocumentoIdentidadDao;

    public List<TipoDocumentoIdentidad> getAll() {
        /*
		ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-config.xml");
		ITipoDocumentoIdentidadDao tipoDocumentoIdentidadDao = (ITipoDocumentoIdentidadDao) context.getBean("tipoDocumentoIdentidadDao");
		List<TipoDocumentoIdentidad> tiposDocumentosIdentidad = tipoDocumentoIdentidadDao.getAll();
		return tiposDocumentosIdentidad;*/
        return tipoDocumentoIdentidadDao.getAll();
    }
}
