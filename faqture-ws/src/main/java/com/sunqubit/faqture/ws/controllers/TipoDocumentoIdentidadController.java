package com.sunqubit.faqture.ws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunqubit.faqture.core.beans.TipoDocumentoIdentidad;
import com.sunqubit.faqture.ws.services.TipoDocumentoIdentidadService;

@Controller
public class TipoDocumentoIdentidadController {

    @Autowired
    private TipoDocumentoIdentidadService tipoDocumentoIdentidadService;

    @RequestMapping(value = "/tiposdocidentidades",method = RequestMethod.GET)
    public @ResponseBody
   List<TipoDocumentoIdentidad> getAll() {
    	return  tipoDocumentoIdentidadService.getAll();  
        
    }
   
}
