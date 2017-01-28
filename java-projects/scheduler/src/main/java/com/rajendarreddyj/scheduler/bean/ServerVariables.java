package com.rajendarreddyj.scheduler.bean;

/**
 * @author rajendarreddy
 */
public class ServerVariables {

    private String httpReferer;
    private String httpUserAgent;
    private String remoteAddress;
    private String remoteHost;
    private String requestMethod;
    private String serverName;
    private String serverPort;
    private String serverSoftware;

    /**
     * @return the httpReferer
     */
    public String getHttpReferer() {
        return this.httpReferer;
    }

    /**
     * @param httpReferer
     *            the httpReferer to set
     */
    public void setHttpReferer(final String httpReferer) {
        this.httpReferer = httpReferer;
    }

    /**
     * @return the httpUserAgent
     */
    public String getHttpUserAgent() {
        return this.httpUserAgent;
    }

    /**
     * @param httpUserAgent
     *            the httpUserAgent to set
     */
    public void setHttpUserAgent(final String httpUserAgent) {
        this.httpUserAgent = httpUserAgent;
    }

    /**
     * @return the remoteAddress
     */
    public String getRemoteAddress() {
        return this.remoteAddress;
    }

    /**
     * @param remoteAddress
     *            the remoteAddress to set
     */
    public void setRemoteAddress(final String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    /**
     * @return the remoteHost
     */
    public String getRemoteHost() {
        return this.remoteHost;
    }

    /**
     * @param remoteHost
     *            the remoteHost to set
     */
    public void setRemoteHost(final String remoteHost) {
        this.remoteHost = remoteHost;
    }

    /**
     * @return the requestMethod
     */
    public String getRequestMethod() {
        return this.requestMethod;
    }

    /**
     * @param requestMethod
     *            the requestMethod to set
     */
    public void setRequestMethod(final String requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * @return the serverName
     */
    public String getServerName() {
        return this.serverName;
    }

    /**
     * @param serverName
     *            the serverName to set
     */
    public void setServerName(final String serverName) {
        this.serverName = serverName;
    }

    /**
     * @return the serverPort
     */
    public String getServerPort() {
        return this.serverPort;
    }

    /**
     * @param serverPort
     *            the serverPort to set
     */
    public void setServerPort(final String serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * @return the serverSoftware
     */
    public String getServerSoftware() {
        return this.serverSoftware;
    }

    /**
     * @param serverSoftware
     *            the serverSoftware to set
     */
    public void setServerSoftware(final String serverSoftware) {
        this.serverSoftware = serverSoftware;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ServerVariables [httpReferer=" + this.httpReferer + ", httpUserAgent=" + this.httpUserAgent + ", remoteAddress=" + this.remoteAddress
                + ", remoteHost=" + this.remoteHost + ", requestMethod=" + this.requestMethod + ", serverName=" + this.serverName + ", serverPort="
                + this.serverPort + ", serverSoftware=" + this.serverSoftware + "]";
    }

}
