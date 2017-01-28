/*
 * The contents of this file are subject to the Mozilla Public License Version 1.1 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for the specific language governing rights and limitations under the License. The
 * Original Code is vox-mail. The Initial Developer of the Original Code is Voxeo Corporation. Portions created by Voxeo
 * are Copyright (C) 2000-2007. All rights reserved. Contributor(s): ICOA Inc. <info@icoa.com> (http://icoa.com)
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
        return this.mailboxId;
    }

    public void setMailboxId(final String mailboxId) {
        this.mailboxId = mailboxId;
    }

    public String getContactId() {
        return this.contactId;
    }

    public void setContactId(final String contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public void setEntityName(final String entityName) {
        this.entityName = entityName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPin() {
        return this.pin;
    }

    public void setPin(final String pin) {
        this.pin = pin;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getMailUrl() {
        return this.mailUrl;
    }

    public void setMailUrl(final String mailUrl) {
        this.mailUrl = mailUrl;
    }

    public boolean isFirstTime() {
        return this.firstTime;
    }

    public void setFirstTime(final boolean firstTime) {
        this.firstTime = firstTime;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public int getGreetingType() {
        return this.greetingType;
    }

    public void setGreetingType(final int greetingType) {
        this.greetingType = greetingType;
    }

    public int getMailboxType() {
        return this.mailboxType;
    }

    public void setMailboxType(final int mailboxType) {
        this.mailboxType = mailboxType;
    }

    public String getRecordedName() {
        return this.recordedName;
    }

    public String getRecordedName(final String greetingPath) {
        String fileLocation = "";
        try {
            fileLocation = greetingPath + "/" + this.getRecordedName();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileLocation;
    }

    public void setRecordedName(final String recordedName) {
        this.recordedName = recordedName;
    }

    public String getRecordedGreeting() {
        return this.recordedGreeting;
    }

    public String getRecordedGreeting(final String greetingPath) {
        String fileLocation = "";
        try {
            fileLocation = greetingPath + "/" + this.getRecordedGreeting();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileLocation;
    }

    public void setRecordedGreeting(final String recordedGreeting) {
        this.recordedGreeting = recordedGreeting;
    }

    public String getImapUsername() {
        return this.imapUsername;
    }

    public void setImapUsername(final String imapUsername) {
        this.imapUsername = imapUsername;
    }

    public String getImapPassword() {
        return this.imapPassword;
    }

    public void setImapPassword(final String imapPassword) {
        this.imapPassword = imapPassword;
    }

}
