package com.sunqubit.faqture.ws.validators;

import org.springframework.stereotype.Component;

@Component
public class DocIdentidadValidator {
	public Boolean docIdentidadValido(String numDocIdentidad, String tipoDocIdentidad) {
		boolean valido = true;
		
		if(!numDocIdentidad.matches("^[A-Z0-9]*$"))
			return false;
		
		switch (tipoDocIdentidad) {
			case "1":
				if(!numDocIdentidad.matches("[0-9]{8}"))
					valido = false;
				break;
			case "4":
				if(numDocIdentidad.length() > 12 || !numDocIdentidad.matches("^[A-Z0-9]*$"))
					valido = false;
				break;
			case "6":
				if(!numDocIdentidad.matches("^[12][0]([0-9]{9})"))
					valido = false;
				break;
			case "7":
				if(numDocIdentidad.length() > 12 || !numDocIdentidad.matches("^[A-Z0-9]*$"))
					valido = false;
				break;
			case "A":
				if(numDocIdentidad.length() > 15 || !numDocIdentidad.matches("^[A-Z0-9]*$"))
					valido = false;
				break;
			default: valido = false; break;
		}
		return valido;
	}
}
