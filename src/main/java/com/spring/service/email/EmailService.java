package com.spring.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class EmailService {

	@Autowired
	MailSender mailSender;
	
	@Autowired
	SimpleMailMessage preConfiguredMessage;
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setPreConfiguredMessage(SimpleMailMessage preConfiguredMessage) {
		this.preConfiguredMessage = preConfiguredMessage;
	}	
	
	public void sendEmail(String to, String body) {
 
		SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);	
		mailMessage.setTo(to);
		mailMessage.setText(body);
		System.out.println(mailMessage);
		try{
			mailSender.send(mailMessage);
		} catch(MailException me){
			System.out.println("Cannot send email");			
			System.out.println(me);
		} catch (Exception e) {
			System.out.println("could not send email");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
