package com.rajendarreddyj.basics.smtp;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class EmailWithPdf {
    private static final Logger logger = Logger.getAnonymousLogger();
    /**
     * Sends an email with a PDF attachment.
     */
    public void email() {
        String smtpHost = "smtp.gmail.com"; // replace this with a valid host
        int smtpPort = 465; // replace this with a valid port
        String sender = "test@gmail.com"; // replace this with a valid sender email address
        String recipient = "test@gmail.com"; // replace this with a valid recipient email address
        String content = "This is a test message from my java application. Just ignore it"; // this will be the text of
                                                                                            // the email
        String subject = "Testing JavaMail & iText API"; // this will be the subject of the email
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        Session session = Session.getDefaultInstance(properties, null);
        ByteArrayOutputStream outputStream = null;
        try {
            // construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(content);
            // now write the PDF content to the output stream
            outputStream = new ByteArrayOutputStream();
            this.writePdf(outputStream);
            byte[] bytes = outputStream.toByteArray();
            // construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("test.pdf");
            // construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);
            // create the sender/recipient addresses
            InternetAddress iaSender = new InternetAddress(sender);
            InternetAddress iaRecipient = new InternetAddress(recipient);
            // construct the mime message
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(iaSender);
            mimeMessage.setSubject(subject);
            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
            mimeMessage.setContent(mimeMultipart);
            // send off the email
            Transport.send(mimeMessage);
            logger.info("sent from " + sender + ", to " + recipient + "; server = " + smtpHost + ", port = " + smtpPort);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // clean off
            if (null != outputStream) {
                try {
                    outputStream.close();
                    outputStream = null;
                } catch (Exception ex) {
                }
            }
        }
    }

    /**
     * Writes the content of a PDF file (using iText API) to the {@link OutputStream}.
     * 
     * @param outputStream
     *            {@link OutputStream}.
     * @throws Exception
     */
    public void writePdf(final OutputStream outputStream) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        document.addTitle("Test PDF");
        document.addSubject("Testing email PDF");
        document.addKeywords("iText, email");
        document.addAuthor("Rajendar");
        document.addCreator("Rajendar");
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk("hello!"));
        document.add(paragraph);
        document.close();
    }

    /**
     * Main method.
     * 
     * @param args
     *            No args required.
     */
    public static void main(final String[] args) {
        EmailWithPdf demo = new EmailWithPdf();
        demo.email();
    }
}