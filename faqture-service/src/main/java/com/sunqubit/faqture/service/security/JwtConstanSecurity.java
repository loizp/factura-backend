package com.sunqubit.faqture.service.security;

public class JwtConstanSecurity {
		// Spring Security
		public static final String LOGIN_URL = "/login";
		public static final String REFRESH_TOKEN_URL = "/token";
		public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
		// JWT
		public static final String ISSUER_INFO = "https://www.faqture.com/";
		public static final String SUPER_SECRET_KEY = "@utHL0g1n";
		public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day
}
