package it.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig
        extends AuthorizationServerConfigurerAdapter {

    @Value("${jwt.key}")
    private String jwtKey;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        var service = new InMemoryClientDetailsService();

        var clientPasswordGrantType = new BaseClientDetails();
        var clientCodeGrantType = new BaseClientDetails();
        var clientClientCredentialsGrantType = new BaseClientDetails();

        // Client per password GRANT TYPE
        clientPasswordGrantType.setClientId("client1");
        clientPasswordGrantType.setClientSecret("secret");
        clientPasswordGrantType.setScope(List.of("ADMIN","MANAGER","USER"));
        clientPasswordGrantType.setAuthorizedGrantTypes(List.of("password","refresh_token"));
        clientPasswordGrantType.setRegisteredRedirectUri(Set.of("http://localhost:9090/client"));
        clientPasswordGrantType.setAccessTokenValiditySeconds(180);

        // Client per code GRANT TYPE
        clientCodeGrantType.setClientId("client2");
        clientCodeGrantType.setClientSecret("secret");
        clientCodeGrantType.setScope(List.of("MANAGER","USER"));
        clientCodeGrantType.setAuthorizedGrantTypes(List.of("authorization_code","refresh_token"));
        clientCodeGrantType.setRegisteredRedirectUri(Set.of("http://localhost:9090/client"));
        clientCodeGrantType.setAccessTokenValiditySeconds(180);

        // Client per client credentials GRANT TYPE
        clientClientCredentialsGrantType.setClientId("client3");
        clientClientCredentialsGrantType.setClientSecret("secret");
        clientClientCredentialsGrantType.setScope(List.of("USER"));
        clientClientCredentialsGrantType.setAuthorizedGrantTypes(List.of("client_credentials","refresh_token"));
        clientClientCredentialsGrantType.setRegisteredRedirectUri(Set.of("http://localhost:9090/client"));
        clientClientCredentialsGrantType.setAccessTokenValiditySeconds(180);

        service.setClientDetailsStore(Map.of("client1", clientPasswordGrantType,
                "client2",clientCodeGrantType, "client3",clientClientCredentialsGrantType ));
        clients.withClientDetails(service);
    }

    /**
     * With these configurations in place, we now have users who can authenticate at our
     * authentication server
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore())
                .accessTokenConverter(jwtAccessTokenConverter());
        //endpoints.authenticationManager(authenticationManager);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        var converter = new JwtAccessTokenConverter();
        converter.setSigningKey(jwtKey);
        return converter;
    }
}
