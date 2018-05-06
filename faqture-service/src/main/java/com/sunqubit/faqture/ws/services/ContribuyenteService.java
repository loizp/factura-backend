package com.sunqubit.faqture.ws.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.Contribuyente;
import com.sunqubit.faqture.core.daos.contracts.IContribuyenteDao;
import com.sunqubit.faqture.core.validators.ValidatorException;
import com.sunqubit.faqture.ws.RestEntitys.ApiRestFullResponse;
import com.sunqubit.faqture.ws.RestEntitys.RestFullResponseHeader;
import com.sunqubit.faqture.ws.validators.ClienteValidator;
import com.sunqubit.faqture.ws.validators.ContribuyenteValidator;
import com.sunqubit.faqture.ws.validators.EmpresaValidator;

@Service
public class ContribuyenteService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContribuyenteService.class);

    @Autowired
    private IContribuyenteDao contribuyenteDao;
    
    @Autowired
    private EmpresaValidator empresaValidator;
    
    @Autowired
    private ContribuyenteValidator contribuyenteValidator;
    
    @Autowired
    private ClienteValidator clienteValidator;
    
    public ApiRestFullResponse insertE(Contribuyente empresa) {
    	Boolean ok = true;
        int code = 201;
        String msg = "La empresa fue registrado correctamente";
        
        try {
        	empresaValidator.validaContDoc(empresa.getNumeroDocumento(), empresa.getTipoDocumentoIdentidad());
        	contribuyenteValidator.validaContNombreLegal(empresa.getNombreLegal());
        	contribuyenteValidator.validaContNombreComercial(empresa.getNombreComercial());
        	contribuyenteValidator.validaContDireccion(empresa.getDireccion());
        	if(empresa.getUrbanizacion() != null)
        		contribuyenteValidator.validaContUrbanizacion(empresa.getUrbanizacion());
        	contribuyenteValidator.validaContUnigeo(empresa.getUbigeo());
        	contribuyenteDao.insert(empresa,"E");
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
    
    public ApiRestFullResponse insertC(Contribuyente cliente) {
		Boolean ok = true;
		int code = 201;
		String msg = "El cliente fue registrado correctamente";
		
		try {
			contribuyenteValidator.validaContNombreLegal(cliente.getNombreLegal());
			clienteValidator.validaContDoc(cliente.getNumeroDocumento(), cliente.getTipoDocumentoIdentidad());
			if(cliente.getDireccion() != null || cliente.getTipoDocumentoIdentidad().getCodigo().equals("6"))
				contribuyenteValidator.validaContDireccion(cliente.getDireccion());
			if(cliente.getNombreComercial() != null)
				contribuyenteValidator.validaContNombreComercial(cliente.getNombreComercial());
			if(cliente.getUrbanizacion() != null)
        		contribuyenteValidator.validaContUrbanizacion(cliente.getUrbanizacion());
			if(cliente.getUbigeo() != null)
				contribuyenteValidator.validaContUnigeo(cliente.getUbigeo());
			contribuyenteDao.insert(cliente,"C");
		} catch (ValidatorException ve) {
			ok = false;
			code = 400;
			msg = "No se puede registrar el cliente debido a: " + ve.getMessage();
		} catch (Exception ex) {
			ok = false;
			code = 500;
			msg = "No se puede registrar el cliente debido a: " + ex.getMessage();
		}
		return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), null);
	}
    
    public ApiRestFullResponse updateE(Contribuyente empresa) {
    	Boolean ok = true;
        int code = 201;
        String msg = "La empresa fue modificada correctamente";
        
        try {
        	contribuyenteValidator.validaContId(empresa.getId());
        	Contribuyente identifiedEmpr = contribuyenteDao.getVerif(empresa.getId());
        	if(empresa.getNumeroDocumento() != null) {
	        	if(!identifiedEmpr.getNumeroDocumento().equals(empresa.getNumeroDocumento()) || !identifiedEmpr.getTipoDocumentoIdentidad().getCodigo().equals(empresa.getTipoDocumentoIdentidad().getCodigo())) {
	        		empresaValidator.validaContDoc(empresa.getNumeroDocumento(), empresa.getTipoDocumentoIdentidad());
	        		contribuyenteDao.changeDoc(empresa);
	        	}
        	}
        	contribuyenteValidator.validaContNombreLegal(empresa.getNombreLegal());
        	contribuyenteValidator.validaContNombreComercial(empresa.getNombreComercial());
        	contribuyenteValidator.validaContDireccion(empresa.getDireccion());
        	contribuyenteValidator.validaContUnigeo(empresa.getUbigeo());
        	contribuyenteDao.update(empresa,"E");
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
    
    public ApiRestFullResponse updateC(Contribuyente cliente) {
		Boolean ok = true;
		int code = 201;
		String msg = "El cliente fue modificado correctamente";
		
		try {
			contribuyenteValidator.validaContId(cliente.getId());
			if(cliente.getNombreLegal() != null)
				contribuyenteValidator.validaContNombreLegal(cliente.getNombreLegal());
			Contribuyente identifiedEmpr = contribuyenteDao.getVerif(cliente.getId());
			if(cliente.getNumeroDocumento() != null) {
				if(!identifiedEmpr.getNumeroDocumento().equals(cliente.getNumeroDocumento()) || !identifiedEmpr.getTipoDocumentoIdentidad().getCodigo().equals(cliente.getTipoDocumentoIdentidad().getCodigo())) {
	        		clienteValidator.validaContDoc(cliente.getNumeroDocumento(), cliente.getTipoDocumentoIdentidad());
	        		contribuyenteDao.changeDoc(cliente);
	        	}
			}
        	LOGGER.info(cliente.getDireccion());
			if(cliente.getDireccion() != null || cliente.getTipoDocumentoIdentidad().getCodigo() == "6")
				contribuyenteValidator.validaContDireccion(cliente.getDireccion());
			if(cliente.getNombreComercial() != null)
				contribuyenteValidator.validaContNombreComercial(cliente.getNombreComercial());
			if(cliente.getUrbanizacion() != null)
        		contribuyenteValidator.validaContUrbanizacion(cliente.getUrbanizacion());
			if(cliente.getUbigeo() != null)
				contribuyenteValidator.validaContUnigeo(cliente.getUbigeo());
			contribuyenteDao.update(cliente,"C");
		} catch (ValidatorException ve) {
			ok = false;
			code = 400;
			msg = "No se puede modificar el cliente debido a: " + ve.getMessage();
		} catch (Exception ex) {
			ok = false;
			code = 500;
			msg = "No se puede modificar el cliente debido a: " + ex.getMessage();
		}
		return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), null);
	}
    
    public ApiRestFullResponse get(long id) {
    	Boolean ok = true;
        int code = 200;
        String msg = "Entrega del contribuyente solicitado";
        Contribuyente res = null;
        try {
        	res = contribuyenteDao.get(id);
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede obtener el contribuyente debido a: " + ex.getMessage();
        }
    	return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
    
    public ApiRestFullResponse get(String doc, String tido) {
    	Boolean ok = true;
        int code = 200;
        String msg = "Entrega del contribuyente solicitado";
        Contribuyente res = null;
        try {
        	res = contribuyenteDao.get(doc, tido);
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede obtener el contribuyente debido a: " + ex.getMessage();
        }
    	return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
    
    public ApiRestFullResponse filter(String nombre) {
    	Boolean ok = true;
        int code = 200;
        String msg = "Entrega de listado de empresas segun el filtro: " + nombre;
        List<Contribuyente> res = null;
        try {
        	res = contribuyenteDao.filterName(nombre);
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede obtener el contribuyente debido a: " + ex.getMessage();
        }
    	return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), res);
    }
}
