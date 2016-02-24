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
import com.spring.service.AddressService;
import com.spring.service.LogService;
import com.spring.service.RoleService;
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
	private String zipKode;
	private String city;
	private String street;
	private String number;
	private String roleName;
	private AddressModel address;
	private RoleModel role;
	private String time;
	private List<UserModel> users = new ArrayList<>();
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LogService logService;
	
	public String showUsers() {
		logService.logInfo("showUsers :: starting...");
		setUsers(userService.getUsers());
		logService.logInfo("showUsers :: complete");
		
		return "/pages/secure/list?faces-redirect=true";
	}

	public String createUser() {
		logService.logInfo("createUser :: starting...");
		AddressModel a = addressService.exists(zipKode, city, street, number);
		RoleModel r = roleService.exists(roleName);
		
		if(a == null){
			address = new AddressModel(zipKode, city, street, number);
		}
		else
			address = a;
		
		if(r == null){
			role = new RoleModel(roleName);
		}
		else
			role = r;
		
		boolean successUser = userService.createUser(name, surname, login, password, address, role);
		logService.logInfo("createUser :: complete");
		
		if (successUser) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Success", new StringBuilder("User ").append(login).append(" created!").toString()));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					new StringBuilder("Unable to create user ").append(login).toString()));
		}
		
		return null;
	}

}
