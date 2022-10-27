package it.springsecurity.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/helloAdminAndManager")
    public String helloAdminAndManager(Authentication authentication) {
        return "Hello "+ authentication.getName() +" your roles are "+ authentication.getAuthorities() +"!";
    }

    @GetMapping("/helloManager")
    public String helloManager(Authentication authentication) {
        return "Hello "+ authentication.getName() +" your roles are "+ authentication.getAuthorities() +"!";
    }

    @GetMapping("/helloUser")
    public String helloUser(Authentication authentication) {
        return "Hello "+ authentication.getName() +" your roles are "+ authentication.getAuthorities() +"!";
    }

    @GetMapping("/backend")
    public String backendClient() {
        return "Hello backend !";
    }
}
