package com.sunqubit.faqture.ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunqubit.faqture.core.beans.Sucursal;
import com.sunqubit.faqture.ws.services.SucursalService;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {
	
	@Autowired
    private SucursalService sucursalService;
	
	@PostMapping(value = "/add", headers="Accept=application/json", produces={"application/json"})
    public @ResponseBody ResponseEntity<?> insert(@RequestBody Sucursal sucursal){
		return ResponseEntity.ok(sucursalService.insert(sucursal));
	}
	
	@PutMapping(value = "/set", headers="Accept=application/json", produces={"application/json"})
    public @ResponseBody ResponseEntity<?> update(@RequestBody Sucursal sucursal){
		return ResponseEntity.ok(sucursalService.update(sucursal));
	}
}
