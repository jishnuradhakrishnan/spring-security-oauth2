package com.oauth2.demo.user;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*
		 * UserDto userDto = new UserDto();
		 * 
		 * userDto.setEnabled(true); userDto.setUsername("admin");
		 * userDto.setPassword(new BCryptPasswordEncoder().encode("password"));
		 * userDto.setRoles(new String[] {"user","admin","superadmin"});
		 * createUser(userDto);
		 */
		return userDetailsRepository.loadUserByUsername(username);
	}

	public boolean createUser(UserDto userDto) {
		boolean result = false;
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());

		String[] roles = userDto.getRoles();
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for(String role:roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		user.setEnabled(true);
		user.setAuthorities(authorities);

		try {
			userDetailsRepository.createUser(user);
			result = true;
		} catch (Exception e) {
			result = false;
			System.out.println("error!!  "+e.getLocalizedMessage());
		}
		return result;
	}
}
