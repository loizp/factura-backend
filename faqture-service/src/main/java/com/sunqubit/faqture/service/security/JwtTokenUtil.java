package com.sunqubit.faqture.service.security;

import static com.sunqubit.faqture.service.security.JwtConstanSecurity.ISSUER_INFO;
import static com.sunqubit.faqture.service.security.JwtConstanSecurity.SUPER_SECRET_KEY;
import static com.sunqubit.faqture.service.security.JwtConstanSecurity.TOKEN_EXPIRATION_TIME;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sunqubit.faqture.beans.core.Usuario;
import com.sunqubit.faqture.dao.contracts.IUsuarioDao;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	
	@Autowired
	IUsuarioDao usuarioDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    static final String CLAIM_KEY_EMISOR = "iss";
    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_ROLES = "aud";
    static final String CLAIM_KEY_EXPIRATION = "exp";
    static final String CLAIM_KEY_CREATED = "iat";

    public String getUsernameFromToken(String token) {
    	LOGGER.info("token: " + token);
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
            LOGGER.info("username: " + username);
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
    
    public String getIssuerFromToken(String token) {
        String emisor;
        try {
            final Claims claims = getClaimsFromToken(token);
            emisor = claims.getIssuer();
        } catch (Exception e) {
        	emisor = null;
        }
        return emisor;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String getRolesFromToken(String token) {
    	String authorities;
        try {
            final Claims claims = getClaimsFromToken(token);
            authorities = (String) claims.get(CLAIM_KEY_ROLES);
        } catch (Exception e) {
        	authorities = null;
        }
        return authorities;
    }
    
    private boolean validaDateUser(String loginName, String token) {
    	final Date created = getCreatedDateFromToken(token);
    	try {
			Usuario user = usuarioDao.login(loginName);
			return (created.before(new Date(user.getDateLogin().getTime())) 
					|| created.before(new Date(user.getDateUpKey().getTime())));
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			return false;
		}
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SUPER_SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
    	Map<String, Object> claims = new HashMap<>();
    	claims.put(CLAIM_KEY_EMISOR, ISSUER_INFO);
    	claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_ROLES, rolesConverter(userDetails));
        claims.put(CLAIM_KEY_EXPIRATION, generateExpirationDate());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
    
    private String rolesConverter(UserDetails userD) {
    	Set<String> authorities = AuthorityUtils.authorityListToSet(userD.getAuthorities());
    	return String.join(",", authorities);
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY)
                .compact();
    }
/*
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }
*/
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }
    
    public Boolean validateToken(String token, UserDetails uDetails) {
    	final String emisor = getIssuerFromToken(token);
        final String username = getUsernameFromToken(token);
        final String autorities = rolesConverter(uDetails);
        
        return (ISSUER_INFO.equals(emisor) && username.equals(uDetails.getUsername()) 
        		&& !isTokenExpired(token) && autorities.equals(getRolesFromToken(token))
        		&& !validaDateUser(uDetails.getUsername(),token));
    }

}
