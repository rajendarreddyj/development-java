package com.rajendarreddyj.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rajendarreddyj.spring.exception.RestApplicationException;
import com.rajendarreddyj.spring.json.request.FileUploadRequest;
import com.rajendarreddyj.spring.json.request.FileUploadResponse;
import com.rajendarreddyj.spring.json.response.GenericResponse;
import com.rajendarreddyj.spring.service.FileUploadService;

/**
 * @author rajendarreddy.jagapathi
 *
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController extends BaseController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/processFile")
    public @ResponseBody GenericResponse processFile(@RequestBody final FileUploadRequest request) {
        FileUploadResponse fileUploadResponse = new FileUploadResponse();
        try {
            fileUploadResponse = this.fileUploadService.readData(request);
        } catch (RestApplicationException e) {
            return this.buildUnsuccessfulResponse(e, fileUploadResponse);
        }
        return this.buildSuccessfulResponse(fileUploadResponse);
    }
}
