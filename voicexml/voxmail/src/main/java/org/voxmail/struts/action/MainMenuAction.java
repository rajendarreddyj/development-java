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

import java.text.DateFormat;
import java.util.Date;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
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
import org.voxmail.VoxmailException;
import org.voxmail.mail.MailConnection;
import org.voxmail.model.Mailbox;
import org.voxmail.model.MessageWrapper;
import org.voxmail.model.Messages;

public class MainMenuAction extends Action {
    
    static DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
    
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
        
        response.setContentType("vxml");
        response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
        response.setHeader("Pragma","no-cache"); //HTTP 1.0
        response.setDateHeader("Expires", 0); //prevents caching at the proxy server
        
        System.out.println(df.format(new Date())+": MainMenuAction ");
        
        String cmd = request.getParameter("cmd");
        
        Mailbox mailbox = (Mailbox) request.getSession().getAttribute("mailbox");
        if (mailbox != null && mailbox.isFirstTime()) {
            return mapping.findForward("firstVisit");
        }
        
        String messagePath = Voxmail.getProps().getProperty("filePath_Messages");
        messagePath = request.getContextPath() + messagePath;
        request.setAttribute("messagePath",messagePath);
        
        if (cmd != null && cmd.equals("saved"))
        {
            request.setAttribute("folder","saved");
        }
        
        //For testing purposes
        if (mailbox == null)
        {
            String mailboxId = request.getParameter("mailboxId");
            try {
                
                mailbox = Voxmail.getInstance().getVoxmailService().retrieveMailbox(mailboxId);
                request.getSession().setAttribute("mailbox",mailbox);
                
            } catch (VoxmailException e) {
                // this is bad, cover it up! but send email and log.
                e.printStackTrace();
            }
        }
        
        String mailurl = mailbox.getMailUrl();
        MailConnection mail = this.getMailConnection(true, mailbox, request);
        Folder inbox = mail.getInbox();
        Messages messages = new Messages();

        try
        {
            //Message[] messageArray = inbox.getMessages();
            SearchTerm st = new HeaderTerm("Message-Context","voice-message");
            Message[] messageArray = inbox.search(st);
            
            boolean supportUser = inbox.getPermanentFlags().contains(Flags.Flag.USER);
            System.out.println("Support for user flags: " + supportUser);
            
            
            
            for (int i=0;i<messageArray.length;i++)
            {
                System.out.println("Getting message i: " + i);
                MessageWrapper mw = new MessageWrapper(messageArray[i]);
                messages.addMessage(mw);
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("MainMenuAction set messages count: " + messages.getMessageCount());
        request.setAttribute("messages",messages);
        
        return mapping.findForward("messages");
    }
    
    
    
    protected void emptyTrash(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        try {
            MailConnection mail = (MailConnection)httpSession.getAttribute("MailConnection");
            Folder inbox = mail.getInbox();
            inbox.expunge();
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error("MainController::emptyTrash() - failed: " + e.getMessage());
        }
    }
    
    protected MailConnection getMailConnection(boolean forceNew, Mailbox mailbox, HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        MailConnection mailConnection = null;
        
        if (forceNew) {
            //get existing connection from session
            mailConnection = (MailConnection)httpSession.getAttribute("MailConnection");
            
            if (mailConnection != null)
            {
                //close old connection first
                System.out.println("Closing inbox and message store");
                mailConnection.closeInbox();
                httpSession.removeAttribute("MailConnection");
            }
        }
        
        String mailurl = mailbox.getMailUrl(); //get the appropriate mailurl from the contact object
        
        //logger.debug("GETTING NEW MAIL CONNECTION...");
        System.out.println("MailURL: " + mailurl);
        
        try {
            mailConnection = new MailConnection(mailbox);
            httpSession.setAttribute("MailConnection", mailConnection); //put connection into session
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error("MainController::getMailConnection() - failed for: " + mailurl + " Exception=" + e.getMessage());
        }
        return mailConnection;
    }
    
    protected String setFlag(HttpServletRequest request) {
        
        HttpSession httpSession = request.getSession(false);
        String strReturn = "";
        
        try {
            MailConnection mail = (MailConnection)httpSession.getAttribute("MailConnection");
            Folder inbox = mail.getInbox();
            Message message = inbox.getMessage(Integer.parseInt(request.getParameter("msgindex")));
            String folder = request.getParameter("newfolder");
            
            //System.out.println("SETTING FLAG................................");
            //System.out.println("folder=" + folder);
            
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
            e.printStackTrace();
            strReturn = "Failed to update message status";
            //logger.error("MainController::setFlag() - failed: " + e.getMessage());
        }
        
        return strReturn;
    }
    
     protected void resetMessageStatus(Message message, MailConnection mailConnection) {
        
        try {
            
            System.out.println("SETTING FLAG for message: " + message.getMessageNumber());
            
            //remove existing flags
//            Flags flags = new Flags();
//            Flags.Flag flag1 = Flags.Flag.SEEN;
//            Flags.Flag flag2 = Flags.Flag.DELETED;
//            Flags.Flag flag3 = Flags.Flag.RECENT;
//            flags.add(flag1);
//            flags.add(flag2);
//            flags.add(flag3);
//            message.setFlags(flags,false);
            message.setFlag(Flags.Flag.SEEN, false);
            message.setFlag(Flags.Flag.DELETED, false);
            message.setFlag(Flags.Flag.RECENT, false);
            
            //message.saveChanges();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
}

