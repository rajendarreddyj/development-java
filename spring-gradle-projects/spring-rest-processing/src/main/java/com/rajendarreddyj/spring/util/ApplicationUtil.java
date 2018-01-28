package com.rajendarreddyj.spring.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import com.rajendarreddyj.spring.json.HeaderJson;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class ApplicationUtil {
    private ApplicationUtil() {
        super();
    }

    public static HeaderJson createHeader() {
        SimpleDateFormat FORMATTER = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");
        HeaderJson header = new HeaderJson();
        header.setSuccess(true);
        header.setDate(FORMATTER.format(Calendar.getInstance().getTime()));
        header.setUniqueId(UUID.randomUUID().toString());
        return header;
    }
}
