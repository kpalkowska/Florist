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

import com.spring.model.AddressModel;
import com.spring.model.RoleModel;
import com.spring.model.UserModel;
import com.spring.service.TimeService;
import com.spring.service.UserService;

import lombok.Data;

@ManagedBean(name = "sessionBean", eager = true)
@SessionScoped
@Component
public @Data class SessionBean implements Serializable {

	private static final long serialVersionUID = 1549481937223946546L;

	private String name;
	private String surname;
	private String login;
	private String password;
	private AddressModel address;
	private RoleModel role;
	private String time;
	private List<UserModel> users = new ArrayList<>();
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TimeService timeService;
	
	public String showUsers() {
		setTime(timeService.getCurrentDateString());
		setUsers(userService.getUsers());
		
		return "/pages/secure/list?faces-redirect=true";
	}

	public String createUser() {
		setTime(timeService.getCurrentDateString());
		boolean success = userService.createUser(name, surname, login, password, address, role);
		
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
