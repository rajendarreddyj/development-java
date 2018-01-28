package com.rajendarreddyj.spring.json.response;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajendarreddyj.spring.enums.ExceptionDetail;
import com.rajendarreddyj.spring.exception.RestApplicationException;
import com.rajendarreddyj.spring.json.BaseResponseJson;
import com.rajendarreddyj.spring.json.HeaderJson;
import com.rajendarreddyj.spring.json.MessageInfo;
import com.rajendarreddyj.spring.util.ApplicationUtil;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class GenericResponseBuilder {
    /**
     * Method to build a successful response with multiple data values. Key is coming in the data map Value
     * 
     * @param data
     * @return Successful
     */
    protected final GenericResponse buildSuccessfulResponse(final BaseResponseJson data) {
        return buildResponse(data, true);
    }

    /**
     * @param data
     * @param messages
     * @return
     */
    protected final GenericResponse buildSuccessfulResponse(final BaseResponseJson data, final List<MessageInfo> messages) {
        GenericResponse genericResponse = this.buildSuccessfulResponse(data);
        genericResponse.getHeader().setMessages(messages);
        return genericResponse;
    }

    /**
     * @param data
     * @param message
     * @return
     */
    protected final GenericResponse buildSuccessfulResponse(final BaseResponseJson data, final MessageInfo message) {
        GenericResponse genericResponse = this.buildSuccessfulResponse(data);
        genericResponse.getHeader().addMessage(message);
        return genericResponse;
    }

    /**
     * @param exception
     * @return
     */
    protected final GenericResponse buildUnsuccessfulResponse(final RestApplicationException exception) {
        GenericResponse genericResponse = buildResponse(null, false);
        genericResponse.getHeader()
                .addMessage(new MessageInfo(exception.getMessage(), exception.getExceptionDetail().getKey(), exception.getExceptionDetail().getDescription()));
        return genericResponse;
    }

    /**
     * Use Below When there is exception while retrieving data when generic response is used as attribute in model
     * 
     * @param exception
     * @param data
     * @return
     */
    protected final GenericResponse buildUnsuccessfulResponse(final RestApplicationException exception, final BaseResponseJson data) {
        GenericResponse genericResponse = buildResponse(data, true);
        genericResponse.getHeader()
                .addMessage(new MessageInfo(exception.getMessage(), exception.getExceptionDetail().getKey(), exception.getExceptionDetail().getDescription()));
        return genericResponse;
    }

    /**
     * @param exception
     * @return
     */
    protected final GenericResponse buildUnsuccessfulResponse(final Exception exception) {
        GenericResponse genericResponse = buildResponse(null, false);
        genericResponse.getHeader().addMessage(new MessageInfo(exception.getMessage(), ExceptionDetail.GENERIC_APPLICATION_ERROR.getKey(),
                ExceptionDetail.GENERIC_APPLICATION_ERROR.getDescription()));
        return genericResponse;
    }

    /**
     * @param messages
     * @return
     */
    protected final GenericResponse buildUnsuccessfulResponse(final List<MessageInfo> messages) {
        GenericResponse genericResponse = buildResponse(null, false);
        genericResponse.getHeader().setMessages(messages);
        return genericResponse;
    }

    /**
     * 
     * @param data
     * @param messages
     * @return
     */
    protected final GenericResponse buildUnsuccessfulResponse(final BaseResponseJson data, final List<MessageInfo> messages) {
        GenericResponse genericResponse = buildResponse(data, false);
        genericResponse.getHeader().setMessages(messages);
        return genericResponse;
    }

    /**
     * @param message
     * @return
     */
    protected final GenericResponse buildUnsuccessfulResponse(final MessageInfo message) {
        GenericResponse genericResponse = buildResponse(null, false);
        genericResponse.getHeader().addMessage(message);
        return genericResponse;
    }

    /**
     * @param redirectURL
     * @param referralURL
     * @return
     */
    protected final GenericResponse buildRedirectResponse(final String redirectURL, final String referralURL) {
        GenericResponse genericResponse = buildResponse(null, true);
        genericResponse.getHeader().setRedirectURL(redirectURL);
        genericResponse.getHeader().setReferralURL(referralURL);
        return genericResponse;
    }

    /**
     * @param data
     * @param responseType
     * @return
     */
    private final static GenericResponse buildResponse(final BaseResponseJson data, final boolean responseType) {
        GenericResponse genericResponse = new GenericResponse();
        HeaderJson header = ApplicationUtil.createHeader();
        header.setSuccess(responseType);
        header.setRedirectURL("");
        header.setReferralURL("");
        genericResponse.setHeader(header);
        genericResponse.setData(data);
        printJSON(genericResponse);
        return genericResponse;
    }

    public static void printJSON(final Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        try {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            System.out.println("JSON Conversion failed " + ExceptionUtils.getStackTrace(e));
        }
    }
}
