package com.sunqubit.faqture.ws.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.Sucursal;
import com.sunqubit.faqture.core.daos.contracts.ISucursalDao;
import com.sunqubit.faqture.core.validators.ValidatorException;
import com.sunqubit.faqture.ws.RestEntitys.ApiRestFullResponse;
import com.sunqubit.faqture.ws.RestEntitys.RestFullResponseHeader;
import com.sunqubit.faqture.ws.validators.SucursalValidator;

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
        
        try {
        	sucursalValidator.validaSucuEmpresa(sucursal.getEmpresa());
        	sucursalValidator.validaSucuDireccion(sucursal.getDireccion());
        	sucursalValidator.validaSucuUnigeo(sucursal.getUbigeo());
        	sucursalDao.insert(sucursal);
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
