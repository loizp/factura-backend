package com.sunqubit.faqture.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.beans.core.RolUsuario;
import com.sunqubit.faqture.beans.core.Sucursal;
import com.sunqubit.faqture.beans.core.Usuario;
import com.sunqubit.faqture.beans.rest.ApiRestFullResponse;
import com.sunqubit.faqture.beans.rest.RestFullResponseHeader;
import com.sunqubit.faqture.dao.contracts.IUsuarioDao;
import com.sunqubit.faqture.dao.validators.ValidatorException;
import com.sunqubit.faqture.service.validators.UsuarioValidator;

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
        	if(!identifiedUser.getLoginName().equals(usuario.getLoginName())) {
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
    
    public Boolean allowService(String servicio, long emprId, long sucuId) {
    	Usuario user = getUserActual();
    	
    	if(findRole(user,"ROOT"))
    		return true;
    	
    	Boolean allow = false;
    	switch (servicio) {
		case "bfis":
		case "bfif":
		case "ndci":
		case "dcpi": allow = allowInsertbf(user, emprId, sucuId); break;
		default:break;
		}
    	return allow;
    }
    
    private Boolean allowInsertbf(Usuario user, long emprId, long sucuId) {
    	if(findRole(user,"APICLIENT") || findRole(user,"EMISOR")) {
    		if(user.getSoloSucursales() && user.getSucursales() == null)
    			return false;
    		
    		if(user.getEmpresa() != null) {
    			if(user.getEmpresa().getId() == emprId)
    				return true;
    		}
    		
    		if(user.getSucursales() != null) {
    			for (Sucursal sucu : user.getSucursales()) {
					if(sucu.getId() == sucuId)
						return true;
				}
    		}
    	}
    	return false;
    }
    
    private Boolean findRole(Usuario user, String role) {
    	for (RolUsuario rol : user.getRoles()) {
    		if(rol.getRoleName().equals(role))
    			return true;
    	}
    	return false;
    }
    
    public Usuario getUserActual() {
    	Usuario user = null;
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails)
			userDetails = (UserDetails) principal;
		
		try {
			user = usuarioDao.login(userDetails.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
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
