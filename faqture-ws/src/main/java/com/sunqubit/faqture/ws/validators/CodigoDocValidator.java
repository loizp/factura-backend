package com.sunqubit.faqture.ws.validators;

import org.springframework.stereotype.Component;

@Component
public class CodigoDocValidator {
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
	
	public Boolean numComprobantePagoValido(String numDoc, String tipoDoc) {
		boolean valido = true;
		
		if(!numDoc.matches("^[FB][A-Z0-9]{3}-[0-9]{1,8}$"))
			return false;
		
		switch (tipoDoc) {
			case "01":
				if(!numDoc.matches("^[F][A-Z0-9]{3}-[0-9]{1,8}$"))
					valido = false;
				break;
			case "03":
				if(!numDoc.matches("^[B][A-Z0-9]{3}-[0-9]{1,8}$"))
					valido = false;
				break;
			default: valido = false; break;
		}
		return valido;
	}
	
	public Boolean numNotaValido(String numDoc, String tipoDoc) {
		if((tipoDoc != "07" && tipoDoc != "08") || !numDoc.matches("^[FB][A-Z0-9]{3}-[0-9]{1,8}$"))
			return false;
		return true;
	}
}
