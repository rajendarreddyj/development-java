package com.rajendarreddyj.basics.smtp;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class TestGMail {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        Properties props = System.getProperties();
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.auth", "true");
        Session session = Session.getInstance(props, null);
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress("test@gmail.com"));;
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("test@gmail.com", false));
            msg.setSubject("RajendarTEST " + System.currentTimeMillis());
            msg.setText("Hi rajendar, Scheduler started running");
            msg.setHeader("TEST EMAIL", "Rajendar's program");
            msg.setSentDate(new Date());
            SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
            t.connect("smtp.gmail.com", "test@gmail.com", "password");
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
            logger.info("Response: " + t.getLastServerResponse());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
