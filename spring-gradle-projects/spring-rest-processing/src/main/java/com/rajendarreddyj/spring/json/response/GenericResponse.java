package com.rajendarreddyj.spring.json.response;

import java.io.Serializable;

import com.rajendarreddyj.spring.json.BaseResponseJson;
import com.rajendarreddyj.spring.json.HeaderJson;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class GenericResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private HeaderJson header;

    private BaseResponseJson data;

    protected GenericResponse() {
        super();
    }

    /**
     * @return the header
     */
    public HeaderJson getHeader() {
        return this.header;
    }

    /**
     * @param header
     *            the header to set
     */
    protected void setHeader(final HeaderJson header) {
        this.header = header;
    }

    /**
     * @param data
     *            the data to set
     */
    protected void setData(final BaseResponseJson data) {
        this.data = data;
    }

    /**
     * @return
     */
    public BaseResponseJson getData() {
        return this.data;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "GenericResponse [header=" + this.header + ", data=" + this.data + "]";
    }

}