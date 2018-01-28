package com.rajendarreddyj.spring.json;

/**
 * @author rajendarreddy.jagapathi
 *
 */
import java.util.ArrayList;
import java.util.List;

public class HeaderJson implements BaseJson {
    private static final long serialVersionUID = 1L;
    private String uniqueId;
    private boolean success;
    private List<MessageInfo> messages = new ArrayList<>();
    private String date;
    private String redirectURL;
    private String referralURL;

    /**
     * @return the uniqueId
     */
    public String getUniqueId() {
        return this.uniqueId;
    }

    /**
     * @param uniqueId
     *            the uniqueId to set
     */
    public void setUniqueId(final String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return this.success;
    }

    /**
     * @param success
     *            the success to set
     */
    public void setSuccess(final boolean success) {
        this.success = success;
    }

    /**
     * @return the messages
     */
    public List<MessageInfo> getMessages() {
        return this.messages;
    }

    /**
     * @param messages
     *            the messages to set
     */
    public void setMessages(final List<MessageInfo> messages) {
        this.messages = messages;
    }

    /**
     * @param message
     */
    public void addMessage(final MessageInfo message) {
        this.messages.add(message);
    }

    /**
     * @return the date
     */
    public String getDate() {
        return this.date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(final String date) {
        this.date = date;
    }

    /**
     * @return the redirectURL
     */
    public String getRedirectURL() {
        return this.redirectURL;
    }

    /**
     * @param redirectURL
     *            the redirectURL to set
     */
    public void setRedirectURL(final String redirectURL) {
        this.redirectURL = redirectURL;
    }

    /**
     * @return the referralURL
     */
    public String getReferralURL() {
        return this.referralURL;
    }

    /**
     * @param referralURL
     *            the referralURL to set
     */
    public void setReferralURL(final String referralURL) {
        this.referralURL = referralURL;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HeaderJson [uniqueId=" + this.uniqueId + ", success=" + this.success + ", messages=" + this.messages + ", date=" + this.date + ", redirectURL="
                + this.redirectURL + ", referralURL=" + this.referralURL + "]";
    }

}
