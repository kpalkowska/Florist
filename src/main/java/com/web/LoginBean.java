package com.web;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.Data;

@ManagedBean(name = "loginBean", eager = true)
@RequestScoped
@Component
public @Data class LoginBean implements Serializable {

	private static final long serialVersionUID = 1549481937223946546L;

	private String username;
	private String password;

	@Autowired
	private AuthenticationManager authenticationManager;

	public String login() {
		try {
			Authentication request = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(this.username, this.password));
			SecurityContextHolder.getContext().setAuthentication(request);
			this.password = null;

			return "/pages/secure/products?faces-redirect=true";
		} catch (AuthenticationException e) {
		
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Unable to authenticate"));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Unable to authenticate"));
		}
		return null;
	}

	public String logout() {
		
		SecurityContextHolder.clearContext();
		return "/pages/unsecure/login?faces-redirect=true";
	}

	public String getCurrentUser() {
		FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		return null;
	}
	
}