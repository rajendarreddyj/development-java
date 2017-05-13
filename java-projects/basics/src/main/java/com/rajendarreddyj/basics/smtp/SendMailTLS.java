package com.rajendarreddyj.basics.smtp;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        final String username = "test@gmail.com";
        final String password = "";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("test@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("test@gmail.com"));
            message.setSubject("Testing JavaMail API");
            message.setText("Dear Mail Crawler," + "\n\n This is a test message from my java application. Just ignore it");
            Transport.send(message);
            logger.info("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}