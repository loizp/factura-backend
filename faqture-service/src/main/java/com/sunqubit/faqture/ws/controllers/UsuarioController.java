package com.sunqubit.faqture.ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunqubit.faqture.core.beans.Usuario;
import com.sunqubit.faqture.ws.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/add", headers="Accept=application/json", produces={"application/json"})
    public @ResponseBody
    ResponseEntity<?> insert(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.insert(usuario));
    }
    
    @PutMapping(value = "/set", headers="Accept=application/json", produces={"application/json"})
    public @ResponseBody
    ResponseEntity<?> update(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.updateAll(usuario));
    }
}
