package com.rajendarreddyj.spring.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.stereotype.Service;

import com.rajendarreddyj.spring.bean.Person;
import com.rajendarreddyj.spring.enums.ExceptionDetail;
import com.rajendarreddyj.spring.exception.RestApplicationException;
import com.rajendarreddyj.spring.json.request.FileUploadRequest;
import com.rajendarreddyj.spring.json.request.FileUploadResponse;

/**
 * @author rajendarreddy.jagapathi
 *
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    /* (non-Javadoc)
     * @see com.rajendarreddyj.spring.service.FileUploadService#readData(com.rajendarreddyj.spring.json.request.FileUploadRequest)
     */
    @Override
    public FileUploadResponse readData(final FileUploadRequest request) throws RestApplicationException {
        if ((request == null) || (request.getFilePath() == null)) {
            throw new RestApplicationException(ExceptionDetail.FILE_NOT_FOUND_EXCEPTION);
        }
        File f = new File(request.getFilePath());
        if (f.exists() && f.isDirectory()) {
            throw new RestApplicationException(ExceptionDetail.FILE_NOT_FOUND_EXCEPTION);
        }
        List<Person> personList;
        try {
            personList = this.csvPersonReader(f);
        } catch (IOException e) {
            throw new RestApplicationException(ExceptionDetail.FILE_NOT_FOUND_EXCEPTION);
        }
        FileUploadResponse fileUploadResponse = new FileUploadResponse();
        fileUploadResponse.setFilePath(request.getFilePath());
        List<Person> failedList = personList.stream().filter(p -> "Jill".equals(p.getFirstName())).collect(Collectors.toList());
        personList.removeAll(failedList);
        fileUploadResponse.setFailureList(failedList);
        fileUploadResponse.setSuccessfulList(personList);
        return fileUploadResponse;
    }

    private List<Person> csvPersonReader(final File f) throws IOException {
        List<Person> personList = new ArrayList<>();
        LineIterator it = FileUtils.lineIterator(f, "UTF-8");
        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                String[] parts = line.split(",");
                personList.add(new Person(parts[0], parts[1]));
            }
        } finally {
            it.close();
        }
        return personList;
    }
}
