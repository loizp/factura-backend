/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sunqubit.faqture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunqubit.faqture.core.beans.Cliente;

public class AppTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		System.out.println("cliente encontrado");
		LOGGER.info("cliente encontrado");
		LOGGER.info("probando...");
	}
}
