package com.rajendarreddyj.spring.service;

import com.rajendarreddyj.spring.exception.RestApplicationException;
import com.rajendarreddyj.spring.json.request.FileUploadRequest;
import com.rajendarreddyj.spring.json.request.FileUploadResponse;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public interface FileUploadService {

    /**
     * @param request
     * @return
     */
    public FileUploadResponse readData(FileUploadRequest request) throws RestApplicationException;

}
