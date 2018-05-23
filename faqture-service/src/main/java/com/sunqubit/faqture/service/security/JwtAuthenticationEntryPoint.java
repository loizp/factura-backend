package com.sunqubit.faqture.service.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // Esto se invoca cuando el usuario intenta acceder a un recurso REST protegido sin proporcionar ninguna credencial
        // Solo debemos enviar una respuesta 401 no autorizada porque no hay una "página de inicio de sesión" para redirigir
        
    	Boolean ok = false;
		int code = 403;
		String msg = "No está autorizado para usar el recurso solicitado";
		
		String res = "{\n\"success\": "+ ok +",\n\"code\": "+ code +",\n\"message\": \"" + msg + "\"\n}";
        
    	try {
            response.setContentType("application/json");
            response.setStatus(code);
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(res);
            out.close();
        } catch (IOException e) {
        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sin autorización");
            LOGGER.info(e.getMessage());
        }
    }

}
