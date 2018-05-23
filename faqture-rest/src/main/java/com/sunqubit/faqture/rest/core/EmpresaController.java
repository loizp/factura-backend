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
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private ContribuyenteService empresaService;

    @PostMapping(value = "/add", headers="Accept=application/json", produces={"application/json"})
    public @ResponseBody ResponseEntity<?> insert(@RequestBody Contribuyente empresa) {
    	return ResponseEntity.ok(empresaService.insertE(empresa));
    }
    
    @PutMapping(value = "/set", headers="Accept=application/json", produces={"application/json"})
    public @ResponseBody ResponseEntity<?> update(@RequestBody Contribuyente empresa) {
    	return ResponseEntity.ok(empresaService.updateE(empresa));
    }
    
    @GetMapping(value = "/get/list/{nombre}", headers="Accept=application/json", produces={"application/json"})
    public @ResponseBody ResponseEntity<?> getByName(@PathVariable("nombre") String nombre) {
    	return ResponseEntity.ok(empresaService.filter(nombre));
    }
    
    @GetMapping(value = "/get/one/{id}", headers="Accept=application/json", produces={"application/json"})
    public @ResponseBody ResponseEntity<?> get(@PathVariable("id") long id) {
    	return ResponseEntity.ok(empresaService.get(id));
    }
    
    @GetMapping(value = "/get/one/{tido}/{doc}", headers="Accept=application/json", produces={"application/json"})
    public @ResponseBody ResponseEntity<?> get(@PathVariable("doc") String doc, @PathVariable("tido") String tido) {
    	return ResponseEntity.ok(empresaService.get(doc, tido));
    }
}
