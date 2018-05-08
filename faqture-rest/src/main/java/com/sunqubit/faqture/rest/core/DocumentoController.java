package com.sunqubit.faqture.rest.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunqubit.faqture.beans.core.ComprobantePago;
import com.sunqubit.faqture.beans.core.NotaDC;
import com.sunqubit.faqture.service.core.DocumentoService;

@RestController
@RequestMapping("/docs")
public class DocumentoController {
	
	@Autowired
	private DocumentoService documentoService;
	
	@PostMapping(value = "/add/bf/simple", headers="Accept=application/json", produces={"application/json"})
	public @ResponseBody ResponseEntity<?> insertSinple(@RequestBody ComprobantePago compPago){
		return ResponseEntity.ok(documentoService.insertSimple(compPago));
	}
	
	@PostMapping(value = "/add/ncd", headers="Accept=application/json", produces={"application/json"})
	public @ResponseBody ResponseEntity<?> insert(@RequestBody NotaDC notaDC){
		return ResponseEntity.ok(documentoService.insert(notaDC));
	}
	
	@PostMapping(value = "/add/bf/full", headers="Accept=application/json", produces={"application/json"})
	public @ResponseBody ResponseEntity<?> insertFull(@RequestBody ComprobantePago compPago){
		return ResponseEntity.ok(documentoService.insertFull(compPago));
	}
	
	@GetMapping(value = "/get/bf/{id}", headers="Accept=application/json", produces={"application/json"})
	public @ResponseBody ResponseEntity<?> getC(@PathVariable("id") long id) {
		return ResponseEntity.ok(documentoService.getC(id));
	}
	
	@GetMapping(value = "/get/ncd/{id}", headers="Accept=application/json", produces={"application/json"})
	public @ResponseBody ResponseEntity<?> getN(@PathVariable("id") long id) {
		return ResponseEntity.ok(documentoService.getN(id));
	}
}
