package com.rajendarreddyj.spring.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author rajendarreddy.jagapathi
 *
 */
@Configuration
public class DBProperties {

    @Value("${db.username}")
    private String userName;

    @Value("${db.password}")
    private String password;

    @Value("${db.url}")
    private String url;
    // getters for instance fields

    /**
     * @return the userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DBProperties [userName=" + this.userName + ", password=" + this.password + ", url=" + this.url + "]";
    }

}
