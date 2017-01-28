package com.rajendarreddyj.ldapconnect.service;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchResult;

/**
 * @author rajendarreddy
 */
public interface LdapConnectService {

    /**
     * @param ctx
     * @param ldapSearchBase
     * @param uid
     * @return
     * @throws NamingException
     */
    SearchResult findAccountByUID(DirContext ctx, String ldapSearchBase, String uid) throws NamingException;

}
