package com.sunqubit.faqture.ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunqubit.faqture.ws.services.UbigeoService;

@RestController
@RequestMapping("/ubigeo")
public class UbigeoController {

    @Autowired
    private UbigeoService ubigeoService;
    
    @GetMapping(value = "/get", headers="Accept=application/json", produces={"application/json"})
    public @ResponseBody
    ResponseEntity<?> getList(@PathVariable("filtro") String filtro) {
        return ResponseEntity.ok(ubigeoService.filter(""));
    }

    @GetMapping(value = "/get/{filtro}", headers="Accept=application/json", produces={"application/json"})
    public @ResponseBody
    ResponseEntity<?> getFilter(@PathVariable("filtro") String filtro) {
        return ResponseEntity.ok(ubigeoService.filter(filtro));
    }
}
