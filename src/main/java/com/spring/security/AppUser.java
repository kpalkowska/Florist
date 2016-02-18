package com.spring.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.spring.model.UserModel;

public class AppUser extends User {
	
	private static final long serialVersionUID = -7008391764109290746L;

	public AppUser(UserModel user) {
		this(user, Collections.emptyList());
	}

	public AppUser(UserModel user, Collection<GrantedAuthority> authorities) {
		super(user.getName(), user.getPassword(), authorities);
	}
}
