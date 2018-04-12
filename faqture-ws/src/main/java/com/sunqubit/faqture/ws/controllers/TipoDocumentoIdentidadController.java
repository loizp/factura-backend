package com.sunqubit.faqture.ws.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunqubit.faqture.core.beans.TipoDocumentoIdentidad;
import com.sunqubit.faqture.ws.services.TipoDocumentoIdentidadService;

@Controller
public class TipoDocumentoIdentidadController {
	@Resource(name = "tipoDocumentoIdentidadService")
	private TipoDocumentoIdentidadService tipoDocumentoIdentidadService;
	
	@RequestMapping(value = "/tiposdocidentidades", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
	public @ResponseBody List<TipoDocumentoIdentidad> getAll(){
		return tipoDocumentoIdentidadService.getAll();
	}
}
