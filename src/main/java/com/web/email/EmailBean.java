package com.web.email;

import java.io.Serializable;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private EmailService emailService;

	private String receiver;
	private String message;
	private AppUser appUser;
    private String login;
    
    private UserModel user;
    
    private static Logger LOGGER = Logger.getLogger("InfoLogging");
	
	public void sendEmail() {

		try{
		appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		login = (Objects.nonNull(appUser)) ? appUser.getUsername() : null;
		user = userService.findUserByLogin(login);
		setReceiver(user.getLogin());

		setMessage("Your order has been registered. Thank you for choosing our Florist's.");

		emailService.sendEmail(receiver, message);
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Success", new StringBuilder("Email is sending!").toString()));
		
		LOGGER.info("Email was sent to user");
		}
		catch(MailException mex){
			mex.printStackTrace();
			LOGGER.error("Email was not sent!");
		}
	}
}
