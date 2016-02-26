package com.spring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.model.UserModel;

public class UserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	public static final Logger LOG = LoggerFactory.getLogger(UserDetailsAuthenticationProvider.class);
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		LOG.info("additionalAuthenticationChecks(UserDetails, UsernamePasswordAuthenticationToken)");
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		LOG.info("retrieveUser(String, UsernamePasswordAuthenticationToken)");
		
		UserModel user = new UserModel(username);
		return new AppUser(user, authentication.getAuthorities());
	}

}
