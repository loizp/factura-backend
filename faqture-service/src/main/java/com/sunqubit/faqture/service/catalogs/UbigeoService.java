package com.sunqubit.faqture.service.catalogs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.beans.catalogs.Ubigeo;
import com.sunqubit.faqture.beans.rest.ApiRestFullResponse;
import com.sunqubit.faqture.beans.rest.RestFullResponseHeader;
import com.sunqubit.faqture.dao.contracts.IUbigeoDao;

@Service
public class UbigeoService {

    @Autowired
    private IUbigeoDao ubigeoDao;
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiRestFullResponse filter(String filtro) {
        Boolean ok = true;
        int code = 200;
        String msg = "Entrega de listado de ubigeos segun el filtro: " + filtro;
        List<Ubigeo> res = null;
        try {
            res = ubigeoDao.filter(filtro);
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede obtener el listado de ubigeos debido a: " + ex.getMessage();
        }
        
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
}
