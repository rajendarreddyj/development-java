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

package org.voxmail.struts.action;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.search.HeaderTerm;
import javax.mail.search.SearchTerm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.voxmail.Voxmail;
import org.voxmail.mail.MailConnection;
import org.voxmail.model.Mailbox;


public class InboxAction extends Action {
    
    static DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
    
    protected String quota_count = "4096"; //max message count
    protected String quota_size = "25000000"; //max mailbox size in bytes
    protected String autocreatedir = "true"; //autocreate folder directories
    
    /**
     * Method execute
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        
        System.out.println("InboxAction:URL=" + request.getRequestURL().toString());
        HttpSession session = request.getSession(true);
                
        String cmd = request.getParameter("cmd"); //commands starting with '_' indicate retrieval
        String platform = request.getParameter("platform"); //html, vxml, etc
        
        String useImap = Voxmail.getProps().getProperty("useImap");
        boolean isImap = false;
        if (useImap != null && useImap.equals("true"))
        {
            isImap = true;
        }
        
        boolean skipRD = false; //skip redirection
        
        if (cmd == null)
            cmd = "";
        
        if (!"vxml".equals(platform) && !"cxml".equals(platform) && !"vhtml".equals(platform))
            platform = "html";
        
        String destination = "login";
        
        System.out.println("CMD=" + cmd + ", PLATFORM=" + platform);
        try
        {
            if ("doLogin".equals(cmd)) {
                //getCntact(request.getParameter("phone"), request.getParameter("pin"));            
                Mailbox mailbox = Voxmail.getInstance().getVoxmailService().login(request.getParameter("phone"), request.getParameter("pin"));

                if (mailbox != null) { //successful login
                    session.setAttribute("mailbox", mailbox); //put contact object in session for later
                    cmd = "_getmail"; //we'll process this command further down
                } else { //login failed
                    destination = "loginFailed";
                    session.invalidate();
                }
            }
            else if ("doPin".equals(cmd)) {
                String pin = request.getParameter("pin");
                String imapUsername = request.getParameter("imapUsername");
                String imapPassword = request.getParameter("imapPassword");
                String mailboxId = request.getParameter("mailboxId");
                
               Mailbox mailbox = Voxmail.getInstance().getVoxmailService().updateOptions(pin, imapUsername, imapPassword, mailboxId);
                    session.setAttribute("mailbox", mailbox); //put contact object in session for later
                    cmd = "_getmail"; //we'll process this command further down
               
            }

            if (cmd.startsWith("_")) { //we are on the secured side (retrieval, settings, etc)

                //security check
                Mailbox mailbox = (Mailbox)session.getAttribute("mailbox");
                if (mailbox != null) {      

                    if (session.getAttribute("MailConnection") == null) {
                        getMailConnection(true, mailbox, request);
                    }

                    destination = "inbox"; //default (catches _continue)

                    if ("_getmail".equals(cmd)) {
                        sortMail(request);
                        destination = "inbox";
                    } else if ("_getmsg".equals(cmd)) {
                        skipRD = true;
                        streamAudio(request, response);
                        return null;
                    } else if ("_movemsg".equals(cmd)) { //set msg flag
                        String status = setFlag(request);
                        sortMail(request);
                        request.setAttribute("status", status);
                        destination = "inbox";
                    } else if ("_refresh".equals(cmd)) {
                        //if (!isImap)
                        //{
                            getMailConnection(false, mailbox, request);
                            sortMail(request);
                        //}
                        destination = "inbox";
                    } else if ("_emptytrash".equals(cmd)) {
                        emptyTrash(request);
                        sortMail(request);
                        destination = "inbox";
                    } else if ("_disconnect".equals(cmd)) {
                        emptyTrash(request);
                        destination = "end";
                    } else if ("_pin".equals(cmd)) { //request to change pin code
                        destination = "pin";
                    } else if ("_updatepin".equals(cmd)) { //change the pin
                        updatePIN(request, mailbox.getMailboxId());
                        destination = "menu";
                    } else if ("_greeting".equals(cmd)) { //request to change greeting
                        destination = "record";
                    } else { //goto main menu
                        destination = "menu";
                    }

                } else {
                    if ("_disconnect".equals(cmd))
                    {
                        destination = "end";
                    }
                    else
                    {
                        destination = "unauthorized";   
                    }
                }
            } else if ("doLogout".equals(cmd)) {
                destination = "logout";
                session.invalidate();
            } else if ("record".equals(cmd)) {
                destination = "record";
            } else if ("menu".equals(cmd)) {
                destination = "menu";
            } else if ("showLogin".equals(cmd)) {            
                destination = "login";
                session.invalidate(); //remove anything we may have already had in the session
            } else if ("greet".equals(cmd) || cmd == null) { //play greeting
                Mailbox mailbox = Voxmail.getInstance().getVoxmailService().retrieveMailbox(request.getParameter("contactid"));//getContact(request.getParameter("contactid")); //get contact 
                request.setAttribute("mailbox", mailbox); //put contact object into request
                destination = "greeting";
            }

            System.out.println("destination=" + destination);

            if (destination != null && skipRD == false) {
                System.out.println("RD_DESTINATION=" + destination);
                //RequestDispatcher rd = getServletContext().getRequestDispatcher("/" + platform + "/" + destination);

                //if (rd != null)
                //    rd.forward(request, response);
                return mapping.findForward(destination);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return mapping.findForward("error");
    }
    
    //creates indicies for new/seen/deleted messages.
    //primarily used with VUI
    protected void sortMail(HttpServletRequest request) {
        //POTENTIALLY ONLY DO THIS FOR HTML
        
        HttpSession httpSession = request.getSession(false);
        try {
            MailConnection mail = (MailConnection)httpSession.getAttribute("MailConnection");
            if (mail == null)
            {
                return;
            }
            Folder inbox = mail.getInbox();
            SearchTerm st = new HeaderTerm("Message-Context","voice-message");
            Message[] messages = inbox.search(st);
            
            //setup our message index strings
            StringBuffer sbNewMsgs = new StringBuffer();
            StringBuffer sbSavedMsgs = new StringBuffer();
            StringBuffer sbDeletedMsgs = new StringBuffer();
            
            for (int i = 0; i < messages.length; i++) {

                Message message = messages[i];
                if (message.isSet(Flags.Flag.SEEN))
                    sbSavedMsgs.append((i+1) + ",");
                else if (message.isSet(Flags.Flag.DELETED))
                    sbDeletedMsgs.append((i+1) + ",");
                else
                    sbNewMsgs.append((i+1) + ",");
            }
            
            //put indicies in request
            request.setAttribute("newmsgs", sbNewMsgs.toString());
            request.setAttribute("savedmsgs", sbSavedMsgs.toString());
            request.setAttribute("deletedmsgs", sbDeletedMsgs.toString());
            
        } catch (Exception e) {
            //logger.error("MainController::sortMail() - failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    protected void emptyTrash(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);      
        try {
            MailConnection mail = (MailConnection)httpSession.getAttribute("MailConnection");
            Folder inbox = mail.getInbox();
            inbox.expunge();            
        } catch (Exception e) {
            //logger.error("MainController::emptyTrash() - failed: " + e.getMessage());
        }
    }
    
    protected void getMailConnection(boolean isNewConnection, Mailbox mailbox, HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);  
        MailConnection mail;
        
        if (!isNewConnection) {
            //get existing connection from session
            mail = (MailConnection)httpSession.getAttribute("MailConnection");
            
            //close old connection first            
            mail.closeInbox();
            httpSession.removeAttribute("MailConnection");
        }
        
        String mailurl = mailbox.getMailUrl(); //get the appropriate mailurl from the contact object
        
        //logger.debug("GETTING NEW MAIL CONNECTION...");
        System.out.println("MailURL: " + mailurl);

        try {
            mail = new MailConnection(mailbox);
            httpSession.setAttribute("MailConnection", mail); //put connection into session
        } catch (Exception e) {
            //logger.error("MainController::getMailConnection() - failed for: " + mailurl + " Exception=" + e.getMessage());
        }
    }
    
    protected void updatePIN(HttpServletRequest request, String mailboxId) {
        HttpSession httpSession = request.getSession(false);
        String strStatus = "Pin successfully updated";
        String pin = request.getParameter("pin");
        String imapUsername = request.getParameter("imapUsername");
        String imapPassword = request.getParameter("imapPassword");
        
        System.out.println("mailboxId=" + mailboxId);
        System.out.println("pin=" + pin);
        String webContext = request.getRealPath("/");
        
        try {
            //remove existing contact object from session
            httpSession.removeAttribute("mailbox");
            
            //update the pin            
            //DB_Contact db = new DB_Contact(fileContacts, fileCastorMapping);
            //Contact contact = db.updatePIN(contactID, pin);
            Mailbox mailbox = Voxmail.getInstance().getVoxmailService().updateOptions(pin, imapUsername, imapPassword ,mailboxId);
            
            //put the new contact object in the session
            httpSession.setAttribute("mailbox", mailbox);
            request.setAttribute("status", "your options have been updated");
            
        } catch (Exception e) {
            strStatus = "Failed to update PIN";
        }
        
        request.setAttribute("status", strStatus);
    }
    
    protected String setFlag(HttpServletRequest request) {
        
        HttpSession httpSession = request.getSession(false);
        String strReturn = "";
        
        try {
            MailConnection mail = (MailConnection)httpSession.getAttribute("MailConnection");
            Folder inbox = mail.getInbox();
            Message message = inbox.getMessage(Integer.parseInt(request.getParameter("msgindex")));
            String folder = request.getParameter("newfolder");
            
            System.out.println("SETTING FLAG................................");
            System.out.println("folder=" + folder);
                        
            //remove existing flags
            message.setFlag(Flags.Flag.SEEN, false);
            message.setFlag(Flags.Flag.DELETED, false);
            message.setFlag(Flags.Flag.RECENT, false);
            
            //setup the flag we're going to set
            Flags.Flag flag = null;
            if ("saved".equals(folder)) {
                flag = Flags.Flag.SEEN;
                strReturn = "Message has been saved";
            } else if ("deleted".equals(folder)) {
                flag = Flags.Flag.DELETED;
                strReturn = "Message has been deleted";
            } else {
                flag = Flags.Flag.RECENT;
                strReturn = "Message status set to new";                
            }
            
            Flags f = new Flags();
            f.add(flag);
            
            message.setFlags(f, true);
        } catch (Exception e) {
            strReturn = "Failed to update message status";
            //logger.error("MainController::setFlag() - failed: " + e.getMessage());
        }
        
        return strReturn;
    }
    
    protected void streamAudio(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);

        try {
            MailConnection mail = (MailConnection)httpSession.getAttribute("MailConnection");
            Folder inbox = mail.getInbox();
            Message message = inbox.getMessage(Integer.parseInt(request.getParameter("msgindex")));
            
            Multipart multipart = (Multipart)message.getContent();
            
            int i = 0;
            
            for(i = 0; i < multipart.getCount(); i++)
            {
                Part p2 = multipart.getBodyPart(i);  
                
                if (p2.isMimeType("text/plain"))
                {
                    System.out.println("MimeType: text/plain");
                    break;
                }
                else
                {
                    response.setContentType("audio/wav");
                    if(p2.getFileName() != null) {
                        System.out.println("Setting content disposition");
                        response.setHeader("Content-Disposition", "attachment; filename=\"" + p2.getFileName() + "\"");
                        OutputStream out = response.getOutputStream();
                        InputStream in = p2.getInputStream();
                        int c = in.read();
                        while(c != -1) {
                            out.write(c);
                            c = in.read();
                        }
                    }
                    else
                    {
                        System.out.println("Filename is null");
                    }
                }
            }
            
        } catch (Exception e) {
            //logger.error("MainController::streamAudio() - failed: " + e.getMessage());
        }
    }
    
}

