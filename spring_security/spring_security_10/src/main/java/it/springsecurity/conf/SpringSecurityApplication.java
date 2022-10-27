package it.springsecurity.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "it.springsecurity.conf.comp", "it.springsecurity.service", "it.springsecurity.controller" })
@EnableJpaRepositories(basePackages = "it.springsecurity.repository")
@EntityScan(basePackages = "it.springsecurity.users")
@Import({it.springsecurity.conf.ProjectConfig.class, it.springsecurity.conf.UserManagementConfig.class})
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
