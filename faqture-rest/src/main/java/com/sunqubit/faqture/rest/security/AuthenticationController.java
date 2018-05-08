package com.sunqubit.faqture.rest.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sunqubit.faqture.beans.rest.JwtAuthenticationRequest;
import com.sunqubit.faqture.service.security.AuthSecurityService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	AuthSecurityService authSecurityService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody JwtAuthenticationRequest authenticationRequest) {
		return ResponseEntity.ok(authSecurityService.createAuthenticationToken(authenticationRequest));
	}

	@RequestMapping(value = "refresh", method = RequestMethod.GET)
	public ResponseEntity<?> refreshToken(HttpServletRequest request) {
		return ResponseEntity.ok(authSecurityService.refreshAndGetAuthenticationToken(request));
	}

}