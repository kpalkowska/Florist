package com.web;

import java.io.Serializable;

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

import com.spring.service.LogService;

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

	@Autowired
	private LogService logService;

	public String login() {
		
		logService.logInfo("Start login");
		try {
			Authentication request = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(this.username, this.password));
			SecurityContextHolder.getContext().setAuthentication(request);
			this.password = null;

			logService.logInfo("Complete login");

			return "/pages/secure/list?faces-redirect=true";
		} catch (AuthenticationException e) {
			logService.logError("Unable to authenticate", e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Unable to authenticate"));
			return null;
		}
	}

	public String logout() {
		
		logService.logInfo("Start logout");
		SecurityContextHolder.clearContext();
		logService.logInfo("Complete logout");

		return "/pages/unsecure/login?faces-redirect=true";
	}

}