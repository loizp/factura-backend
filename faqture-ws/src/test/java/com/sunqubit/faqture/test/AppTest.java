package com.sunqubit.faqture.test;

import com.sunqubit.faqture.core.daos.TipoDocumentoIdentidadDao;
import com.sunqubit.faqture.core.daos.contracts.ITipoDocumentoIdentidadDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-config.xml");
        ITipoDocumentoIdentidadDao tipoDocumentoIdentidadDao = (TipoDocumentoIdentidadDao) context.getBean("tipoDocumentoIdentidadDao");
        System.out.println(tipoDocumentoIdentidadDao.getAll().toString());
        ((ClassPathXmlApplicationContext) context).close();
    }
}
