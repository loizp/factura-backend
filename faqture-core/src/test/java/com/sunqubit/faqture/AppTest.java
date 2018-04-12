/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sunqubit.faqture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sunqubit.faqture.core.beans.TipoDocumentoIdentidad;
import com.sunqubit.faqture.core.beans.Ubigeo;
import com.sunqubit.faqture.core.daos.contracts.IEmpresaDao;
import com.sunqubit.faqture.core.daos.contracts.ITipoDocumentoIdentidadDao;
import com.sunqubit.faqture.core.daos.contracts.IUbigeoDao;

public class AppTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);

    public static void main(String[] args) {
    	
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-config.xml");
        LOGGER.info("inicio preliminar");

        
        //Cliente cliente = new Cliente(1,"46798659","Juan Perez",new TipoDocumentoIdentidad("1","DNI"));
        /*
        Empresa empresa = new Empresa();
        empresa.setEmprRuc("12345678901");
        empresa.setEmprRazonSocial("Comercial S.A.");
        empresa.setEmprNombreComercial("Comercio Ropa");
        empresa.setEmprDireccion("Jr. 5nocer");
        empresa.setTipoDocumentoIdentidad(tipoDocumentoIdentidad);
        empresa.setUbigeo(ubigeo);*/
        IEmpresaDao empresaDao = (IEmpresaDao) context.getBean("empresaDao");
        //empresaDao.insert(empresa);
        //LOGGER.info("insertando empresa");
        //IClienteDao clienteDao = (IClienteDao) context.getBean("clienteDao");
        //clienteDao.insert(cliente);
        //System.out.println(clienteDao.getList().size());
        LOGGER.info("obteniendo empresa");
        System.out.println(empresaDao.filterName("Ropa"));
        //IUbigeoDao ubigeoDao = (IUbigeoDao) context.getBean("ubigeoDao");
        //System.out.println(ubigeoDao.filter("22000"));
        LOGGER.info("final");
        
        ((ClassPathXmlApplicationContext) context).close();
    }
}
