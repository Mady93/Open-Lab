package it.mobileapp.auth.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

public class CustomUserAuthentication extends UsernamePasswordAuthenticationToken {

    private final Integer businessUserId;

    public CustomUserAuthentication(Object principal, Object credentials, Integer businessUserId) {
        super(principal,credentials);
        this.businessUserId = businessUserId;
    }

    public CustomUserAuthentication(Object principal, Object credentials, Integer businessUserId, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.businessUserId = businessUserId;
    }

    public Integer getBusinessUserId() {
        return businessUserId;
    }
}
