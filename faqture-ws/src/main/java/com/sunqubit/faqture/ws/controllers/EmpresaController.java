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

import com.sunqubit.faqture.core.beans.Empresa;
import com.sunqubit.faqture.ws.services.EmpresaService;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping(value = "/add", headers="Accept=application/json", produces={"application/json"})
    public @ResponseBody ResponseEntity<?> insert(@RequestBody Empresa empresa) {
    	return ResponseEntity.ok(empresaService.insert(empresa));
    }
    
    @PutMapping(value = "/set", headers="Accept=application/json", produces={"application/json"})
    public @ResponseBody ResponseEntity<?> update(@RequestBody Empresa empresa) {
    	return ResponseEntity.ok(empresaService.update(empresa));
    }
    
    @GetMapping(value = "/get/{id}", headers="Accept=application/json", produces={"application/json"})
    public @ResponseBody ResponseEntity<?> get(@PathVariable("id") long id) {
    	return ResponseEntity.ok(empresaService.get(id));
    }
    
    @GetMapping(value = "/get/ruc/{ruc}", headers="Accept=application/json", produces={"application/json"})
    public @ResponseBody ResponseEntity<?> get(@PathVariable("ruc") String ruc) {
    	return ResponseEntity.ok(empresaService.get(ruc));
    }
    
    @GetMapping(value = "/get/list/{nombre}", headers="Accept=application/json", produces={"application/json"})
    public @ResponseBody ResponseEntity<?> getByName(@PathVariable("nombre") String nombre) {
    	return ResponseEntity.ok(empresaService.filter(nombre));
    }
    
    @GetMapping(value = "/get/sucursales/{id}", headers="Accept=application/json", produces={"application/json"})
    public @ResponseBody ResponseEntity<?> getByName(@PathVariable("id") long id) {
    	return ResponseEntity.ok(empresaService.getSucursales(id));
    }
}
