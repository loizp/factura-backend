package com.sunqubit.faqture.ws.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sunqubit.faqture.core.beans.RolUsuario;
import com.sunqubit.faqture.core.beans.Usuario;
import com.sunqubit.faqture.core.daos.contracts.IUsuarioDao;

@Service
public class UserSecurityService implements UserDetailsService {
	
	@Autowired
    private IUsuarioDao usuarioDao;

	@Override
	public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
		Usuario userLogin = null;
		try {
			userLogin = usuarioDao.login(loginName);
		} catch (Exception e) {
			throw new UsernameNotFoundException("No se puede iniciar sesión debido a: " + e.getMessage());
		}
		
		if(userLogin == null) {
			throw new UsernameNotFoundException("No se puede iniciar sesión debido a: Usuario no encontrado");
		}
		
		return userBuilder(userLogin);
	}
	
	private User userBuilder(Usuario userToBuild) {
		Boolean enabled = true;
		Boolean accountNonExpired = true;
		Boolean credentialsNonExpired = true;
		Boolean accountNonLocked = true;
		
		return new User(userToBuild.getLoginName(), userToBuild.getPassword(), 
				enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, getGrantedAuthorities(userToBuild));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(Usuario userG){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for(RolUsuario role : userG.getRoles())
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        
        return authorities;
    }
}
