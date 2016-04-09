package com.spring.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.spring.model.UserModel;

public class AppUser extends User {

	private static final long serialVersionUID = -7008391764109290746L;

	private static final Collection<GrantedAuthority> DEFAULT_AUTHORITIES = Arrays
			.asList(new SimpleGrantedAuthority("ROLE_USER"));

	public AppUser(UserModel user) {
		this(user, DEFAULT_AUTHORITIES);
	}

	public AppUser(UserModel user, Collection<GrantedAuthority> authorities) {
		super(user.getLogin(), user.getPassword(), authorities);
	}
}
