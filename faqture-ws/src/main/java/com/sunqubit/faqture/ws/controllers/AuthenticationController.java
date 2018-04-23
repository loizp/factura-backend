package com.sunqubit.faqture.ws.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sunqubit.faqture.ws.RestEntitys.JwtAuthenticationRequest;
import com.sunqubit.faqture.ws.services.AuthSecurityService;

import static com.sunqubit.faqture.ws.security.JwtConstanSecurity.LOGIN_URL;
import static com.sunqubit.faqture.ws.security.JwtConstanSecurity.REFRESH_TOKEN_URL;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	AuthSecurityService authSecurityService;

	@RequestMapping(value = LOGIN_URL, method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody JwtAuthenticationRequest authenticationRequest) {
		return ResponseEntity.ok(authSecurityService.createAuthenticationToken(authenticationRequest));
	}

	@RequestMapping(value = REFRESH_TOKEN_URL, method = RequestMethod.GET)
	public ResponseEntity<?> refreshToken(HttpServletRequest request) {
		return ResponseEntity.ok(authSecurityService.refreshAndGetAuthenticationToken(request));
	}

}