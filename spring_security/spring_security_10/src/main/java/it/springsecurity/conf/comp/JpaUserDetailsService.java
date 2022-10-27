package it.springsecurity.conf.comp;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import it.springsecurity.repository.UserRepository;
import it.springsecurity.users.CustomUserDetails;
import it.springsecurity.users.User;

@Component
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public CustomUserDetails loadUserByUsername(String username) {
		Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("Problem during authentication!");
		User u = userRepository.findUserByUsername(username).orElseThrow(s);
		return new CustomUserDetails(u);
	}
}
