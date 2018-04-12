package com.sunqubit.faqture.ws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunqubit.faqture.core.beans.Ubigeo;
import com.sunqubit.faqture.ws.services.UbigeoService;

@Controller
public class UbigeoController {

   @Autowired
    private UbigeoService ubigeoService;

    @RequestMapping(value = "/ubigeo/{filtro}", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    List<Ubigeo> getFilter(@PathVariable("filtro") String filtro) {
        return ubigeoService.filter(filtro);
    }
}
