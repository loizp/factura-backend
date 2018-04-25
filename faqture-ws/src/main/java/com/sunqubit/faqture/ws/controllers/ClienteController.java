package com.sunqubit.faqture.ws.controllers;

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

import com.sunqubit.faqture.core.beans.Cliente;
import com.sunqubit.faqture.ws.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping(value = "/add", headers="Accept=application/json", produces={"application/json"})
	public @ResponseBody ResponseEntity<?> insert(@RequestBody Cliente cliente){
		return ResponseEntity.ok(clienteService.insert(cliente));
	}
	
	@PutMapping(value = "/set", headers="Accept=application/json", produces={"application/json"})
	public @ResponseBody ResponseEntity<?> update(@RequestBody Cliente cliente){
		return ResponseEntity.ok(clienteService.insert(cliente));
	}
	
	@GetMapping(value = "/get/list/{filtro}", headers="Accept=application/json", produces={"application/json"})
	public @ResponseBody ResponseEntity<?> getFilter(@PathVariable("numDoc") String numDoc, @PathVariable("filtro") String filtro) {
		return ResponseEntity.ok(clienteService.getFilter(filtro));
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
