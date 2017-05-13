/*
 * The contents of this file are subject to the Mozilla Public License Version 1.1 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for the specific language governing rights and limitations under the License. The
 * Original Code is vox-mail. The Initial Developer of the Original Code is Voxeo Corporation. Portions created by Voxeo
 * are Copyright (C) 2000-2007. All rights reserved. Contributor(s): ICOA Inc. <info@icoa.com> (http://icoa.com)
 */

/*
 * Messages.java Created on January 17, 2007, 8:59 AM
 */

package org.voxmail.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * @author James
 */
public class Messages {

    /** Creates a new instance of Messages */
    public Messages() {
    }

    private final ArrayList<MessageWrapper> messages = new ArrayList<>();

    public void addMessage(final MessageWrapper message) {
        this.messages.add(message);
    }

    public int getMessageCount() {
        return this.messages.size();
    }

    public ArrayList<MessageWrapper> getNewMessages() {
        return this.getMessagesByStatus(MessageWrapper.MESSAGE_STATUS_NEW);
    }

    public ArrayList<MessageWrapper> getSavedMessages() {
        return this.getMessagesByStatus(MessageWrapper.MESSAGE_STATUS_SAVED);
    }

    public ArrayList<MessageWrapper> getDeletedMessages() {
        return this.getMessagesByStatus(MessageWrapper.MESSAGE_STATUS_DELETED);
    }

    public ArrayList<MessageWrapper> getSkippedMessages() {
        return this.getMessagesByStatus(MessageWrapper.MESSAGE_STATUS_SKIPPED);
    }

    private ArrayList<MessageWrapper> getMessagesByStatus(final int status) {
        ArrayList<MessageWrapper> list = new ArrayList<>();
        Iterator<MessageWrapper> it = this.messages.iterator();
        while (it.hasNext()) {
            MessageWrapper mw = it.next();
            if (mw.getStatus() == status) {
                list.add(mw);
            }
        }
        // Sort the list by received date descending
        Collections.sort(list);
        // Collections.reverse(list);

        // logger.info("\nAfter Sorting :");

        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }

        return list;
    }

}
