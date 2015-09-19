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

package org.voxmail.service;

import java.util.Iterator;
import java.util.List;
import org.voxmail.Voxmail;
import org.voxmail.VoxmailException;
import org.voxmail.model.Mailbox;

public class VoxmailService {

        public Mailbox updateOptions(String pin, String imapUsername, String imapPassword, String mailboxId) throws VoxmailException
        {
            Mailbox m = Voxmail.getInstance().getVoxmailDAO().getMailboxByMailboxId(mailboxId);
            if (m!= null)
            {
                m.setPin(pin);
                m.setImapUsername(imapUsername);
                m.setImapPassword(imapPassword);
                Voxmail.getInstance().getVoxmailDAO().updateMailbox(m);
            }
            return m;        
        }
        
        public void updateMailbox(Mailbox mailbox) throws VoxmailException
        {
            Voxmail.getInstance().getVoxmailDAO().updateMailbox(mailbox);
        }
        
        public Mailbox createMailbox(String contactId, String firstName, String lastName, String phoneNumber, String email, String mailRoot) throws VoxmailException
        {
            Mailbox m = new Mailbox();
            m.setContactId(contactId);
            m.setFirstName(firstName);
            m.setLastName(lastName);
            m.setPhoneNumber(phoneNumber);
            m.setPin("1234");
            m.setEmail(email);
            m.setEntityName("");
            m.setRecordedGreeting("");
            m.setRecordedName("");
            m.setFirstTime(true);
            m.setGreetingType(Mailbox.GREETING_TYPE_DEFAULT);
            m.setMailUrl("maildir:" + mailRoot + "\\" + contactId + "_" + firstName + "_" + lastName);
            m.setImapUsername("");
            m.setImapPassword("");
            Voxmail.getInstance().getVoxmailDAO().createMailbox(m);
            
            return m;        
        }
        
        public void updateGreeting(Mailbox mailbox) throws VoxmailException
        {
            String mailboxId = mailbox.getMailboxId();
            Mailbox m = Voxmail.getInstance().getVoxmailDAO().getMailboxByMailboxId(mailboxId);
            if (m!= null)
            {
                m.setGreetingType(mailbox.getGreetingType());
                m.setRecordedName(mailbox.getRecordedName());
                m.setRecordedGreeting(mailbox.getRecordedGreeting());
                
                //Set first time to false so we don't go through this again.
                m.setFirstTime(false);
                
                Voxmail.getInstance().getVoxmailDAO().updateMailbox(m);
            }
            
        }
        
        public Mailbox retrieveMailbox(String mailboxId) throws VoxmailException
        {
            Mailbox m = Voxmail.getInstance().getVoxmailDAO().getMailboxByMailboxId(mailboxId);
            
            return m;        
        }
        
        public Mailbox retrieveMailboxByContactId(String contactId) throws VoxmailException
        {
            Mailbox m = Voxmail.getInstance().getVoxmailDAO().getMailboxByContactId(contactId);
            
            return m;        
        }
        
        public List retrieveMailboxByPhoneNumber(String phone) throws VoxmailException
        {
            List list = Voxmail.getInstance().getVoxmailDAO().getMailboxByPhoneNumber(phone);
            
            return list;        
        }
        
        public Mailbox login(String phoneNumber, String pin) throws VoxmailException
        {
            List mailboxes = Voxmail.getInstance().getVoxmailDAO().getMailboxByPhoneNumber(phoneNumber);
            if (mailboxes == null || mailboxes.size() == 0)
            {
                return null;
            }
            Iterator it = mailboxes.iterator();
            while (it.hasNext())
            {
                Mailbox mailbox = (Mailbox) it.next();
                if (mailbox.getPin().equals(pin))
                {
                    return mailbox;
                }
            }
            return null;        
        }
    

}
