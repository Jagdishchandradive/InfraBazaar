package com.info.service;
import org.springframework.mail.MailException;

public interface IEmailSenderService {
	/**
	 * Sends a simple email.
	 *
	 * @param toEmail  The recipient's email address.
	 * @param body     The content of the email.
	 * @param subject  The subject of the email.
	 * @throws MailException if an error occurs during the email sending process.
	 */

	boolean sendSimpleEmail(String toEmail, String body, String subject) throws MailException, InterruptedException;

}
