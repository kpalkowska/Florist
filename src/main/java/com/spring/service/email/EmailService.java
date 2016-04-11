package com.spring.service.email;

import org.apache.log4j.Logger;
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

	private static Logger LOGGER = Logger.getLogger("InfoLogging");

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
		try {
			mailSender.send(mailMessage);
		} catch (MailException me) {
			LOGGER.error("Email was not sent!");
		} catch (Exception e) {
			LOGGER.error("Email was not sent!");
		}
	}
}
