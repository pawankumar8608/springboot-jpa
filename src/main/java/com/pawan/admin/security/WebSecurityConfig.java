package com.pawan.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		  return new BCryptPasswordEncoder();
	}
	
	
	 @Override
     protected void configure(HttpSecurity http) throws Exception{
    	 http
    	      .authorizeRequests()
    	          .antMatchers("/resources/**", "/registration").permitAll()
    	          .anyRequest().authenticated()
    	          .and()
    	       .formLogin()
    	          .loginPage("/login")
    	          .usernameParameter("userName")
    	          .passwordParameter("password")
    	          .failureUrl("/login?error")
    	          .defaultSuccessUrl("/welcome")
    	          .permitAll()
    	          .and()
    	       .logout()
    	          .logoutUrl("/logout")
    	          .logoutSuccessUrl("/login?logout")
    	          .permitAll();
    	 
    	 http.sessionManagement().invalidSessionUrl("/login");
    	 http.csrf().disable();
     }
	 
	 @Autowired
	 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		    auth.userDetailsService(userDetailsService);
		    auth.authenticationProvider(authenticationProvider());
	 }
	 
	 @Bean
	 public DaoAuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
			authenticationProvider.setUserDetailsService(userDetailsService);
			authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
			return authenticationProvider;
	 }


}
