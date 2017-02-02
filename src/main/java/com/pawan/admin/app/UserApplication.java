package com.pawan.admin.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.pawan.admin")
@EntityScan(basePackages="com.pawan.admin.model")
@EnableJpaRepositories("com.pawan.admin.repository")
public class UserApplication extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		 return application.sources(UserApplication.class);
	}
	
    public static void main(String[] args) throws Exception{
		SpringApplication.run(UserApplication.class, args);
	}
}
