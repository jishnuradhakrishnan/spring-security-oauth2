package com.oauth2.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
/**
 * 
 * @author Jishnu
 *
 *overwriting the default spring boot security configurations
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	//defining a authentication manager bean
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	//here ignores the base url so that anyone can access it
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/");
	}
	
	//we use the in-memory token-storage for the time being
	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}
}
