package it.mobileapp.auth.service;

import java.util.function.Supplier;
import it.mobileapp.auth.entity.User;
import it.mobileapp.auth.model.CustomUserDetails;
import it.mobileapp.auth.repository.data.IUserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	private final IUserEntityRepository userRepository;

	@Autowired
	public JpaUserDetailsService(IUserEntityRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("Problem during authentication!");
		User user = userRepository.findUserByUsername(username).orElseThrow(s);
		return new CustomUserDetails(user);
	}
}
