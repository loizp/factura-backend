package com.sunqubit.faqture.ws.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.Usuario;
import com.sunqubit.faqture.core.daos.contracts.IUsuarioDao;
import com.sunqubit.faqture.core.validators.ValidatorException;
import com.sunqubit.faqture.ws.RestEntitys.ApiRestFullResponse;
import com.sunqubit.faqture.ws.RestEntitys.RestFullResponseHeader;
import com.sunqubit.faqture.ws.validators.UsuarioValidator;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Autowired
    private UsuarioValidator usuarioValidator;

    public ApiRestFullResponse insert(Usuario usuario) {
        Boolean ok = true;
        int code = 201;
        String msg = "El usuario fue registrado correctamente";

        try {
        	usuarioValidator.validaUserLoginName(usuario.getLoginName());
        	usuarioValidator.validaUserPassword(usuario.getPassword());
        	usuarioValidator.validaUserEmail(usuario.getEmail());
            usuarioDao.insert(prepareUser(usuario, true));
        } catch (ValidatorException ve) {
            ok = false;
            code = 400;
            msg = "No se puede registrar el usuario debido a: " + ve.getMessage();
        } catch (Exception ex) {
            ok = false;
            code = 500;
            msg = "No se puede registrar el usuario debido a: " + ex.getMessage();
        }
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), null);
    }
    
    public ApiRestFullResponse updateAll(Usuario usuario) {
    	int code = 201;
        Boolean ok = true;
        String msg = "El usuario fue modificado correctamente";
        
        try {
        	usuarioValidator.validaUserId(usuario.getId());
        	Usuario identifiedUser = usuarioDao.getById(usuario.getId());
        	
        	if(identifiedUser.getLoginName() != usuario.getLoginName()) {
        		usuarioValidator.validaUserLoginName(usuario.getLoginName());
        		usuarioDao.changeLoginName(usuario);
        	}
        		
        	if(usuario.getPassword() != null) {
        		usuarioValidator.validaUserPassword(usuario.getPassword());
        		usuarioDao.changePassword(prepareUser(usuario, true));
        	}

        	usuarioValidator.validaUserEmail(usuario.getEmail());
        	usuarioDao.changeEmail(usuario);
        	usuario = prepareUser(usuario, false);
        	usuarioValidator.validaUserNombre(usuario.getNombre());
            usuarioDao.update(usuario);
        } catch (ValidatorException ve) {
        	ok = false;
            code = 400;
            msg = "No se puede modificar el usuario debido a: " + ve.getMessage();
        } catch (Exception ex) {
        	ok = false;
            code = 500;
            msg = "No se puede modificar el usuario debido a: " + ex.getMessage();
        } 
        return new ApiRestFullResponse(new RestFullResponseHeader(ok, code, msg), null);
    }
    
    private Usuario prepareUser(Usuario usuario, boolean encode) {
        if (usuario.getNombre() == null)
            usuario.setNombre(usuario.getLoginName());
        
        if (encode) {
        	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }

        return usuario;
    }
}
