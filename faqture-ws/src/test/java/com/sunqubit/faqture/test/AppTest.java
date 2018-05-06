package com.sunqubit.faqture.test;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

import com.sunqubit.faqture.ws.validators.FechaHoraValidator;

//import java.time.LocalDateTime;


public class AppTest {

    public static void main(String[] args) {
    	//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);
    	
    	//System.out.println("ROOT: " + passwordEncoder.encode("20f@qture$UNAT18"));
    	
    	//System.out.println("UWEB: " + passwordEncoder.encode("#WEB@pplicat10n"));
    	
    	String fh = "2001-02-20 23:20:33.0";
        
        String h = "00:00:00.78 AM";
    	FechaHoraValidator fv = new FechaHoraValidator("ddddma");
    	System.out.println("Fecha Hora: " + fh.matches(fv.getFechaHoraRegExp()));
        System.out.println("hora: " + fv.horaValida(h));
        FechaHoraValidator fv2 = new FechaHoraValidator("aaaaaaa-mmm-d");
    	Date femi = new Date();
        System.out.println(femi.toString());
        java.sql.Time aa = new java.sql.Time(femi.getTime());
        System.out.println(aa.toString());
        System.out.println(fv2.getFechaHoraRegExp());
        System.out.println(aa.toString().matches(fv2.getFechaHoraRegExp()));
        System.out.println(fv2.horaValida(aa));
        
        
        //LocalDateTime fecha = LocalDateTime.now();
        
        System.out.println("fecha sql: " + java.sql.Timestamp.valueOf(fh));
        
        //System.out.println(" P.M.".matches("[ ](?:([aA][mM])|([aA].[mM].))"));
        
        //String cader = "dddd-m-a";
        //System.out.println ("Original: " + cader);
        //String cad = cader.replaceAll("(.)\\1+", "$1");
        //System.out.println ("Resultado: " + cad);
        //System.out.println ("Resultado2: " + cad.substring(1, 2));
    }
}
