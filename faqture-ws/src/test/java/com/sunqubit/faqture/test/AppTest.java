package com.sunqubit.faqture.test;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AppTest {

    public static void main(String[] args) {
    	//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);
    	
    	//System.out.println("ROOT: " + passwordEncoder.encode("20f@qture$UNAT18"));
    	
    	//System.out.println("UWEB: " + passwordEncoder.encode("#WEB@pplicat10n"));
    	
    	String cadena = "2123456789";
    	System.out.println(cadena.matches("^[12][0]([0-9]{9})"));
    }
}
