package com.sunqubit.faqture.ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunqubit.faqture.core.beans.ComprobantePago;
import com.sunqubit.faqture.core.beans.NotaDC;
import com.sunqubit.faqture.ws.services.DocumentoService;

@RestController
@RequestMapping("/docs")
public class DocumentoController {
	
	@Autowired
	private DocumentoService documentoService;
	
	@PostMapping(value = "/add/bf", headers="Accept=application/json", produces={"application/json"})
	public @ResponseBody ResponseEntity<?> insert(@RequestBody ComprobantePago compPago){
		return ResponseEntity.ok(documentoService.insert(compPago));
	}
	
	@PostMapping(value = "/add/ncd", headers="Accept=application/json", produces={"application/json"})
	public @ResponseBody ResponseEntity<?> insert(@RequestBody NotaDC notaDC){
		return ResponseEntity.ok(documentoService.insert(notaDC));
	}
}
