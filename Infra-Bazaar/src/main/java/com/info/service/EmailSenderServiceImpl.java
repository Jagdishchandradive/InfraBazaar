package com.info.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements IEmailSenderService {
	private static final Logger logger = LoggerFactory.getLogger(EmailSenderServiceImpl.class);
	private final JavaMailSender mailSender;
	
	@Autowired
	public EmailSenderServiceImpl(final JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Async
	@Override
	public boolean sendSimpleEmail(String toEmail, String body, String subject)
			throws MailException, InterruptedException {
		try {
			logger.info("Sleeping now.. ");
			Thread.sleep(10000);

			// Validate parameters
			validateParameters(toEmail, body, subject);

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("infrabazaar22dac@gmail.com");
			message.setTo(toEmail);
			message.setText(body);
			message.setSubject(subject);

			mailSender.send(message);
			logger.info("Mail sent successfully!");
		} catch (Exception e) {
			logger.error("Error sending email", e);
			throw e;

		}
		return false;
	}
	private static void validateParameters(String toEmail, String body, String subject) {
		if (toEmail == null || toEmail.isEmpty()) {
			throw new IllegalArgumentException("Recipient email cannot be null or empty");
		}

		if (body == null || body.isEmpty()) {
			throw new IllegalArgumentException("Email body cannot be null or empty");
		}

		if (subject == null || subject.isEmpty()) {
			throw new IllegalArgumentException("Email subject cannot be null or empty");
		}
	}
}
