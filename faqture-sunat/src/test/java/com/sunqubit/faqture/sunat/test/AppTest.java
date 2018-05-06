package com.sunqubit.faqture.sunat.test;

import java.time.LocalDateTime;

import com.sunqubit.faqture.sunat.utils.FuncionesUtiles;

public class AppTest {
	 public static void main(String[] args) {
		 
		 //String rutaprueba = FuncionesUtiles.getDirectorio("prueba", java.sql.Timestamp.valueOf(LocalDateTime.now()));
		String rutaprueba = FuncionesUtiles.getPropertyValue("keystoreType", "prueba");
		 System.out.println(rutaprueba);
	 }
}
