package it.mobileapp.auth.web;

import static org.springframework.http.ResponseEntity.ok;
import it.mobileapp.auth.components.JwtAuthentication;
import it.mobileapp.auth.model.CustomUserAuthentication;
import it.mobileapp.auth.model.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAuthentication jwtAuthentication;
    
    @PostMapping(value="/auth/login")
    public ResponseEntity<Map<Object,Object>> login(@RequestBody UserLoginDTO userLogin) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new CustomUserAuthentication(userLogin.getUsername(),userLogin.getPassword(), null));
            CustomUserAuthentication customUserAuthentication = null;

            if(authentication instanceof CustomUserAuthentication) {
                customUserAuthentication = (CustomUserAuthentication) authentication;
                List<String> roles = customUserAuthentication.getAuthorities().stream().map(a -> a.getAuthority())
                        .collect(Collectors.toList());
                String username = (String) customUserAuthentication.getPrincipal();
                Integer  businessUserId = (Integer) customUserAuthentication.getBusinessUserId();
                String token = jwtAuthentication.createToken(username,businessUserId,roles);
                Map<Object,Object> model = new HashMap<>();
                model.put("token",token);
                return ok(model);
            }
            else throw new RuntimeException("Authentication error!");

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password!");
        }
    }
}
