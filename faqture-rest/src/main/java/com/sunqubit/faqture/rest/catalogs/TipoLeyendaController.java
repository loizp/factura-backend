package com.sunqubit.faqture.rest.catalogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunqubit.faqture.service.catalogs.TipoLeyendaService;

@RestController
@RequestMapping("/tiposleyendas")
public class TipoLeyendaController {
	
	@Autowired
	private TipoLeyendaService tipoLeyendaService;
	
	@GetMapping(value = "/get", headers="Accept=application/json", produces={"application/json"})
	public @ResponseBody ResponseEntity<?> getAll() {
		return ResponseEntity.ok(tipoLeyendaService.getAll());
	}
}
