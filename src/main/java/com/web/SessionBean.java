package com.web;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.model.UserModel;
import com.spring.service.LogService;
import com.spring.service.TimeService;
import com.spring.service.UserService;

import lombok.Data;

@ManagedBean(name = "sessionBean", eager = true)
@SessionScoped
@Component
public @Data class SessionBean implements Serializable {

	private static final long serialVersionUID = 1549481937223946546L;

	private String name;
	private String time;

	private List<UserModel> users = new ArrayList<>();
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TimeService timeService;
	
	@Autowired
	private LogService logService;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String showUsers() {
		logService.logInfo("Start showing users");
		setTime(timeService.getCurrentDateString());
		setUsers(userService.getAllUsers());
		logService.logInfo("Showing users complete");
		
		return "/views/user/response?faces-redirect=true";
	}

	public String createUser() {
		logService.logInfo("Start creating user");
		setTime(timeService.getCurrentDateString());
		boolean success = userService.createUser(name);
		logService.logInfo("Creating user complete");
		
		if (success) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Success", new StringBuilder("User ").append(name).append(" created!").toString()));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					new StringBuilder("Unable to create user ").append(name).toString()));
		}
		
		return null;
	}
}
