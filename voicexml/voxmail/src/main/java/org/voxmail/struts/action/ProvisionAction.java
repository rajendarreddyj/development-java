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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.voxmail.Voxmail;
import org.voxmail.VoxmailException;
import org.voxmail.model.Mailbox;
import org.voxmail.utils.WebUtil;


public class ProvisionAction extends Action {
    
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
        
        String contactId = request.getParameter("contactId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        
        String cmd = request.getParameter("cmd");
        
        System.out.println(df.format(new Date())+": Provision contactId is "+contactId);
        
        if (!WebUtil.isEmpty(contactId)) {
            Mailbox mailbox = null;
            try {
                List list = Voxmail.getInstance().getVoxmailService().retrieveMailboxByPhoneNumber(phoneNumber);
                if (list.size()  > 1)
                {
                    System.out.println("***Exception provisioning mailbox, phoneNumber: " + phoneNumber + " has multiple" +
                            "entries in the database.  It must be unique. Please correct this problem before provisioning this number");
                    
                    throw new VoxmailException("Exception provisioning mailbox, phoneNumber: " + phoneNumber + " has multiple" +
                            "entries in the database.  It must be unique. Please correct this problem before provisioning this number");
                                
                }
                else if (list.size() == 1)
                {
                    mailbox = (Mailbox) list.get(0);
                    if (!mailbox.getContactId().equals(contactId))
                    {
                        //check first and last name, if the same, let go through as the same contact (may have been a delete/add)
                        if (mailbox.getFirstName().equals(firstName) && mailbox.getLastName().equals(lastName))
                        {
                            //ok, let it go.
                        }
                        else
                        {
                            System.out.println("***Exception provisioning mailbox, a different mailbox with this work phone number already " +
                                    "exists.  Please use a different work phone");

                            throw new VoxmailException("Exception provisioning mailbox, a different mailbox with this work phone number already " +
                                    "exists.  Please use a different work phone");
                        }
                    }
                }
                if (mailbox != null)
                {
                    System.out.println("ProvisionAction mailbox already exists for contactId: " + contactId);
                    mailbox.setEmail(email);
                    mailbox.setFirstName(firstName);
                    mailbox.setLastName(lastName);
                    mailbox.setPhoneNumber(phoneNumber);
                    
                    if (mailbox.getImapPassword() == null)
                    {
                        mailbox.setImapPassword("");
                    }
                    
                    if (mailbox.getImapUsername() == null)
                    {
                        mailbox.setImapUsername("");
                    }
                    
                    Voxmail.getInstance().getVoxmailService().updateMailbox(mailbox);
                }
                else
                {
                    System.out.println("ProvisionAction creating mailbox for contactId: " + contactId);
                    String mailRoot = request.getRealPath("/mailroot");
                    Voxmail.getInstance().getVoxmailService().createMailbox(contactId,firstName,lastName,phoneNumber,email,mailRoot);
                
                }
                
            } catch (VoxmailException e) {
                e.printStackTrace();
                return mapping.findForward("error");
            }
            
            return mapping.findForward("provision");

        } 
        else
        {
            return mapping.findForward("error");
        }
       
    }
    
}

