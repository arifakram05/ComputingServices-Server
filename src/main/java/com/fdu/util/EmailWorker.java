package com.fdu.util;

import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.fdu.model.Email;

public final class EmailWorker {

	private final static BlockingQueue<Email> EMAIL_QUEUE = new ArrayBlockingQueue<>(20);
	private final static ExecutorService EXECUTOR = Executors.newFixedThreadPool(1);
	private final static Logger LOGGER = Logger.getLogger(EmailWorker.class);
	private static Session SESSION;

	public static void setupEmailWorker() {
		// setup email essentails
		final Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		SESSION = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("marif8150@gmail.com", "9491144784");
			}
		});
		// run email job
		startEmailingJob();
	}

	public static void sendEmail(Email email) {
		try {
			EMAIL_QUEUE.put(email);
		} catch (InterruptedException e) {
			LOGGER.error("Error occurred while processing email requests");
		}
	}

	private static Runnable task = () -> {
		while (true) {
			LOGGER.debug("Checking email queue for email requests");
			try {
				Email email = EMAIL_QUEUE.take();
				if (!processEmail(email)) {
					LOGGER.info("Could not send email to " + email.getTo());
				}
			} catch (InterruptedException e) {
				LOGGER.error("Error occurred while processing email requests");
			}
		}
	};

	private static void startEmailingJob() {
		EXECUTOR.submit(task);
	}

	private static boolean processEmail(Email email) {
		try {
			LOGGER.info("sending email to " + email.getTo());
			Message message = new MimeMessage(SESSION);
			// construct email
			message.setFrom(new InternetAddress("marif8150@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
			message.setSubject(email.getSubject());
			message.setText(email.getContent());
			// send email
			Transport.send(message);
		} catch (MessagingException messagingException) {
			LOGGER.error("Error while trying to send email - " + messagingException);
			return false;
		}
		return true;
	}

	public static void shutdown() {
		try {
			LOGGER.info("Shutting down executor");
			EXECUTOR.shutdown();
			EXECUTOR.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			LOGGER.info("Tasks Interrupted");
		} finally {
			if (!EXECUTOR.isTerminated()) {
				LOGGER.info("Cancel non-finished tasks");
			}
			EXECUTOR.shutdownNow();
			LOGGER.info("Executor shutdown complete");
		}
	}
}
