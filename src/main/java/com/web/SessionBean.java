package com.web;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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



	public String doAction() {
		logService.logInfo("doAction :: starting...");
		
		setTime(timeService.getCurrentDateString());
		userService.addUser(this.name);
		users = userService.getAllUsers();
		logService.logInfo("doAction :: complete");
		
		return "/views/response?faces-redirect=true";
	}
}
