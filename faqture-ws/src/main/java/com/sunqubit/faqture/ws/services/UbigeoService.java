package com.sunqubit.faqture.ws.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.Ubigeo;
import com.sunqubit.faqture.core.daos.contracts.IUbigeoDao;
import com.sunqubit.faqture.ws.RestEntitys.ApiRestFullResponse;
import com.sunqubit.faqture.ws.RestEntitys.RestFullResponseHeader;

@Service
public class UbigeoService {

    @Autowired
    private IUbigeoDao ubigeoDao;

    public ApiRestFullResponse filter(String filtro) {
        Boolean ok = true;
        int code = 200;
        String msg = "Entrega de listado de ubigeos segun el filtro: " + filtro;
        List<Ubigeo> res = null;
        try {
            res = ubigeoDao.filter(filtro);
        } catch (Exception ex) {
            ok = false;
            code = 400;
            msg = "No se puede obtener el listado de ubigeos debido a: " + ex.getMessage();
        }
        
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
}
