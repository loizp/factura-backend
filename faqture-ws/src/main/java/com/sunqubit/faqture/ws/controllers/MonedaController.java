package com.sunqubit.faqture.ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunqubit.faqture.ws.services.MonedaService;

@RestController
@RequestMapping("/monedas")
public class MonedaController {
	
	@Autowired
	 private MonedaService monedaService;
	 
	 @GetMapping(value = "/get", headers="Accept=application/json", produces={"application/json"})
	 public @ResponseBody
	 ResponseEntity<?> getAll() {
		 return ResponseEntity.ok(monedaService.getAll());
	 }
}