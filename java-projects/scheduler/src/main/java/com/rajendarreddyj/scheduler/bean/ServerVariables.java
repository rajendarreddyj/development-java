package com.rajendarreddyj.scheduler.bean;

/**
 * @author rajendarreddy
 *
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
        return httpReferer;
    }
    /**
     * @param httpReferer the httpReferer to set
     */
    public void setHttpReferer(String httpReferer) {
        this.httpReferer = httpReferer;
    }
    /**
     * @return the httpUserAgent
     */
    public String getHttpUserAgent() {
        return httpUserAgent;
    }
    /**
     * @param httpUserAgent the httpUserAgent to set
     */
    public void setHttpUserAgent(String httpUserAgent) {
        this.httpUserAgent = httpUserAgent;
    }
    /**
     * @return the remoteAddress
     */
    public String getRemoteAddress() {
        return remoteAddress;
    }
    /**
     * @param remoteAddress the remoteAddress to set
     */
    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }
    /**
     * @return the remoteHost
     */
    public String getRemoteHost() {
        return remoteHost;
    }
    /**
     * @param remoteHost the remoteHost to set
     */
    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }
    /**
     * @return the requestMethod
     */
    public String getRequestMethod() {
        return requestMethod;
    }
    /**
     * @param requestMethod the requestMethod to set
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
    /**
     * @return the serverName
     */
    public String getServerName() {
        return serverName;
    }
    /**
     * @param serverName the serverName to set
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
    /**
     * @return the serverPort
     */
    public String getServerPort() {
        return serverPort;
    }
    /**
     * @param serverPort the serverPort to set
     */
    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }
    /**
     * @return the serverSoftware
     */
    public String getServerSoftware() {
        return serverSoftware;
    }
    /**
     * @param serverSoftware the serverSoftware to set
     */
    public void setServerSoftware(String serverSoftware) {
        this.serverSoftware = serverSoftware;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ServerVariables [httpReferer=" + httpReferer + ", httpUserAgent=" + httpUserAgent + ", remoteAddress=" + remoteAddress + ", remoteHost="
                + remoteHost + ", requestMethod=" + requestMethod + ", serverName=" + serverName + ", serverPort=" + serverPort + ", serverSoftware="
                + serverSoftware + "]";
    }
    
}
