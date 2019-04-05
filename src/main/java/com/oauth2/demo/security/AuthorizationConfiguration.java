package com.oauth2.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.oauth2.demo.user.CustomUserDetailsService;

/**
 * 
 * @author Jishnu
 *
 *configuring authorization server
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationConfiguration extends AuthorizationServerConfigurerAdapter {

	//providing our own userdetailsService
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	//we use the in-memory token-storage for the time being
	@Autowired
	private TokenStore tokenStore;

	//token expiry default time is 30 minutes(for noobs:- 1800 seconds/60 seconds= 30 minutes)
	@Value("${auth.token.expiry}")
	private int expiration;

	//client id provide this in basic auth
	@Value("${auth.client.id}")
	private String clientId;

	//client secret provide this in basic auth
	@Value("${auth.client.secret}")
	private String clientSecret;

	//scopes are the access that granted to an authentication token
	@Value("${auth.scope}")
	private String[] scopes;

	@Value("${auth.grant.types}")
	private String[] authGrantTypes;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(clientId).secret(passwordEncoder().encode(clientSecret))
				.accessTokenValiditySeconds(expiration).scopes(scopes)
				.authorizedGrantTypes(authGrantTypes);
	}
}
