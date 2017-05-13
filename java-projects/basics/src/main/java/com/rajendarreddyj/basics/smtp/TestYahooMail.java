package com.rajendarreddyj.basics.smtp;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestYahooMail {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        String smtpHost = "smtp.mail.yahoo.com";
        String from = "username";
        String pass = "password";
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        String[] to = { "test@gmail.com" };
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];
            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            logger.info(Message.RecipientType.TO.toString());
            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            message.setSubject("sending in a group");
            message.setText("Test Email Java SMTP");
            Transport transport = session.getTransport("smtp");
            transport.connect(smtpHost, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
