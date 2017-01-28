package com.rajendarreddyj.ldapconnect.service.impl;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.rajendarreddyj.ldapconnect.service.LdapConnectService;

public class LdapConnectServiceImpl implements LdapConnectService {

    /*
     * (non-Javadoc)
     * 
     * @see com.rajendarreddyj.ldapconnect.service.LdapConnectService#
     * findAccountByUID(javax.naming.directory.DirContext,
     * java.lang.String, java.lang.String)
     */
    @Override
    public SearchResult findAccountByUID(final DirContext ctx, final String ldapSearchBase, final String uid) throws NamingException {
        String searchFilter = "(&(objectClass=person)(uid=" + uid + "))";

        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> results = ctx.search(ldapSearchBase, searchFilter, searchControls);

        SearchResult searchResult = null;
        if (results.hasMoreElements()) {
            searchResult = results.nextElement();

            // make sure there is not another item available, there should be
            // only 1 match
            if (results.hasMoreElements()) {
                System.err.println("Matched multiple users for the accountName: " + uid);
                return null;
            }
        }

        return searchResult;
    }

}
