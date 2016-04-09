package com.spring.service.email.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.service.email.EmailService;

@ContextConfiguration(locations = { "classpath:/mail-context-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SendEmailTest {

	@Autowired
	EmailService emailService;

	@Test
	public void sendEmailTest() {
		String receiver = "klaudia.elblag@gmail.com";
		String message = "Your order has been registered. Thank you for choosing our Florist's.";

		emailService.sendEmail(receiver, message);
	}
}
