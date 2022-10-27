package it.mobileapp.secure.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import it.mobileapp.secure.model.JWTAuthentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class JwtAuthenticationFilter implements Filter {

    private static final String SECRETKEY = Base64.getEncoder().encodeToString("abc1234".getBytes());
    private static final String PREFIX = "Bearer";
    private static final String EMPTY = "";
    private static final String AUTHORIZATION = "Authorization";

    private AuthenticationManager authenticationManager;

    public  JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        /* Costruzione oggetto Authentication partendo dal Token */
        Authentication authentication = getJWTAuthentication((HttpServletRequest) servletRequest);
        /* Se authentication è null significa che l'estrazione del token non è andata a buon fine*/
        if(authentication == null) {
            forbidden(servletResponse);
            System.out.println("AUTHENTICATION NULL");
        } else {
            /* Altrimenti abbiamo un oggetto Authentication valorizzato con il solo token */
            try {
                /* Sull'Authentication Manager richiamiamo il processo di autenticazione*/
                authentication = authenticationManager.authenticate(authentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println(authentication.isAuthenticated());
                if(authentication.isAuthenticated())
                    filterChain.doFilter(servletRequest, servletResponse);
                else
                    forbidden(servletResponse);
            } catch(AuthenticationException ex){
                ex.printStackTrace();
                forbidden(servletResponse);
            }
        }
    }

    private Authentication getJWTAuthentication(HttpServletRequest request) {
        String token = resolveToken(request);
        if(token != null) {
            return new JWTAuthentication(token);
        }
        return null;
    }

    private void forbidden(ServletResponse servletResponse){
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION);
        if(bearerToken != null && bearerToken.startsWith(PREFIX)) {
            return bearerToken.replace(PREFIX, EMPTY);
        }
        return null;
    }
}
