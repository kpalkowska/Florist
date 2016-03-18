package com.web.email;

import java.util.Properties;
import com.sun.mail.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import lombok.Data;

@ManagedBean(name = "emailBean")
@SessionScoped
public @Data class EmailBean {

	private static Logger LOGGER = Logger.getLogger("InfoLogging");

	public void sendEmail() {
		String username = "florist.project@gmail.com";
		String password = "florist2016";

		String host = "smtp.gmail.com";
		Properties props = new Properties();

	

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.host", host);
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", "25");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		String msgBody = "sending message";

		try {

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("florist.project@gmail.com"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("aneta.stypa7@wp.pl"));
			msg.setSubject("Your Example.com account has been activated");
			msg.setText(msgBody);
			Transport.send(msg);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Success", new StringBuilder("Email is sending").toString()));

		} catch (AddressException ex1) {

			LOGGER.error("address error");
			ex1.printStackTrace();
		} catch (MessagingException ex) {

			LOGGER.error("MessangigngError");
			ex.printStackTrace();
		}

	}
}

//
// @Autowired
// UserService userService;
//
// @Autowired
// EmailService emailService;
//
// private String sender;
// private String receiver;
// private String subject;
// private String message;
// private AppUser appUser;
// private String login;
//
// private UserModel user;
//
// private static Logger LOGGER = Logger.getLogger("InfoLogging");
//
// public void sendEmail() {
//
// try{
// appUser = (AppUser)
// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
// login = (Objects.nonNull(appUser)) ? appUser.getUsername() : null;
// user = userService.findUserByLogin(login);
// receiver = "klaudia.elblag@gmail.com";
//
// setSender("florist.project@gmail.com");
// setSubject("Florist");
// setMessage("Your order has been registered. Thank you for choosing our
// Florist's.");
//
// emailService.sendEmail(receiver, sender, subject, message);
//
// LOGGER.info("Email was sent to user");
// }
// catch(MailException mex){
// mex.printStackTrace();
// LOGGER.error("Email was not sent!");
// }
// }
// }
