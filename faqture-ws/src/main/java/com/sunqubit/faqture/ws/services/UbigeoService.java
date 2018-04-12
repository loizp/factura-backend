package com.sunqubit.faqture.ws.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.Ubigeo;
import com.sunqubit.faqture.core.daos.contracts.IUbigeoDao;

@Service
public class UbigeoService {
	
	@Autowired
	private IUbigeoDao ubigeoDao;
	
	public List<Ubigeo> filter(String filtro){
		/*ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-config.xml");
		IUbigeoDao ubigeoDao = (IUbigeoDao) context.getBean("ubigeoDao");
		List<Ubigeo> ubigeos = ubigeoDao.filter(filtro);
		return ubigeos;*/
		return ubigeoDao.filter(filtro);
	}
}
