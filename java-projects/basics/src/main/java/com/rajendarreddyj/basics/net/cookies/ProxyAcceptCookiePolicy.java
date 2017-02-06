package com.rajendarreddyj.basics.net.cookies;

import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

public class ProxyAcceptCookiePolicy implements CookiePolicy {
    String acceptedProxy;

    public ProxyAcceptCookiePolicy(final String acceptedProxy) {
        this.acceptedProxy = acceptedProxy;
    }

    @Override
    public boolean shouldAccept(final URI uri, final HttpCookie cookie) {
        String host;
        try {
            host = InetAddress.getByName(uri.getHost()).getCanonicalHostName();
        } catch (UnknownHostException e) {
            host = uri.getHost();
        }

        if (!HttpCookie.domainMatches(this.acceptedProxy, host)) {
            return false;
        }

        return CookiePolicy.ACCEPT_ORIGINAL_SERVER.shouldAccept(uri, cookie);
    }
}
