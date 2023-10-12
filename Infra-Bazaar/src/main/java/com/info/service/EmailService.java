package com.info.service;

import org.springframework.stereotype.Service;

import java.util.Properties;

import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

	public boolean sendEmail(String subject, String message, String to) {
		boolean f = false;
		String from = "infrabazaar22dac@gmail.com";

		// variable of gmail

		String host = "smtp.gmail.com";
		// get s/m properties

		Properties properties = System.getProperties();

		System.out.println("PROPERTIES " + properties);

		// set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// get session obj
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("infrabazaar22dac@gmail.com", "Pass@123");
			}
		});

		session.setDebug(true);

		// compose the message
		MimeMessage m = new MimeMessage(session);

		try {
			m.setFrom(from);

			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			m.setSubject(subject);

			// m.setText(message);
			m.setContent(message, "text/html");

			// send the message
			Transport.send(m);

			System.out.println("Sent Success");
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
