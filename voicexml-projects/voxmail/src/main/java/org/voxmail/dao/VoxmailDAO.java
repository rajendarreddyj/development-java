/*
 * The contents of this file are subject to the Mozilla Public License Version 1.1 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for the specific language governing rights and limitations under the License. The
 * Original Code is vox-mail. The Initial Developer of the Original Code is Voxeo Corporation. Portions created by Voxeo
 * are Copyright (C) 2000-2007. All rights reserved. Contributor(s): ICOA Inc. <info@icoa.com> (http://icoa.com)
 */

package org.voxmail.dao;

import java.util.List;

import org.voxmail.VoxmailException;
import org.voxmail.model.Mailbox;

import net.sf.hibernate.HibernateException;

public class VoxmailDAO extends VoxmailSessionManagement {

    public void createMailbox(final Mailbox mailbox) throws VoxmailException {
        try {
            this.getSession().save(mailbox);
        } catch (HibernateException e) {
            throw new VoxmailException("create Mailbox ", e);
        }
    }

    public void updateMailbox(final Mailbox mailbox) throws VoxmailException {
        try {
            this.getSession().save(mailbox);
        } catch (HibernateException e) {
            throw new VoxmailException("updateMailbox Mailbox ", e);
        }
    }

    public Mailbox getMailboxByContactId(final String contactId) throws VoxmailException {
        if ((contactId == null) || contactId.equals("")) {
            throw new VoxmailException("getMailboxByContactId " + contactId);
        }
        try {
            return (Mailbox) this.getSession().createQuery("from org.voxmail.model.Mailbox where CONTACTID = :contactId").setParameter("contactId", contactId)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new VoxmailException("getMailboxByContactId " + contactId, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Mailbox> getMailboxByPhoneNumber(final String phoneNumber) throws VoxmailException {
        if ((phoneNumber == null) || phoneNumber.equals("")) {
            throw new VoxmailException("getMailboxByPhoneNumber " + phoneNumber);
        }
        try {
            return this.getSession().createQuery("from org.voxmail.model.Mailbox where PHONENUMBER = :phoneNumber").setParameter("phoneNumber", phoneNumber)
                    .list();
        } catch (HibernateException e) {
            // e.printStackTrace();
            throw new VoxmailException("getMailboxByPhoneNumber " + phoneNumber, e);
        }
    }

    public Mailbox getMailboxByMailboxId(final String mailboxId) throws VoxmailException {
        if ((mailboxId == null) || mailboxId.equals("")) {
            throw new VoxmailException("getMailboxByMailboxId " + mailboxId);
        }
        try {
            return (Mailbox) this.getSession().createQuery("from org.voxmail.model.Mailbox where MAILBOXID = :mailboxId").setParameter("mailboxId", mailboxId)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new VoxmailException("getMailboxByMailboxId " + mailboxId, e);
        }
    }

}
