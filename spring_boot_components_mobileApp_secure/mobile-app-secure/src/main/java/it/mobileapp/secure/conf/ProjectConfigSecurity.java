package it.mobileapp.secure.conf;

import it.mobileapp.secure.components.JWTAuthenticationProvider;
import it.mobileapp.secure.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@ComponentScan({"it.mobileapp.secure.components"})
@EnableWebSecurity
public class ProjectConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
    private JWTAuthenticationProvider jwtAuthentication;

	private JwtAuthenticationFilter authenticationFilter() throws Exception {
		return new JwtAuthenticationFilter(getAuthenticationManager());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(jwtAuthentication);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(authenticationFilter(), BasicAuthenticationFilter.class).authorizeRequests().
				antMatchers("/user/create").hasAnyRole("OPERATOR","USER").
				antMatchers("/user/update").hasAnyRole("OPERATOR","USER").
				antMatchers("/user/delete").hasRole("OPERATOR").
				antMatchers("/user/findById/*").hasAnyRole("OPERATOR","USER").
				antMatchers("/user/findUsers/*").hasRole("OPERATOR").
				antMatchers("/user/findUsersByFilter/*").hasRole("OPERATOR").
				antMatchers("/sim/create/*").hasRole("OPERATOR").
				antMatchers("/sim/user/activate/*").hasRole("OPERATOR").
				antMatchers("/sim/user/*").hasAnyRole("OPERATOR","USER").
				antMatchers("/sim/activate/*").hasRole("OPERATOR").
				antMatchers("/sim/deactivate/*").hasRole("OPERATOR").
				antMatchers("/product/create/*").hasRole("OPERATOR").
				antMatchers("/product/update/*").hasRole("OPERATOR").
				antMatchers("/product/delete/*").hasRole("OPERATOR").
				antMatchers("/product/list/*").hasRole("OPERATOR").
				antMatchers("/validation/create/*").hasRole("OPERATOR").
				antMatchers("/validation/get/*").hasRole("OPERATOR").
				and().csrf().disable().httpBasic().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

    @Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManager();
	}

}