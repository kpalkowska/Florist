package com.web.email;

import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.spring.model.UserModel;
import com.spring.security.AppUser;
import com.spring.service.UserService;
import com.spring.service.email.EmailService;

import lombok.Data;

@ManagedBean(name = "emailBean")
@SessionScoped
public @Data class EmailBean {
	
	@Autowired
    UserService userService;
	
	@Autowired
	EmailService emailService;

	private String sender;
	private String receiver;
	private String subject;
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
		receiver = "klaudia.elblag@gmail.com";
		
		setSender("florist.project@gmail.com");
		setSubject("Florist");
		setMessage("Your order has been registered. Thank you for choosing our Florist's.");

		emailService.sendEmail(receiver, sender, subject, message);
		
		LOGGER.info("Email was sent to user");
		}
		catch(MailException mex){
			mex.printStackTrace();
			LOGGER.info("Email was not sent!");
		}
	}
}
