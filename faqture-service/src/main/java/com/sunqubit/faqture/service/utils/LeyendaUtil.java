package com.sunqubit.faqture.service.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

@Component
public class LeyendaUtil {
	private static final int  Unidad = 1; 
	private static final int  Decena = 10;
	private static final int  Centena = 100;
	
	public String numberToLetterES(String Number, String sepDecimal, String sMoney, int numDecimales) {
		return convertNumber(Number, sepDecimal, sMoney, numDecimales);
	}
	
	public String numberToLetterES(BigDecimal Number, String sepDecimal, String sMoney, int numDecimales) {
		return convertNumber(Number.toString(), sepDecimal, sMoney, numDecimales);
	}
	
	private static String convertNumber(String Number, String sepDecimal, String sMoney, int numDecimales){
		Number = Number.trim();
		sepDecimal = sepDecimal.trim();
		sepDecimal = sepDecimal.trim();
		
		String  V[] = initVector(), s = "", z = "", c = "", e = " ", t;
	    int l = Number.length(), k = Number.indexOf(sepDecimal), u = 1, n = 0, j = 0, b = 0, r = 2, d, p;
	   
	    try{
	    	BigDecimal big = new BigDecimal(Number.replaceAll(",", ""));
			big = big.setScale(numDecimales, RoundingMode.HALF_UP);
		    Number = big.toString();
	    	if(k >= 0) { 
	    		c = String.valueOf(Integer.parseInt(String.valueOf(Number.substring(k + 1, k + 1 + numDecimales))));
	    		l = k;
	    	}
	    	if (l <= 24) {
	    		if(l == 0) {
	    			Number = "0" + Number;
	    			l = 1;
	    		}
	    		for(int i = l ; i >= 1; i--){
	    			j++;
	    			d = Integer.parseInt(String.valueOf(Number.charAt(i - 1)));
	    			n = (d * u) + n;
	    			switch(u) {
	    				case Unidad:
	    					if(d == 1 && l == 1) s = "uno";
	    					else s = V[n];
	    					if (i == l && n == 1) b++;
	    					break;
	    				case Decena:
	    					p = d - 2;
	    					if(p < 0) s = V[n];
	    					else {
	    						t = V[20 + p];
	    						if(n % 10 != 0) s = (d == 2)? "veinti" + s : t + " y " + s;
	    						else s = t;
	    					}
	    					break;
	    				case Centena:
	    					p = d - 1;
	    					t = V[30 + p];
	    					if(n % 100  == 0) {
	    						s = ""; 
	    						e = "";
	    					} else if(d == 1) t += "to";
	    					s = t + e + s;
	    					z = (s + z);
	    					break;
	    			}
	    			e = " ";
	    			if(j > 1 && (j - 1) % 3 == 0) {
	    				p = (j % 2 == 0) ? 1 : r;
	    				t = V[40 + p];
	    				if(p > 1) {
	    					r++;
	    					if(((n == 1 && i > 1 ) || n > 1)) t += "es";
	    				}
	    				z = e + t + e + z;
	    			}
	    			if (u == Centena) {
	    				u = 1;
	    				n = 0;
	    				s = "";
	    			} else u *= 10;
	    		}
	    		switch(c.length()) {
		    		case 0: c = " con 00/100"; break;
		    		case 1: c = " con 0" + c + "/100"; break;
		    		case 2: c = " con " + c + "/100"; break;
		    		case 3: c = " con " + c + "/1000"; break;
		    		case 4: c = " con " + c + "/10000"; break;
		    		case 5: c = " con " + c + "/100000"; break;
		    		case 6: c = " con " + c + "/1000000"; break;
		    		case 7: c = " con " + c + "/10000000"; break;
		    		case 8: c = " con " + c + "/100000000"; break;
		    	}
		    	if (!sMoney.trim().equals("")) sMoney = " " + sMoney.trim();
		    	else if(b > 0) z += "o";
		    	z = (s + z) + c + sMoney;
		    	z = z.substring(0,1).toUpperCase() + z.substring(1);
	    	} else
	    		z = "ERROR: Número demaciado grande.";
	    } catch(Exception ex){
	    	z = "ERROR: Formato numérico incorrecto.";
	    }
	    return z;
	}
	
	private static String[] initVector(  ){
		String V[] = new String[45];
		V[0] = "cero";
		V[1] = "un";
		V[2] = "dos";
		V[3] = "tres";
		V[4] = "cuatro";
		V[5] = "cinco";
		V[6] = "seis";
		V[7] = "siete";
		V[8] = "ocho";
		V[9] = "nueve";
		V[10] = "diez";
		V[11] = "once";
		V[12] = "doce";
		V[13] = "trece";
		V[14] = "catorce";
		V[15] = "quince";
		V[16] = "dieciseis";
		V[17] = "diecisiete";
		V[18] = "dieciocho";
		V[19] = "diecinueve";
		V[20] = "veinte";
		V[21] = "treinta";
		V[22] = "cuarenta";
		V[23] = "cincuenta";
		V[24] = "secenta";
		V[25] = "setenta";
		V[26] = "ochenta";
		V[27] = "noventa";
		V[28] = "";
		V[29] = "";
		V[30] = "cien";
		V[31] = "doscientos";
		V[32] = "trescientos";
		V[33] = "cuatrocientos";
		V[34] = "quinientos";
		V[35] = "seiscientos";
		V[36] = "setecientos";
		V[37] = "ochocientos";
		V[38] = "novecientos";
		V[39] = "";
		V[40] = "";
		V[41] = "mil";
		V[42] = "millon";
		V[43] = "billon";
		V[44] = "trillon";
		return V;
	}
}
