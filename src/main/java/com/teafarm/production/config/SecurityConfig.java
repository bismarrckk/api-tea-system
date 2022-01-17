package com.teafarm.production.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.teafarm.production.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired 
	UserService userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CustomFilter customFilter=new CustomFilter();
		customFilter.setAuthenticationManager(authenticationManager());
		http
		.csrf().disable()
		.addFilterAt(customFilter,UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/accounts/**").hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.POST, "/accounts").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.PATCH, "/books/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.POST, "/login/").permitAll()
        .and()
        .formLogin().disable();
        
	}
	
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authenticationProvider());
	}
	 
	@Bean
	   public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
	

}
