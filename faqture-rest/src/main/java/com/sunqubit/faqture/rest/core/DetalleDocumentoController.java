package com.sunqubit.faqture.rest.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunqubit.faqture.beans.core.DetalleDocumento;
import com.sunqubit.faqture.service.core.DetalleDocumentoService;

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
