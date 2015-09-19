/*
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 * The Original Code is vox-mail.
 *
 * The Initial Developer of the Original Code is Voxeo Corporation.
 * Portions created by Voxeo are Copyright (C) 2000-2007.
 * All rights reserved.
 * 
 * Contributor(s):
 * ICOA Inc. <info@icoa.com> (http://icoa.com)
 */

package org.voxmail.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.MappingException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.cfg.Configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.voxmail.Voxmail;
import org.voxmail.VoxmailException;
import org.voxmail.model.Mailbox;

/**
 * @author shawn
 * Follows open Session in View Pattern http://www.hibernate.org/43.html
 */
public class VoxmailSessionManagement {
	
    private static Log log = LogFactory.getLog(VoxmailSessionManagement.class);

    private static SessionFactory sessions = null;

    private static ThreadLocal session = new ThreadLocal();
    private static ThreadLocal transaction = new ThreadLocal();
    private static ThreadLocal serviceCache = new ThreadLocal();

    synchronized private static SessionFactory getSessionFactory() throws VoxmailException {
        if (sessions == null) {
            try {
                Configuration ds = new Configuration()
                    .addClass(Mailbox.class);
                    
                Properties props = Voxmail.getProps();
                if (props.getProperty("hibernate.connection.driver_class").equalsIgnoreCase("org.hsqldb.jdbcDriver") &&
                	props.getProperty("hibernate.connection.url") == null) 
                {
                	String dbServer = "jdbc:hsqldb:file:"+Voxmail.getInstance().getBasePath()+"WEB-INF/db/voxmail.hsql";
                	dbServer = dbServer.replaceAll("/","\\\\");
                	props.setProperty("hibernate.connection.url",dbServer);
                }
                ds.setProperties(props);
                
                sessions = ds.buildSessionFactory();
            } catch (MappingException e) {
                log.fatal(e);
                throw new VoxmailException("getSessionFactory() failed",e);
            } catch (HibernateException e) {
            	log.fatal(e);
                throw new VoxmailException("getSessionFactory() failed",e);
            }
        }
        return sessions;

    }

    public VoxmailSessionManagement() {
    }
    
    public void init() throws VoxmailException {
    	getSessionFactory();
    }
    
    public void destroy() throws VoxmailException {
    	session = null;
    	transaction = null;
    	serviceCache = null;
    	log = null;
    	sessions = null;
    }

    Session getSession() throws VoxmailException {
        Session s;
        try {
            s = (Session) session.get();
            if (s == null) {
                s = getSessionFactory().openSession();
                session.set(s);
            }
            getTransaction(); // create a new transaction for this session
        } catch (HibernateException e) {
            throw new VoxmailException("Unable to open a hibernate session.", e);
        }
        if (s == null) {
            throw new VoxmailException("Unable to get hibernate session!");
        }
        return s;
    }
    
    Session getExternalSession(Connection c) throws VoxmailException {
        Session s = getSessionFactory().openSession(c);
        if (s == null) {
            throw new VoxmailException("Unable to get hibernate session!");
        }
        return s;
    }

    Transaction getTransaction() throws VoxmailException {
        Transaction t = (Transaction) transaction.get();
        try {
            t = (Transaction) transaction.get();
            if (t == null) {
                t = ((Session) session.get()).beginTransaction();
                transaction.set(t);
            }
        } catch (HibernateException e) {
        	e.printStackTrace();
            throw new VoxmailException("Unable to open a hibernate session.", e);
        }

        if (t == null) {
            throw new VoxmailException("Unable to get hibernate transaction!");
        }
        return t;
    }
    
    public Hashtable getServiceCache() throws VoxmailException {
        Hashtable h = (Hashtable) serviceCache.get();

        if (h == null) {
            h = new Hashtable();
            serviceCache.set(h);
        }
        return h;
    }

    public void update() throws VoxmailException {
        try {
            getSession().flush();
        } catch (HibernateException e) {
            throw new VoxmailException("unable to save all the unsaved changes", e);
        }
    }

    public void saveObject(Object object) throws VoxmailException {
        try {
            getSession().save(object);
        } catch (HibernateException e) {
            throw new VoxmailException("unable to save object", e);
        }
    }

    public void commit() throws VoxmailException {
        try {
            if (isTransactionActive()) {
                getTransaction().commit();
                transaction.set(null);
            } else if (isSessionActive()) {
                getSession().connection().commit();
            }
        } catch (HibernateException e) {
            throw new VoxmailException("Commit failed.", e);
        } catch (SQLException e) {
        	log.error(e);
            throw new VoxmailException("Commit failed.", e);
        }
    }

    public void rollback() throws VoxmailException {
        try {
            if (isTransactionActive()) {
                getTransaction().rollback();
                transaction.set(null);
            } else if (isSessionActive()) {
                getSession().connection().rollback();
            }
        } catch (HibernateException e) {
            transaction.set(null);
            session.set(null);
            throw new VoxmailException("Rollback failed.", e);
        } catch (SQLException e) {
        	log.error(e);
            throw new VoxmailException("Rollback failed.", e);
        }
    }

    public void releaseSession() throws VoxmailException {
        try {
            if (isTransactionActive()) {
                getTransaction().commit();
                transaction.set(null);
            }
            if (isSessionActive()) {
                ((Session) session.get()).close();
                session.set(null);
            }
            if (isServiceCacheActive()) {
                serviceCache.set(null);
            }
        } catch (HibernateException e) {
            transaction.set(null);
            session.set(null);
            log.error(e);
            throw new VoxmailException("Closing Session failed.", e);
        }
    }

    private boolean isSessionActive() {
        return (session.get() != null);
    }

    private boolean isTransactionActive() {
        return (transaction.get() != null);
    }

    private boolean isServiceCacheActive() {
        return (serviceCache.get() != null);
    }


}
