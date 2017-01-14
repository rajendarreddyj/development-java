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

import java.io.Serializable;

public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;
	private int contactID = 0;
    private String firstName = null;
    private String lastName = null;
    private String phoneNumber = null;
    private String email = null;
    private String mailURL = null;
    private String pin = null;
    
    public Contact() {}
    
    public Contact(int contactID, String firstName, String lastName, 
                   String phoneNumber, String email, String mailURL, String pin)
    {
        this.setContactID(contactID);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
        this.setMailURL(mailURL);
        this.setPin(pin);
    }
    
    public int getContactID() {
        return contactID;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getMailURL() {
        return mailURL;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMailURL(String mailURL) {
        this.mailURL = mailURL;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    
    
}



