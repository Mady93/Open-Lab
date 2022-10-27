package it.springsecurity.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan({"it.springsecurity.controller","it.springsecurity.controller",
        "it.springsecurity.authentication.filters",
        "it.springsecurity.authentication.providers",
        "it.springsecurity.authentication.proxy" })
@Import({it.springsecurity.config.ProjectConfig.class,it.springsecurity.config.SecurityConfig.class})
public class SpringSecurityApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
 
    private static Class<SpringSecurityApplication> applicationClass = SpringSecurityApplication.class;

}
