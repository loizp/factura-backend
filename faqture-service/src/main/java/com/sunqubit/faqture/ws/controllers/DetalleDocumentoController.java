package com.sunqubit.faqture.ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunqubit.faqture.core.beans.DetalleDocumento;
import com.sunqubit.faqture.ws.services.DetalleDocumentoService;

@RestController
@RequestMapping("/detalledoc")
public class DetalleDocumentoController {
	
	@Autowired
	private DetalleDocumentoService detalleDocumentoService;
	
	@PostMapping(value = "/add", headers="Accept=application/json", produces={"application/json"})
	public @ResponseBody ResponseEntity<?> insert(@RequestBody DetalleDocumento detalleDocumento){
		return ResponseEntity.ok(detalleDocumentoService.insert(detalleDocumento));
	}
}
