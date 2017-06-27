package com.pawan.admin;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.pawan.admin")
@EntityScan(basePackages="com.pawan.admin.model")
@EnableJpaRepositories("com.pawan.admin.repository")
public class SimpleUserApplication {
	
	private static final Logger log = LoggerFactory.getLogger(SimpleUserApplication.class);

	
	/**
     * Main method, used to run the application.
     *
     * @param args the command line arguments
     * @throws UnknownHostException if the local host name could not be resolved into an address
     */
	
	public static void main(String[] args) throws UnknownHostException{
		SpringApplication app = new SpringApplication(SimpleUserApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\thttp://127.0.0.1:{}\n\t" +
                "External: \thttp://{}:{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            env.getProperty("server.port"),
            InetAddress.getLocalHost().getHostAddress());

	}

}
