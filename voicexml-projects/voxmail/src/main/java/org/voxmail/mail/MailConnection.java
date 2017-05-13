/*
 * The contents of this file are subject to the Mozilla Public License Version 1.1 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for the specific language governing rights and limitations under the License. The
 * Original Code is vox-mail. The Initial Developer of the Original Code is Voxeo Corporation. Portions created by Voxeo
 * are Copyright (C) 2000-2007. All rights reserved. Contributor(s): ICOA Inc. <info@icoa.com> (http://icoa.com)
 */

package org.voxmail.mail;

// for logging
import java.io.Serializable;
import java.util.logging.Logger;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.servlet.http.HttpSessionBindingListener;

import org.voxmail.Voxmail;
import org.voxmail.model.Mailbox;

public class MailConnection implements HttpSessionBindingListener, Serializable {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final long serialVersionUID = 1L;

    static final String fileProviderName = "maildir:";

    static private Session session = Session.getDefaultInstance(System.getProperties(), null);
    protected Store store;
    protected Folder inbox;

    /** Creates a new instance of MailConnection */
    public MailConnection(final Mailbox mailbox) throws Exception {

        // if local storage, check to make sure directories exist for the user
        String mailurl = mailbox.getMailUrl();
        if ((mailurl != null) && mailurl.startsWith(fileProviderName)) {
            try {
                new org.voxmail.utils.FileStoreHelper().verifyMailboxExists(mailurl.substring(fileProviderName.length()));
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }

        try {
            String useImap = Voxmail.getProps().getProperty("useImap");
            URLName urlName = null;
            if ((useImap != null) && useImap.toLowerCase().equals("true")) {
                logger.info("MailConnection using imap to getStore for mailbox: " + mailbox.getMailboxId());
                urlName = this.getImapUrl(mailbox);
                logger.info("Imap URL: " + urlName.toString() + ", host: " + urlName.getHost() + ", username: " + urlName.getUsername());
            } else {
                logger.info("MailConnection using file store to getStore for mailurl: " + mailurl);
                urlName = new URLName(mailurl);
            }

            this.store = session.getStore(urlName);
            this.store.connect();
            this.inbox = this.store.getFolder("INBOX");
            this.inbox.open(Folder.READ_WRITE);

        } catch (Exception e) {
            // logger.error("MailConnection() - failed: " + e.getMessage());
            throw new Exception("Mail connection failed with error: " + e.getMessage());
        }
    }

    public URLName getImapUrl(final Mailbox mailbox) throws Exception {

        String imapHost = Voxmail.getProps().getProperty("imapHost");
        URLName url = new URLName("imap", imapHost, -1, "INBOX", mailbox.getImapUsername(), mailbox.getImapPassword());

        return url;
    }

    public Folder getInbox() {
        return this.inbox;
    }

    public void closeInbox() {
        try {
            this.inbox.close(true);
            this.store.close();
        } catch (Exception e) {
            // nothing
        }
    }

    @Override
    public void valueBound(final javax.servlet.http.HttpSessionBindingEvent httpSessionBindingEvent) {
    }

    @Override
    public void valueUnbound(final javax.servlet.http.HttpSessionBindingEvent httpSessionBindingEvent) {
        this.closeInbox();
    }
}
