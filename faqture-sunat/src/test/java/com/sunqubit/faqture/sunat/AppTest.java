/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sunqubit.faqture.sunat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);

    public static void main(String[] args) throws MalformedURLException, IOException {
        String ruta = "http://www.sunat.gob.pe/cl-ti-itmrconsruc/jcrS00Alias";

        URL url = new URL(ruta);
        URLConnection connection = url.openConnection();

        for (int i = 0;; i++) {
            String headerName = connection.getHeaderFieldKey(i);
            String headerValue = connection.getHeaderField(i);

            if (headerName == null && headerValue == null) {
                break;
            }
            if ("Set-cookie".equalsIgnoreCase(headerName)) {
                String[] fields = headerValue.split(";\\s*");
                for (int j = 1; j < fields.length; j++) {
                    System.out.println(fields[j]);
                }
                LOGGER.info("la cabecera es: {}", headerName);
                LOGGER.info("ell cuerpo es: {}", headerValue);
                LOGGER.info("=========================================");
            }
        }
        

        

        LOGGER.info("final");
    }
}
