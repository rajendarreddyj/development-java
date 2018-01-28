package com.rajendarreddyj.spring.json.request;

import java.util.ArrayList;
import java.util.List;

import com.rajendarreddyj.spring.bean.Person;
import com.rajendarreddyj.spring.json.BaseResponseJson;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class FileUploadResponse extends BaseResponseJson {

    private static final long serialVersionUID = 1L;

    private String filePath;
    private List<Person> successfulList = new ArrayList<>();
    private List<Person> failureList = new ArrayList<>();

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * @param filePath
     *            the filePath to set
     */
    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the successfulList
     */
    public List<Person> getSuccessfulList() {
        return this.successfulList;
    }

    /**
     * @param successfulList
     *            the successfulList to set
     */
    public void setSuccessfulList(final List<Person> successfulList) {
        this.successfulList = successfulList;
    }

    /**
     * @return the failureList
     */
    public List<Person> getFailureList() {
        return this.failureList;
    }

    /**
     * @param failureList
     *            the failureList to set
     */
    public void setFailureList(final List<Person> failureList) {
        this.failureList = failureList;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FileUploadResponse [filePath=" + this.filePath + ", successfulList=" + this.successfulList + ", failureList=" + this.failureList + "]";
    }

}
