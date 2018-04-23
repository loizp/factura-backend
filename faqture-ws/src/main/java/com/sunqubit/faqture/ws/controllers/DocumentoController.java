package com.sunqubit.faqture.ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunqubit.faqture.ws.services.DocumentoService;

@RestController
@RequestMapping("/documento")
public class DocumentoController {
	
	@Autowired
	private DocumentoService documentoService;
}
