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

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.voxmail.mail.MailConnection;


/**
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class UpdateMessageAction extends Action {
    
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
        
        //Http Session Context
        String cmd = request.getParameter("cmd");
        String msgNum = request.getParameter("msgNum");
        System.out.println("UpdateMessageAction: cmd: " + cmd + ", msgNum: " + msgNum);
        
        
        MailConnection mailConnection = (MailConnection)request.getSession().getAttribute("MailConnection");
        this.setMessageStatus(cmd,msgNum,mailConnection);
        
        System.out.println("UpdateMessageAction: forwarding to updateMessage.jsp");
        return mapping.findForward("updateMessage");
    }
    
    protected void setMessageStatus(String cmd, String msgNum, MailConnection mailConnection) {
        
        try {
            Folder inbox = mailConnection.getInbox();
            Message message = inbox.getMessage(Integer.parseInt(msgNum));
            
            //System.out.println("SETTING FLAG................................");
            
            //remove existing flags
            //message.setFlag(Flags.Flag.SEEN, false);
            //message.setFlag(Flags.Flag.DELETED, false);
            //message.setFlag(Flags.Flag.RECENT, false);
            
            Flags f = new Flags();
            
            //setup the flag we're going to set
            Flags.Flag flag = null;
            if ("save".equals(cmd)) {
                System.out.println("UpdateMessageAction: Setting message to saved by setting SEEN");
                message.setFlag(Flags.Flag.SEEN,true);
                message.setFlag(Flags.Flag.FLAGGED,false);
                
            } else if ("delete".equals(cmd)) {
                System.out.println("UpdateMessageAction: Setting message to deleted by setting DELETED");
                //flag = Flags.Flag.DELETED;
                //f.add(flag);
                //message.setFlags(f,true);
                message.setFlag(Flags.Flag.DELETED,true);
                
            } else { //Skipped
                System.out.println("UpdateMessageAction: Setting message to skipped by setting SKIPPED");
                message.setFlag(Flags.Flag.SEEN,false);
                message.setFlag(Flags.Flag.FLAGGED,true);
            }
            
            //message.setFlags(f, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
