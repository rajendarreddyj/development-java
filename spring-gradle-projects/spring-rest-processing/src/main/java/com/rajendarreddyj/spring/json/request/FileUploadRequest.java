package com.rajendarreddyj.spring.json.request;

import com.rajendarreddyj.spring.json.BaseJson;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class FileUploadRequest implements BaseJson {

    private static final long serialVersionUID = 1L;

    private String filePath;

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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FileUploadResponse [filePath=" + this.filePath + "]";
    }

}
