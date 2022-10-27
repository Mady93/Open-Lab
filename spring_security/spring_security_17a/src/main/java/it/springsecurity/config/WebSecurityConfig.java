package it.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService uds() {
        var uds = new InMemoryUserDetailsManager();

        /*
        WARNING!
        Make sure the parameter you provide for the roles() method does
        not include the ROLE_ prefix. If that prefix is inadvertently included in the
        role() parameter, the method throws an exception. In short, when using
        the authorities() method, include the ROLE_ prefix. When using the
        roles() method, do not include the ROLE_ prefix.
         */

        //Autorizzazione
        //Significa che salviamo il nome dell'autorizzazione senza aggiungere nulla
        /*var u = User.withUsername("John")
                .password("12345")
                .authorities("READ")
                .build();*/
        //Ruolo
        //Significa che salviamo l'autorizzazione in realtà come ruolo grazie alla presenza del prefisso
        /*var u = User.withUsername("John")
                .password("12345")
                .authorities("ROLE_USER")
                .build();*/
        //Ruolo
        //Usando roles non abbiamo bisogno di specificare il prefisso
        var u = User.withUsername("John")
                .password("12345")
                .roles("USER")
                .build();
        uds.createUser(u);

        // Quindi se intendiamo utilizzare i ruoli con i metodi ..authority() dobbiamo salvare il nome dei ruoli con
        // il prefisso ROLE_

        // Si consiglia di utilizzare hasRole e anyRole salvando il nome dei ruoli senza prefisso
        // evitando di utilizzare i metodi authority interpretando i nomi come ruoli.
        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * Adds the AuthenticationManager
     * instance as a bean
     * in the Spring context
     *
     * @return AuthenticationManager
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
