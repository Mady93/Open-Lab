package it.mobileapp.auth.components;

import it.mobileapp.auth.model.CustomUserAuthentication;
import it.mobileapp.auth.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserDetails user = userDetailsService.loadUserByUsername(username);
		return checkPassword((CustomUserDetails) user, password, passwordEncoder);
	}

	private Authentication checkPassword(CustomUserDetails user, String rawPassword, PasswordEncoder encoder) {
		if (encoder.matches(rawPassword, user.getPassword())) {
			return new CustomUserAuthentication(user.getUsername(), user.getPassword(),user.getBusinessUserId(),
					user.getAuthorities());
		} else {
			throw new BadCredentialsException("Bad credentials");
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return CustomUserAuthentication.class.isAssignableFrom(aClass);
	}
}
