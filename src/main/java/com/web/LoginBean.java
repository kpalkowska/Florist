package com.web;

import java.io.Serializable;
import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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

	private static Logger LOGGER = Logger.getLogger("InfoLogging");

	@Autowired
	private AuthenticationManager authenticationManager;

	public String login() {
		try {
			Authentication request = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(this.username, this.password));
			SecurityContextHolder.getContext().setAuthentication(request);
			this.password = null;

			LOGGER.info("Correct login");
			return "/pages/secure/products?faces-redirect=true";
		} catch (AuthenticationException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Unable to authenticate"));
			LOGGER.error("Bad login");
		}
		return null;
	}

	public String logout() {
		SecurityContextHolder.clearContext();
		LOGGER.info("Correct logout");
		return "/pages/unsecure/login?faces-redirect=true";
	}
}