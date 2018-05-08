package com.sunqubit.faqture.service.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.beans.core.Sucursal;
import com.sunqubit.faqture.beans.rest.ApiRestFullResponse;
import com.sunqubit.faqture.beans.rest.RestFullResponseHeader;
import com.sunqubit.faqture.dao.contracts.ISucursalDao;
import com.sunqubit.faqture.dao.validators.ValidatorException;
import com.sunqubit.faqture.service.validators.SucursalValidator;

@Service
public class SucursalService {
	
	@Autowired
	private ISucursalDao sucursalDao;
	
	@Autowired
    private SucursalValidator sucursalValidator;
	
	public ApiRestFullResponse insert(Sucursal sucursal) {
		Boolean ok = true;
        int code = 201;
        String msg = "La sucursal fue registrado correctamente";
        Long res = null;
        try {
        	sucursalValidator.validaSucuContribuyente(sucursal.getContribuyente());
        	sucursalValidator.validaSucuDireccion(sucursal.getDireccion());
        	sucursalValidator.validaSucuUnigeo(sucursal.getUbigeo());
        	if(sucursal.getUrbanizacion() != null)
        		sucursalValidator.validaSucuUrbanizacion(sucursal.getUrbanizacion());
        	res = sucursalDao.insert(sucursal);
        } catch (ValidatorException ve) {
            ok = false;
            code = 400;
            msg = "No se puede registrar la empresa debido a: " + ve.getMessage();
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede registrar la empresa debido a: " + ex.getMessage();
        }
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
	}
	
	public ApiRestFullResponse update(Sucursal sucursal) {
		Boolean ok = true;
        int code = 201;
        String msg = "La sucursal fue registrado correctamente";
        
        try {
        	sucursalValidator.validaSucuId(sucursal.getId());
        	sucursalValidator.validaSucuDireccion(sucursal.getDireccion());
        	sucursalValidator.validaSucuUnigeo(sucursal.getUbigeo());
        	sucursalDao.update(sucursal);
        } catch (ValidatorException ve) {
            ok = false;
            code = 400;
            msg = "No se puede registrar la empresa debido a: " + ve.getMessage();
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede registrar la empresa debido a: " + ex.getMessage();
        }
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), null);
	}
}
