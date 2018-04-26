package com.sunqubit.faqture.core.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sunqubit.faqture.core.daos.contracts.IUsuarioDao;

@Component
public class UsuarioDaoValidator {
	
	@Autowired
    private IUsuarioDao usuarioDao;
	
	public void validaUserId(long userId) throws ValidatorException{
		if (Long.valueOf(userId) == null)
            throw new ValidatorException("Es necesario contener el atributo 'id' del usuario");
	}
	
	public void validaUserLoginName(String userLoginName) throws ValidatorException{
		if (userLoginName == null)
            throw new ValidatorException("Es necesario contener el atributo 'loginName' del usuario");

		if (userLoginName.length() > 20)
            throw new ValidatorException("la longitud del atributo 'loginName' no debe exceder los 20 caracteres");

		try {
    		if (usuarioDao.loginNameExist(userLoginName))
                throw new ValidatorException("El nombre de inicio de sesión ya existe");
    	} catch (Exception ex) {
    		throw new ValidatorException(ex.getMessage());
    	}
	}
	
	public void validaUserPassword(String userPassword) throws ValidatorException{
		if (userPassword == null)
            throw new ValidatorException("Es necesario contener el atributo 'password' del usuario");
		
		if (userPassword.length() > 200)
            throw new ValidatorException("la longitud del atributo 'password' no debe exceder los 250 caracteres");
	}
	
	public void validaUserNombre(String userNombre) throws ValidatorException{
		if (userNombre == null)
            throw new ValidatorException("Es necesario contener el atributo 'nombre' del usuario");

		if (userNombre.length() > 200)
            throw new ValidatorException("la longitud del atributo 'nombre' no debe exceder los 200 caracteres");
	}
	
	public void validaUserEmail(String userEmail) throws ValidatorException{
		if (userEmail == null)
            throw new ValidatorException("Es necesario contener el atributo 'email' del usuario");

		if (userEmail.length() > 200)
            throw new ValidatorException("la longitud del atributo 'email' no debe exceder los 200 caracteres");
	}
}
