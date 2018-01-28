package com.rajendarreddyj.spring.json;

import java.io.Serializable;

/**
 * @author rajendarreddy.jagapathi
 *
 */
import com.rajendarreddyj.spring.enums.MessageType;

public class MessageInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;

    private String key;

    private String description;

    private MessageType type;

    public MessageInfo() {
    }

    public MessageInfo(final String message, final String key, final String description, final MessageType type) {
        super();
        this.message = message;
        this.key = key;
        this.description = description;
        this.type = type;
    }

    public MessageInfo(final String message, final String key, final String description) {
        this(message, key, description, MessageType.ERROR);
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return the type
     */
    public MessageType getType() {
        return this.type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(final MessageType type) {
        this.type = type;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MessageInfo [message=" + this.message + ", key=" + this.key + ", description=" + this.description + ", type=" + this.type + "]";
    }

}
