package com.sunqubit.faqture.service.validators;

import org.springframework.stereotype.Component;

import com.sunqubit.faqture.dao.validators.UsuarioDaoValidator;
import com.sunqubit.faqture.dao.validators.ValidatorException;

@Component
public class UsuarioValidator extends UsuarioDaoValidator {
	
	@Override
	public void validaUserLoginName(String userLoginName) throws ValidatorException{
		super.validaUserLoginName(userLoginName);
		
		if (userLoginName.length() < 5)
            throw new ValidatorException("Es necesario que el atributo 'loginName' del usuario tenga al menos 5 caracteres");
		
		if (!userLoginName.matches("[A-Za-z0-9-_]*$"))
			throw new ValidatorException("Es necesario que el atributo 'loginName' solo contenga caracteres alfabéticos o númericos");
	}
	
	@Override
	public void validaUserPassword(String userPassword) throws ValidatorException{
		super.validaUserPassword(userPassword);
		
		if (userPassword.length() < 8 || userPassword.length() > 25)
            throw new ValidatorException("Es necesario contener el atributo 'password' del usuario al menos 8 caracteres y no exeda los 25 caracteres");
		
		if(!userPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$_!%#-.*?&])[A-Za-z\\d$@_!%#-.*?&]{8,25}"))
			 throw new ValidatorException("Es necesario contener el atributo 'password' debe estar contener al menos una letra minusculas, letra mayusculas, numero, simbolo y sin espacios");
	}
	
	@Override
	public void validaUserNombre(String userNombre) throws ValidatorException {
		super.validaUserNombre(userNombre);
		
		if (!userNombre.matches("^[A-Za-z][\\w- \\.]*$"))
			throw new ValidatorException("Es necesario que el atributo 'loginName' solo contenga caracteres alfabéticos o númericos");
	}
	
	@Override
	public void validaUserEmail(String userEmail) throws ValidatorException{
		super.validaUserEmail(userEmail);
		
		if (!userEmail.matches("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
            throw new ValidatorException("Es necesario que el atributo 'email' debe estar correctamente expresado");
	}
}
