package com.rajendarreddyj.basics.net.cookies;

import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.List;

public class PersistentCookieStore implements CookieStore, Runnable {
    CookieStore store;

    public PersistentCookieStore() {
        this.store = new CookieManager().getCookieStore();
        // deserialize cookies into store
        Runtime.getRuntime().addShutdownHook(new Thread(this));
    }

    @Override
    public void run() {
        // serialize cookies to persistent storage
    }

    @Override
    public void add(final URI uri, final HttpCookie cookie) {
        this.store.add(uri, cookie);

    }

    @Override
    public List<HttpCookie> get(final URI uri) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<HttpCookie> getCookies() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<URI> getURIs() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(final URI uri, final HttpCookie cookie) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAll() {
        // TODO Auto-generated method stub
        return false;
    }

}
