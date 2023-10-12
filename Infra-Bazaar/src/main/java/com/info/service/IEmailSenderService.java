package com.info.service;
import org.springframework.mail.MailException;

public interface IEmailSenderService {
	
	void sendSimpleEmail(String toEmail, String body, String subject) throws MailException, InterruptedException;

}
