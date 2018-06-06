package com.sunqubit.faqture.service.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.beans.rest.JwtAuthenticationRequest;
import com.sunqubit.faqture.beans.rest.JwtAuthenticationResponse;
import com.sunqubit.faqture.dao.contracts.IUsuarioDao;

@Service
public class AuthSecurityService {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenSecurity jwtTokenSecurity;
	
	@Autowired
	IUsuarioDao usuarioDao;
	
	public JwtAuthenticationResponse createAuthenticationToken(JwtAuthenticationRequest jwtAuthRequest) {
		
		if (autenticateUser(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword())) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
			final String token = jwtTokenSecurity.generateToken(userDetails);
			
			return new JwtAuthenticationResponse(true, "Entrega del token de autorización", token);
		} else
			return new JwtAuthenticationResponse(false, "No se puede iniciar sesión debido a: Datos de inicio de sesión incorrectos", "");
	}
	
	public JwtAuthenticationResponse refreshAndGetAuthenticationToken(HttpServletRequest request) {
		/*
		 * String token = request.getHeader(tokenHeader); String username =
		 * jwtTokenUtil.getUsernameFromToken(token); JwtUser user = (JwtUser)
		 * userDetailsService.loadUserByUsername(username);
		 * 
		 * if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate()))
		 * { String refreshedToken = jwtTokenUtil.refreshToken(token); return
		 * ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken)); } else {
		 * return ResponseEntity.badRequest().body(null); }
		 */
		return null;
	}
	
	private Boolean autenticateUser(String username, String password) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username,password));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			usuarioDao.dateLogin(username);
			return true;
		} catch (BadCredentialsException ex1) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
