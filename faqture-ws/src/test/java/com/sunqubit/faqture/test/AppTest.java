package com.sunqubit.faqture.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sunqubit.faqture.core.daos.contracts.ITipoDocumentoIdentidadDao;

public class AppTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-config.xml");
		ITipoDocumentoIdentidadDao tipoDocumentoIdentidadDao = (ITipoDocumentoIdentidadDao) context.getBean("tipoDocumentoIdentidadDao");
		System.out.println(tipoDocumentoIdentidadDao.getAll().toString());
		((ClassPathXmlApplicationContext) context).close();
	}
}
