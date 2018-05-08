package com.sunqubit.faqture.rest.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunqubit.faqture.beans.core.Contribuyente;
import com.sunqubit.faqture.service.core.ContribuyenteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ContribuyenteService clienteService;
	
	@PostMapping(value = "/add", headers="Accept=application/json", produces={"application/json"})
	public @ResponseBody ResponseEntity<?> insert(@RequestBody Contribuyente cliente){
		return ResponseEntity.ok(clienteService.insertC(cliente));
	}
	
	@PutMapping(value = "/set", headers="Accept=application/json", produces={"application/json"})
	public @ResponseBody ResponseEntity<?> update(@RequestBody Contribuyente cliente){
		return ResponseEntity.ok(clienteService.updateC(cliente));
	}
	
	@GetMapping(value = "/get/list/{filtro}", headers="Accept=application/json", produces={"application/json"})
	public @ResponseBody ResponseEntity<?> getFilter(@PathVariable("filtro") String filtro) {
		return ResponseEntity.ok(clienteService.filter(filtro));
	}
	
	@GetMapping(value = "/get/{id}", headers="Accept=application/json", produces={"application/json"})
	public @ResponseBody ResponseEntity<?> get(@PathVariable("id") long id) {
		return ResponseEntity.ok(clienteService.get(id));
	}
	
	@GetMapping(value = "/get/{tipoDoc}/{numDoc}", headers="Accept=application/json", produces={"application/json"})
	public @ResponseBody ResponseEntity<?> get(@PathVariable("numDoc") String numDoc, @PathVariable("tipoDoc") String tipoDoc) {
		return ResponseEntity.ok(clienteService.get(numDoc, tipoDoc));
	}
}
