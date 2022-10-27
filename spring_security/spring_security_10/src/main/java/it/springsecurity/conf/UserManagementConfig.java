package it.springsecurity.conf;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import it.springsecurity.conf.comp.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class UserManagementConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new JpaUserDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		 Map<String, PasswordEncoder> encoders = new HashMap<>();
		 encoders.put("pbkdf2", new Pbkdf2PasswordEncoder("secret", 30000, 256));
		 encoders.put("bcrypt", new BCryptPasswordEncoder());
		 encoders.put("scrypt", new SCryptPasswordEncoder());
		 return new DelegatingPasswordEncoder("bcrypt", encoders);
	}
}