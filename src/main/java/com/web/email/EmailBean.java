package com.web.email;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;
import org.springframework.stereotype.Component;

import com.spring.model.UserModel;
import com.spring.security.AppUser;
import com.spring.service.UserService;
import com.spring.service.email.EmailService;

import lombok.Data;

@ManagedBean(name = "emailBean", eager = true)
@SessionScoped
@Component
public @Data class EmailBean implements Serializable {
	
	private static final long serialVersionUID = 7291708796066664438L;
	
    private static Logger LOGGER = Logger.getLogger("InfoLogging");
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	public void sendEmail() {

		try{
		AppUser appUser = (AppUser) getContext().getAuthentication().getPrincipal();		
		UserModel user = null;
		
		if(appUser.getUsername() != null)
			user = userService.findUserByLogin(appUser.getUsername());
		
		String receiver = user.getLogin();
		String message = "Your order has been registered. Thank you for choosing our Florist's.";

		emailService.sendEmail(receiver, message);
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Success", new StringBuilder("Email is sending!").toString()));
		
		LOGGER.info("Email was sent to user");
		}
		catch(Exception mex){
			LOGGER.error("Email was not sent!");
		}
	}
}
