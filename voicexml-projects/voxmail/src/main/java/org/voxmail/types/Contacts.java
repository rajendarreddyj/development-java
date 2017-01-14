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

package org.voxmail.types;

import java.util.ArrayList;
import java.util.Iterator;


public class Contacts {
    
    private ArrayList contacts;

    public Contacts() {
        contacts = new ArrayList();
    }
    
    public ArrayList getContacts() {
        return contacts;
    }
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void updateContact(Contact contact) {
        removeContact(contact.getContactID());
        contacts.add(contact);
    }
    
    public void removeContact(int contactID)
    {
        Iterator it = contacts.iterator();
        while (it.hasNext())
        {
            Contact contact = (Contact) it.next();
            if (contact.getContactID() == contactID)
            {
                it.remove();
            }
        }
    }
    
    public Contact getContact(int contactID)
    {
        Iterator it = contacts.iterator();
        while (it.hasNext())
        {
            Contact contact = (Contact) it.next();
            if (contact.getContactID() == contactID)
            {
                return contact;
            }
        }
        return null;
    }
    
    public Contact getContact(String phoneNumber, String pin)
    {
        Iterator it = contacts.iterator();
        
        while (it.hasNext())
        {
            Contact contact = (Contact) it.next();
            if (contact.getPhoneNumber() == null || contact.getPin() == null)
            {
                return null;
            }
            if (contact.getPhoneNumber().equals(phoneNumber) && contact.getPin().equals(pin))
            {
                return contact;
            }
        }
        return null;
    }
    
    
}
