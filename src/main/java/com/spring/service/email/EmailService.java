package com.spring.service.email;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private MailSender mailSender = new JavaMailSenderImpl();
	
	public void sendEmail(String receiver, String sender, String subject, String message) {
 
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom(sender);
		smm.setTo(receiver);
		smm.setSubject(subject);
		smm.setText(message);
		
		try {
			mailSender.send(smm);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
