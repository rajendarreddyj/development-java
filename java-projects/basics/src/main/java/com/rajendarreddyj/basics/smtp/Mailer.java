package com.rajendarreddyj.basics.smtp;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mailer {
    public static void main(final String[] args) {
        String from = "";
        String cc = "";
        String to = "test@hotmail.com";
        String host = "mail.xxxxx.com";
        String subject = "This is a Test mail";
        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.host", host);
            Session session = Session.getInstance(props, null);
            session.setDebug(false);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
            message.setSubject(subject);
            MimeMultipart mp = new MimeMultipart();
            mp.setSubType("related");
            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText("Sample Mail"); // Type your Text Message Here
            MimeBodyPart mbp2 = new MimeBodyPart();
            FileDataSource fds = new FileDataSource("examples/test1.html");
            mbp2.setDataHandler(new DataHandler(fds));
            mbp2.setFileName(fds.getName());
            mp.addBodyPart(mbp1); // mail body of the message
            mp.addBodyPart(mbp2); // Add your attachments here
            message.setContent(mp);
            message.setSentDate(new Date());
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}