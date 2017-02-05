package com.rajendarreddyj.basics.encoderdecoder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rajendarreddy
 *
 */
public class EncoderDecoderTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncoderDecoderTest.class);
    private static final String testUrl = "http://www.example.com?key1=value+1&key2=value%40%21%242&key3=value%253";
    private static final String testUrlWithPath = "http://www.example.com/path+1?key1=value+1&key2=value%40%21%242&key3=value%253";

    private String encodeValue(final String value) {
        String encoded = null;
        try {
            encoded = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Error encoding parameter {}", e.getMessage(), e);
        }
        return encoded;
    }

    private String decode(final String value) {
        String decoded = null;
        try {
            decoded = URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Error encoding parameter {}", e.getMessage(), e);
        }
        return decoded;
    }

    @Test
    public void givenURL_whenAnalyze_thenCorrect() throws Exception {
        URL url = new URL(testUrl);
        Assert.assertThat(url.getProtocol(), CoreMatchers.is("http"));
        Assert.assertThat(url.getHost(), CoreMatchers.is("www.example.com"));
        Assert.assertThat(url.getQuery(), CoreMatchers.is("key1=value+1&key2=value%40%21%242&key3=value%253"));
    }

    @Test
    public void givenRequestParam_whenUTF8Scheme_thenEncode() throws Exception {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("key1", "value 1");
        requestParams.put("key2", "value@!$2");
        requestParams.put("key3", "value%3");
        String encodedURL = requestParams.keySet().stream().map(key -> key + "=" + this.encodeValue(requestParams.get(key)))
                .collect(Collectors.joining("&", "http://www.example.com?", ""));
        Assert.assertThat(testUrl, CoreMatchers.is(encodedURL));
    }

    @Test
    public void givenRequestParam_whenUTF8Scheme_thenDecodeRequestParams() throws Exception {
        URL url = new URL(testUrl);
        String query = url.getQuery();
        String decodedQuery = Arrays.stream(query.split("&")).map(param -> param.split("=")[0] + "=" + this.decode(param.split("=")[1]))
                .collect(Collectors.joining("&"));
        Assert.assertEquals("http://www.example.com?key1=value 1&key2=value@!$2&key3=value%3", url.getProtocol() + "://" + url.getHost() + "?" + decodedQuery);
    }

    private String encodePath(String path) {
        try {
            path = new URI(null, null, path, null).getPath();
        } catch (URISyntaxException e) {
            LOGGER.error("Error encoding parameter {}", e.getMessage(), e);
        }
        return path;
    }

    @Test
    public void givenPath_thenEncodeDecodePath() throws URISyntaxException {
        URI uri = new URI(null, null, "/Path 1/Path+2", null);
        Assert.assertEquals("/Path 1/Path+2", uri.getPath());
        Assert.assertEquals("/Path%201/Path+2", uri.getRawPath());
    }

    @Test
    public void givenPathAndRequestParam_whenUTF8Scheme_thenEncode() throws Exception {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("key1", "value 1");
        requestParams.put("key2", "value@!$2");
        requestParams.put("key3", "value%3");
        String path = "path+1";
        String encodedURL = requestParams.keySet().stream().map(key -> key + "=" + this.encodeValue(requestParams.get(key)))
                .collect(Collectors.joining("&", "http://www.example.com/" + this.encodePath(path) + "?", ""));
        Assert.assertThat(testUrlWithPath, CoreMatchers.is(encodedURL));
    }
}
