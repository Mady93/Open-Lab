package it.mobileapp.secure.components;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import it.mobileapp.secure.model.JWTAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

	private static final String SECRETKEY = Base64.getEncoder().encodeToString("abc1234".getBytes());

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("JWTAuthenticationProvider");
		JWTAuthentication jwtAuthentication = (JWTAuthentication) authentication;
		String token = jwtAuthentication.getToken();
		// Token validation
		if(!validateToken(token)) {
			System.out.println("invalid token");
			throw new AuthenticationException("Invalid Token!"){};
		}
		// Token processing
		try {
			System.out.println("token valid");
			jwtAuthentication.setPrincipal(getUserName(token));
			jwtAuthentication.setAuthorities(getRoles(token));
			jwtAuthentication.setBusinessUserId(getBusinessUserId(token));
			jwtAuthentication.setAuthenticated(true);
			System.out.println(getUserName(token));
			System.out.println(getRoles(token));
			System.out.println(getBusinessUserId(token));
			return jwtAuthentication;
		}catch (RuntimeException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			throw new AuthenticationException("Token processing error!"){};
		}
	}

	private boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token);
			if (claims.getBody().getExpiration().before(new Date())) { return false; }
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private String getUserName(String token) throws ExpiredJwtException {
		return Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token).getBody().getSubject();
	}

	private Collection<? extends GrantedAuthority> getRoles(String token) {
		List<String> roles = (List<String>)Jwts.parser().setSigningKey(SECRETKEY).
				parseClaimsJws(token).getBody().get("roles");
		return roles.stream().map(a -> new SimpleGrantedAuthority(a))
				.collect(Collectors.toList());
	}

	private Integer getBusinessUserId(String token) {
		return (Integer) Jwts.parser().setSigningKey(SECRETKEY).
				parseClaimsJws(token).getBody().get("businessUserId");
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return JWTAuthentication.class.isAssignableFrom(aClass);
	}
}
