package com.rajendarreddyj.spring.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// to test csrf
@Controller
@RequestMapping(value = "/")
public class ImageController {
    //private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    /*public ResponseEntity<byte[]> getImageAsResponseEntity() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        InputStream in = ImageController.class.getResourceAsStream("/apps/sourceCode/git/rajendarreddyj/development-java/spring-projects/spring-rest-security/src/main/webapp/images/image.jpg");
        byte[] media = ByteStreams.toByteArray(in);

        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
         
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
    }*/
  /*  public ResponseEntity<byte[]> showImages () throws IOException {
        String boundary="---------THIS_IS_THE_BOUNDARY";
        List<String> imageNames = Arrays.asList(new String[]{"image.jpg","chinna.jpg"});
        List<String> contentTypes = Arrays.asList(new String[]{MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_JPEG_VALUE});
        List<Byte[]> imagesData = new ArrayList<Byte[]>();
        imagesData.add(ArrayUtils.toObject(IOUtils.toByteArray(getClass().getResourceAsStream("/images/image.jpg"))));
        imagesData.add(ArrayUtils.toObject(IOUtils.toByteArray(getClass().getResourceAsStream("/images/chinna.jpg"))));
        byte[] allImages = getMultipleImageResponse(boundary, imageNames,contentTypes, imagesData);
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","multipart/x-mixed-replace; boundary=" + boundary);
        return new ResponseEntity<byte[]>(allImages,headers,HttpStatus.CREATED);
    }*/

    public ResponseEntity<byte[]> getAllImages() throws IOException {
        List<String> imageNames = Arrays.asList(new String[]{"image.jpg","chinna.jpg"});
        List<String> contentTypes = Arrays.asList(new String[]{MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_JPEG_VALUE});
        List<Byte[]> imagesData = new ArrayList<Byte[]>();
        imagesData.add(ArrayUtils.toObject(IOUtils.toByteArray(getClass().getResourceAsStream("/images/image.jpg"))));
        imagesData.add(ArrayUtils.toObject(IOUtils.toByteArray(getClass().getResourceAsStream("/images/chinna.jpg"))));
        byte[] htmlData=getHtmlData(imageNames,contentTypes, imagesData);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        return new ResponseEntity<byte[]>(htmlData,headers,HttpStatus.OK);
    }

    private static byte[] getHtmlData(List<String> imageNames, List<String> contentTypes, List<Byte[]> imagesData){
        String htmlContent="<!DOCTYPE html><html><head><title>Images</title></head><body>";
         Integer imagesCounter = -1;
        for(String imageName : imageNames){
             imagesCounter++;
            htmlContent = htmlContent + "<br/><br/><b>" + imageName + "</b><br/></br/><img src='data:" + contentTypes.get(imagesCounter) + ";base64, " + org.apache.commons.codec.binary.StringUtils.newStringUtf8(Base64
                    .encodeBase64(ArrayUtils.toPrimitive(imagesData.get(imagesCounter)))) + "'/>";
        }
        htmlContent = htmlContent + "</body></html>";
        return htmlContent.getBytes();
    }
}
