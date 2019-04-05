package com.oauth2.demo.user;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.oauth2.demo.commons.RepositorySupport;

@Repository
@Transactional
public class UserDetailsRepository extends RepositorySupport {

	public UserDetails loadUserByUsername(String username) {
		User user = (User) getSession().createQuery("from User where username='" + username + "'").uniqueResult();

		// this is a known bug
		user.getAuthorities().forEach((k) -> {
		});

		return user;
	}

	public void createUser(User user) throws Exception {
		getSession().save(user);

	}
}
