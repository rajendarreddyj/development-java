/*
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 * The Original Code is vox-mail.
 *
 * The Initial Developer of the Original Code is Voxeo Corporation.
 * Portions created by Voxeo are Copyright (C) 2000-2007.
 * All rights reserved.
 * 
 * Contributor(s):
 * ICOA Inc. <info@icoa.com> (http://icoa.com)
 */

package org.voxmail.mail;

//for logging
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Category;
import org.voxmail.Voxmail;
import org.voxmail.model.Mailbox;
import org.voxmail.types.Contact;


/**
 * @author  Rick
 */
public class MailSender {
    
    /** Creates a new instance of VM_Create */
    public MailSender() {
    }
    
    public boolean sendMail(String mailurl, String filePath, String callerId, Mailbox mailbox) {
        return this.sendMail(mailurl, filePath, callerId, mailbox, null, null);
    }
    
    public boolean sendMail(String mailurl, String filePath, String callerId, Mailbox mailbox, String smtpHost, String smtpUserAccount) {
        
        
        System.out.println("sendMail() filePath=" + filePath);
        String useImap = Voxmail.getProps().getProperty("useImap");
        boolean isImap = false;
        if (useImap != null && useImap.equals("true"))
        {
            isImap = true;
        }
        
        boolean bSUCCESS = false;
        boolean isSMTP = false;
        
        try {
            Session s = null;
            
            if (mailurl.toUpperCase().startsWith("POP")) {
                isSMTP = true;
            
                Properties props = new Properties();
                
                //SMTP
                props.put("mail.smtp.host", smtpHost);
                s = Session.getInstance(props);
                
                //GMAIL SETTINGS
                //props.put("mail.smtp.host", "smtp.gmail.com");
                //props.put("mail.smtp.auth", "true");
                //props.put("mail.smtp.starttls.enable","true");
                //props.put("mail.smtp.port", "465");
                //props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
                //props.put("mail.smtp.socketFactory.fallback", "false");

                //Authenticator auth = new MyAuthenticator("email@email", "password");
                //s = Session.getInstance(props, auth);
                
                
            } else {
                s = Session.getInstance(new Properties());
            }
            
            //BUILD MESSAGE..........
            MimeMessage message = new MimeMessage(s);

            message.setFrom(new InternetAddress(mailbox.getEmail(), "Voice Mail"));
            System.out.println("Forwarding message to: " + mailbox.getEmail());
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailbox.getEmail(), mailbox.getEmail()));
            
            if (callerId == null)
                callerId = "";
            
            if (!isImap)
            {
                message.addHeader("X-Message-Path", filePath);
            }
            message.addHeader("X-Priority","1");
            message.addHeader("X-Caller-Id", callerId);
            message.addHeader("X-Mailer","Voxeo Voice Mail v0.5");
            message.addHeader("Message-Context", "voice-message");
            message.addHeader("content-class", "urn:content-classes:audio");
            
            String subject = "Voice Mail";
            if (!"".equals(callerId) && !"unknown".equalsIgnoreCase(callerId))
                subject += " From " + callerId;

            message.setSubject(subject);

            Multipart multipart = new MimeMultipart("voice-message");

            //add message text
            BodyPart part = new MimeBodyPart();
            DataSource source = new FileDataSource(filePath);
            part.setDataHandler(new DataHandler(source));
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd_HH-mm");
            String sDate = sdf.format(new Date());
            String filename = "vmail-" + sDate + ".wav";
            part.setFileName(filename);
            part.setDisposition(MimeBodyPart.INLINE);
            //part.setHeader("Content-Type", "multipart/voice-message");
            
            multipart.addBodyPart(part);
            
            message.setContent(multipart);
            message.setSentDate(new Date());
            

            //SEND MESSAGE..........
            if (isSMTP && !isImap) {
                System.out.println("Sending message via SMTP");
                Transport trans = s.getTransport("smtp");
                message.setFrom(new InternetAddress(smtpUserAccount, "Voice Mail"));
                
                try {
                    trans.connect();
                    Transport.send(message);
                } catch (Exception e) {
                    System.out.println("MailSender::sendMail() - failed to send via SMTP: " + e.getMessage());
                    e.printStackTrace();
                } finally {
                   trans.close();
                }
            } else {
                //IMAP or javamaildir
                MailConnection mail = null;;

                try {
                    mail = new MailConnection(mailbox);
                    Folder inbox = mail.getInbox();
                    inbox.appendMessages(new Message[]{(Message)message});
                    bSUCCESS = true;
                } catch (Exception e) {
                    //logger.debug("MailSender::sendMail() - failed to append message: " + e.getMessage());
                } finally {
                    if (mail != null)
                    {
                        mail.closeInbox();
                    }
                }
            }
            
        } catch (javax.mail.MessagingException me) {
            
            me.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }
        
        return bSUCCESS;
    } 
    
    public class MyAuthenticator extends Authenticator {
        
        String sUsername = null;
        String sPassword = null;
        
        public MyAuthenticator(String username, String password){
            // Assign username and password values passed in data from calling mail connection
            sUsername = username;
            sPassword = password;
        }
        
        protected PasswordAuthentication getPasswordAuthentication(){
            // username/password get authenticated next line
            return new PasswordAuthentication(sUsername, sPassword);
        }
        
    }
}
