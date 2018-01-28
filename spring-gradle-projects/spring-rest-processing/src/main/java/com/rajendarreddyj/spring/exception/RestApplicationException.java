package com.rajendarreddyj.spring.exception;

import com.rajendarreddyj.spring.enums.ExceptionDetail;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class RestApplicationException extends Exception {
    private ExceptionDetail exceptionDetail;

    private static final long serialVersionUID = 1L;

    public RestApplicationException(final ExceptionDetail exceptionCode) {
        super(exceptionCode.getDescription());
        this.exceptionDetail = exceptionCode;
    }

    public RestApplicationException(final String message, final Throwable cause, final ExceptionDetail exceptionCode) {
        super(message, cause);
        this.exceptionDetail = exceptionCode;
    }

    public RestApplicationException(final String message, final ExceptionDetail exceptionCode) {
        super(message);
        this.exceptionDetail = exceptionCode;
    }

    public RestApplicationException(final Throwable cause, final ExceptionDetail exceptionCode) {
        super(cause);
        this.exceptionDetail = exceptionCode;
    }

    public ExceptionDetail getExceptionDetail() {
        return this.exceptionDetail;
    }

    public void setExceptionDetail(final ExceptionDetail exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }
}
