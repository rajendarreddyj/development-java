package com.rajendarreddyj.spring.enums;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public enum ExceptionDetail {
    GENERIC_APPLICATION_ERROR("exception.generic.application_error", "Generic Application Error"),
    GENERIC_NOT_FOUND_EXCEPTION("exception.generic.not_found", "Generic Not Found"),
    FILE_NOT_FOUND_EXCEPTION("exception.file.not_found", "File Not Found"),
    GENERIC_UNAUTHORIZED("exception.generic.unauthorized", "Generic Unauthorized"),
    GENERIC_FORBIDDEN("exception.generic.forbidden", "Generic Forbidden");

    private String key;

    private String description;

    private ExceptionDetail(final String key, final String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
