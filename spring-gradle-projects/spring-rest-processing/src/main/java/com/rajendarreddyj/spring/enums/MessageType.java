package com.rajendarreddyj.spring.enums;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public enum MessageType {
    ERROR, WARNING, INFO, NONE;

    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }
}
