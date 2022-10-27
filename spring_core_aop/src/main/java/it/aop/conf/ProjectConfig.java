package it.aop.conf;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"it.aop"})
@EnableAspectJAutoProxy
public class ProjectConfig {
}
