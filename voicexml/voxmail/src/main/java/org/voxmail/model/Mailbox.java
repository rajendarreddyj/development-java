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

package org.voxmail.model;

/**
 * @author shawn
 */
public class Mailbox {
    private String mailboxId;
    private String contactId;
    private String firstName;
    private String lastName;
    private String entityName;
    private String phoneNumber;
    private String pin;
    private String email;
    private String mailUrl;
    private String recordedName;
    private String recordedGreeting;
    private boolean firstTime;
    private boolean active;
    private int greetingType;
    private int mailboxType;
    private String imapUsername;
    private String imapPassword;
    
    public static final int GREETING_TYPE_DEFAULT = 0;
    public static final int GREETING_TYPE_NAME = 1;
    public static final int GREETING_TYPE_GREETING = 2;
    
    public String getMailboxId() {
        return mailboxId;
    }
    
    public void setMailboxId(String mailboxId) {
        this.mailboxId = mailboxId;
    }
    
    public String getContactId() {
        return contactId;
    }
    
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEntityName() {
        return entityName;
    }
    
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getPin() {
        return pin;
    }
    
    public void setPin(String pin) {
        this.pin = pin;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMailUrl() {
        return mailUrl;
    }
    
    public void setMailUrl(String mailUrl) {
        this.mailUrl = mailUrl;
    }
    
    public boolean isFirstTime() {
        return firstTime;
    }
    
    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public int getGreetingType() {
        return greetingType;
    }
    
    public void setGreetingType(int greetingType) {
        this.greetingType = greetingType;
    }
    
    public int getMailboxType() {
        return mailboxType;
    }
    
    public void setMailboxType(int mailboxType) {
        this.mailboxType = mailboxType;
    }
    
    public String getRecordedName() {
        return recordedName;
    }
    public String getRecordedName(String greetingPath)
    {
        String fileLocation="";
        try {
            fileLocation = greetingPath + "/" + this.getRecordedName();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileLocation;
    }
    
    public void setRecordedName(String recordedName) {
        this.recordedName = recordedName;
    }
    
    public String getRecordedGreeting() {
        return recordedGreeting;
    }
    
    public String getRecordedGreeting(String greetingPath)
    {
        String fileLocation="";
        try {
            fileLocation = greetingPath + "/" + this.getRecordedGreeting();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileLocation;
    }
    
    public void setRecordedGreeting(String recordedGreeting) {
        this.recordedGreeting = recordedGreeting;
    }

    public String getImapUsername() {
        return imapUsername;
    }

    public void setImapUsername(String imapUsername) {
        this.imapUsername = imapUsername;
    }

    public String getImapPassword() {
        return imapPassword;
    }

    public void setImapPassword(String imapPassword) {
        this.imapPassword = imapPassword;
    }
    
    
    
    
}
