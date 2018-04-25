package com.sunqubit.faqture.ws.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.Empresa;
import com.sunqubit.faqture.core.daos.contracts.IEmpresaDao;
import com.sunqubit.faqture.core.validators.ValidatorException;
import com.sunqubit.faqture.ws.RestEntitys.ApiRestFullResponse;
import com.sunqubit.faqture.ws.RestEntitys.RestFullResponseHeader;
import com.sunqubit.faqture.ws.validators.EmpresaValidator;

@Service
public class EmpresaService {

    @Autowired
    private IEmpresaDao empresaDao;
    
    @Autowired
    private EmpresaValidator empresaValidator;

    public ApiRestFullResponse insert(Empresa empresa) {
    	Boolean ok = true;
        int code = 201;
        String msg = "La empresa fue registrado correctamente";
        
        try {
        	empresaValidator.validaContDoc(empresa.getNumeroDocumento(), empresa.getTipoDocumentoIdentidad());
        	empresaValidator.validaContNombreLegal(empresa.getNombreLegal());
        	empresaValidator.validaContNombreComercial(empresa.getNombreComercial());
        	empresaValidator.validaContDireccion(empresa.getDireccion());
        	empresaValidator.validaContUnigeo(empresa.getUbigeo());
        	empresaDao.insert(empresa);
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
    
    public ApiRestFullResponse update(Empresa empresa) {
    	Boolean ok = true;
        int code = 201;
        String msg = "La empresa fue modificada correctamente";
        
        try {
        	empresaValidator.validaContId(empresa.getId());
        	Empresa identifiedEmpr = empresaDao.get(empresa.getId());
        	if(identifiedEmpr.getNumeroDocumento() != empresa.getNumeroDocumento() || identifiedEmpr.getTipoDocumentoIdentidad().getCodigo() != empresa.getTipoDocumentoIdentidad().getCodigo()) {
        		empresaValidator.validaContDoc(empresa.getNumeroDocumento(), empresa.getTipoDocumentoIdentidad());
        		empresaDao.changeRuc(empresa);
        	}
        	empresaValidator.validaContNombreLegal(empresa.getNombreLegal());
        	empresaValidator.validaContNombreComercial(empresa.getNombreComercial());
        	empresaValidator.validaContDireccion(empresa.getDireccion());
        	empresaValidator.validaContUnigeo(empresa.getUbigeo());
        	empresaDao.update(empresa);
        } catch (ValidatorException ve) {
            ok = false;
            code = 400;
            msg = "No se puede modificar la empresa debido a: " + ve.getMessage();
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede modificar la empresa debido a: " + ex.getMessage();
        }
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), null);
    }
    
    public ApiRestFullResponse get(long id) {
    	Boolean ok = true;
        int code = 200;
        String msg = "Entrega la empresa solicitada";
        Empresa res = null;
        try {
        	res = empresaDao.get(id);
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede obtener la empresa debido a: " + ex.getMessage();
        }
    	return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
    
    public ApiRestFullResponse get(String ruc) {
    	Boolean ok = true;
        int code = 200;
        String msg = "Entrega de la empresa solicitada";
        Empresa res = null;
        try {
        	res = empresaDao.get(ruc, "6");
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede obtener la empresa debido a: " + ex.getMessage();
        }
    	return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
    
    public ApiRestFullResponse filter(String nombre) {
    	Boolean ok = true;
        int code = 200;
        String msg = "Entrega de listado de empresas segun el filtro: " + nombre;
        List<Empresa> res = null;
        try {
        	res = empresaDao.filterName(nombre);
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede obtener la empresa debido a: " + ex.getMessage();
        }
    	return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
    
    public ApiRestFullResponse getSucursales(long id) {
    	Boolean ok = true;
        int code = 200;
        String msg = "Entrega de la empresa con sus respectivas sucursales solicitada";
        Empresa res = null;
        try {
        	res = empresaDao.getSucursales(id);
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede obtener la empresa con sus respectivas sucursales debido a: " + ex.getMessage();
        }
    	return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
}
