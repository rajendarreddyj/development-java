/*
 * The contents of this file are subject to the Mozilla Public License Version 1.1 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for the specific language governing rights and limitations under the License. The
 * Original Code is vox-mail. The Initial Developer of the Original Code is Voxeo Corporation. Portions created by Voxeo
 * are Copyright (C) 2000-2007. All rights reserved. Contributor(s): ICOA Inc. <info@icoa.com> (http://icoa.com)
 */

package org.voxmail.struts.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.voxmail.Voxmail;
import org.voxmail.VoxmailException;
import org.voxmail.mail.MailSender;
import org.voxmail.model.Mailbox;
import org.voxmail.utils.WebUtil;

import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

public class LeaveMessageAction extends Action {
    private static final Logger logger = Logger.getAnonymousLogger();
    static DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
    public String filePath_Messages = null; // directory where recorded messages are saved
    public String filePath_Greetings = null; // directory where recorded messages are saved
    public String smtpHost = null;
    public String smtpUserAccount = null;
    public String aaRedirect = null;
    public static boolean isInitialized = false;

    /**
     * Method execute
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     */
    @Override
    public ActionForward execute(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) {

        try {
            this.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("vxml");
        response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // prevents caching at the proxy server

        String contactId = request.getParameter("contactId");
        request.getParameter("cmd");
        request.getParameter("callerId");
        request.getParameter("mailboxId");

        String useImap = Voxmail.getProps().getProperty("useImap");
        boolean isImap = false;
        if ((useImap != null) && useImap.toLowerCase().equals("true")) {
            isImap = true;
        }

        if (!WebUtil.isEmpty(contactId)) {

            logger.info(df.format(new Date()) + ": INCOMING contactId is " + contactId);

            Mailbox mailbox = null;
            try {
                mailbox = Voxmail.getInstance().getVoxmailService().retrieveMailboxByContactId(contactId);
            } catch (VoxmailException e) {
                // this is bad, cover it up! but send email and log.
                e.printStackTrace();
                request.setAttribute("msg", "tryAgain: Voxmail exception: " + e.toString());
                return mapping.findForward("vxmlError");
            }
            if (mailbox != null) {
                HttpSession session = request.getSession();
                session.setAttribute("mailbox", mailbox);
                logger.info("Mailbox id retrieved for mailbox: " + mailbox.getMailboxId());

                String greetingPath = Voxmail.getProps().getProperty("filePath_Greetings");
                greetingPath = request.getContextPath() + greetingPath;
                request.setAttribute("greetingPath", greetingPath);

                return mapping.findForward("leaveMessage");
            } else {
                logger.info("Mailbox not found for contact id: " + contactId);
                return mapping.findForward("error");
            }
        } else // Leaving a message
        {
            logger.info(df.format(new Date()) + ": Leaving a message.");
            String forward = "";
            // Send the email
            try {
                forward = this.processAndSendRecording(request, response, isImap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (forward.equals("saveandtransfer")) {
                String host = request.getServerName();
                if ((request.getServerPort() > 0) && (request.getServerPort() != 80)) {
                    host = host + ":" + request.getServerPort();
                }
                host = "http://" + host;
                host = host + this.aaRedirect;
                request.setAttribute("redirect", host);
            }
            return mapping.findForward(forward);

        }

    }

    public void init() throws ServletException {

        if (isInitialized) {
            return;
        } else {
            isInitialized = true;
        }
        // get our recorded messages path
        this.filePath_Messages = Voxmail.getInstance().getBasePath() + Voxmail.getProps().getProperty("filePath_Messages");

        // test the directory where we'll be saving recorded messages
        File dir = new File(this.filePath_Messages);
        if (!dir.exists() || !dir.isDirectory()) {
            // logger.info("Could not find filePath_Messages: " + filePath_Messages);
            throw new ServletException("Could not find filePath_Messages: " + this.filePath_Messages);
        } else {
            if (!this.filePath_Messages.endsWith("/") && !this.filePath_Messages.endsWith("\\")) {
                this.filePath_Messages += File.separator;
            }
        }

        // get our recorded greetings path
        this.filePath_Greetings = Voxmail.getInstance().getBasePath() + Voxmail.getProps().getProperty("filePath_Greetings");

        // test the directory where we'll be saving recorded greetings
        File dir2 = new File(this.filePath_Greetings);
        if (!dir2.exists() || !dir2.isDirectory()) {
            // logger.info("Could not find filePath_Greetings: " + filePath_Greetings);
            throw new ServletException("Could not find filePath_Greetings: " + this.filePath_Greetings);
        } else {
            if (!this.filePath_Greetings.endsWith("/") && !this.filePath_Greetings.endsWith("\\")) {
                this.filePath_Greetings += File.separator;
            }
        }

        this.smtpHost = Voxmail.getProps().getProperty("smtpHost");
        this.smtpUserAccount = Voxmail.getProps().getProperty("smtpUserAccount");

        this.aaRedirect = Voxmail.getProps().getProperty("autoattendantRedirect");

        logger.info("LeaveMessageAction filePath_Messages=" + this.filePath_Messages);
        logger.info("LeaveMessageAction filePath_Greetings=" + this.filePath_Greetings);

    }

    // sends voicemail message at audioPath to the contact defined by the mailboxId
    protected void sendMail(final String mailboxId, final String audioPath, final String callerId, final boolean isImap) {
        Mailbox mailbox = null;
        try {
            logger.info("LeaveMessageAction.sendMail mailboxId: " + mailboxId);
            mailbox = Voxmail.getInstance().getVoxmailService().retrieveMailbox(mailboxId);
        } catch (VoxmailException e) {
            // this is bad, cover it up! but send email and log.
            e.printStackTrace();
        }

        // get the appropriate mailurl
        String mailurl = mailbox.getMailUrl();

        MailSender mail = new MailSender();

        // Send to Local mailbox
        mail.sendMail(mailurl, audioPath, callerId, mailbox, this.smtpHost, this.smtpUserAccount);

        // Send Copy to Email address if not using IMAP
        if (!isImap && ((mailbox.getEmail() != null) && (mailbox.getEmail().length() > 0))) {
            mail.sendMail("POP", audioPath, callerId, mailbox, this.smtpHost, this.smtpUserAccount);
        }

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * 
     * @param request
     *            servlet request
     * @param response
     *            servlet response
     */
    protected String processAndSendRecording(final HttpServletRequest request, final HttpServletResponse response, final boolean isImap)
            throws ServletException, java.io.IOException {

        long savedFileSize = 0; // size of file, if saved
        String mailboxId = "";
        String cmd = "";

        logger.info("#---------------------------------------------------#");
        logger.info("LeaveMessageAction - Attempting to parse stream...");

        try {

            MultipartParser mp = new MultipartParser(request, 25 * 1024 * 1024); // 25MB
            Part part;

            String callerId = null;
            FilePart filePart = null;
            ByteArrayOutputStream bout = null;
            String audioFileName = null;

            while ((part = mp.readNextPart()) != null) {

                String name = part.getName();
                if (part.isParam()) {
                    // it's a parameter part
                    ParamPart paramPart = (ParamPart) part;
                    String value = paramPart.getStringValue();

                    // get important params
                    if ("mailboxId".equals(name)) {
                        mailboxId = value;
                    } else if ("callerId".equals(name)) {
                        callerId = value;
                    } else if ("cmd".equals(name)) {
                        cmd = value;
                    }

                    logger.info("name=" + name + ", value=" + value);

                } else if (part.isFile()) {
                    // put the file into a ByteArrayOutputStream. We'll save it when we finish parsing
                    filePart = (FilePart) part;

                    audioFileName = filePart.getFileName();
                    logger.info("filename=" + audioFileName);

                    bout = new ByteArrayOutputStream();
                    filePart.writeTo(bout);
                }
            } // while

            // if we got a file, try to save it
            if ((filePart != null) && (bout != null)) {
                try {
                    if (audioFileName != null) { // we really do have a file!
                        logger.info("File is not null");

                        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_kk,mm,S").format(new Date());
                        String realPath = request.getSession().getServletContext().getRealPath(this.filePath_Messages);
                        String myPath = realPath + File.separator;
                        String myFileName = mailboxId + "." + audioFileName + "." + timeStamp;

                        // test directory
                        File dir = new File(myPath);
                        if (!dir.exists()) {
                            logger.info("Directory does not exist: " + myPath);
                        }

                        File file = new File(myPath + myFileName);

                        FileOutputStream fos = new FileOutputStream(file); // write data to temp file
                        bout.writeTo(fos);
                        fos.close();
                        savedFileSize = file.length();

                        if (savedFileSize > 0) {
                            // send to mail
                            this.sendMail(mailboxId, file.getAbsolutePath(), callerId, isImap);
                            logger.info("SAVED VM: " + file.getAbsolutePath());
                        }

                    } else {
                        logger.info("LeaveMessageAction - Empty or missing file part");
                    }
                } catch (Exception e) {
                    logger.info("Unable to save file: " + e.getMessage());
                }
            } else {
                logger.info("null file");
            }

            logger.info("LeaveMessageAction: mailboxId=" + mailboxId + "*");

        } catch (Exception e) {
            // try to at least grab the nextpage
            logger.info("Parsing failed: " + e.getMessage());
            logger.info("We're still going to try and redirect to nextpage.");
        }

        return cmd;

    }

}
