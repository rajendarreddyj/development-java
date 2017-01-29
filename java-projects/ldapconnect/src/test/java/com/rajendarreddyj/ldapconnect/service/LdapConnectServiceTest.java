package com.rajendarreddyj.ldapconnect.service;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.junit.Ignore;

import com.rajendarreddyj.ldapconnect.service.impl.LdapConnectServiceImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
@Ignore
public class LdapConnectServiceTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName
     *            name of the test case
     */
    public LdapConnectServiceTest(final String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(LdapConnectServiceTest.class);
    }

    /**
     * Rigourous Test :-)
     * 
     * @throws NamingException
     */
    public void testApp() throws NamingException {
        final String ldapAdServer = "ldap://localhost:10389";
        final String ldapSearchBase = "o=sevenSeas";

        final String ldapUsername = "uid=admin,ou=system";
        final String ldapPassword = "secret";

        final String ldapUIDToLookup = "hnelson";

        Hashtable<String, Object> env = new Hashtable<>();
        env.put(Context.SECURITY_AUTHENTICATION, "Simple");
        if (ldapUsername != null) {
            env.put(Context.SECURITY_PRINCIPAL, ldapUsername);
        }
        if (ldapPassword != null) {
            env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
        }
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapAdServer);

        // ensures that objectSID attribute values
        // will be returned as a byte[] instead of a String
        env.put("java.naming.ldap.attributes.binary", "objectSID");

        // the following is helpful in debugging errors
        env.put("com.sun.jndi.ldap.trace.ber", System.err);

        LdapContext ctx = new InitialLdapContext(env, null);

        LdapConnectService ldap = new LdapConnectServiceImpl();

        // lookup the ldap account
        SearchResult srLdapUser = ldap.findAccountByUID(ctx, ldapSearchBase, ldapUIDToLookup);
        assertNotNull(srLdapUser);

    }
}
