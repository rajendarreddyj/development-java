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

package org.voxmail;

import org.voxmail.utils.PropertyLoader;
import java.util.Properties;
import org.voxmail.dao.VoxmailDAO;
import org.voxmail.dao.VoxmailSessionManagement;
import org.voxmail.service.VoxmailService;

public class Voxmail {
    private VoxmailDAO voxmailDao = null;
    private VoxmailService voxmailService = null;
    private VoxmailSessionManagement vmsm = null;
    private static Voxmail voxmail = null;
    private static Properties props = null;
    private String basePath = null;
    
    
    private Voxmail() {
        voxmailDao = new VoxmailDAO();
        voxmailService = new VoxmailService();
        vmsm = new VoxmailSessionManagement();
        
    }
    
    public static Voxmail getInstance() {
        if (voxmail == null) {
            voxmail = new Voxmail();
        }
        return voxmail;
    }
    
    /**
     * @return DAO for Voxmail DAO
     */
    public VoxmailDAO getVoxmailDAO() {
        return voxmailDao;
    }
    
    /**
     * @return DAO for Voxmail Service
     */
    public VoxmailService getVoxmailService() {
        return voxmailService;
    }
    
    /**
     * Saves any changes made to the Voxmail model objects to the database.  Should be called
     * whenever the transaction is complete.  This is normally done in the services (Business
     * Delegates), and generally should not be done in the Action classes or the DAO classes.
     * @throws VoxmailException
     */
    public void commit() throws VoxmailException {
        vmsm.commit();
    }
    
    /**
     * This will cancel any changes made to the Voxmail model.  At this point all references to
     * any Voxmail objects should be thrown away, and re-grabbed by going to a Service or DAO.
     * @throws VoxmailException
     */
    public void rollback() throws VoxmailException {
        vmsm.rollback();
    }
    
    /**
     * @throws VoxmailException
     */
    public void update() throws VoxmailException {
        vmsm.update();
    }
    
    /**
     * Saves an individual object.  This should not be commonly used, but only used when you
     * want to do a partial transaction.  Normally Voxmail.commit() will save all the model
     * changes.
     * @param object
     * @throws VoxmailException
     */
    public void saveObject(Object object) throws VoxmailException {
        vmsm.saveObject(object);
    }
    
    /**
     * This will clean up any thread context state, this should be called when the thread is done
     * doing Voxmail model work (for a web container this could be done in a filter before the
     * thread is returned to the thread pool.)
     * @throws VoxmailException
     */
    public void releaseSession() throws VoxmailException {
        vmsm.releaseSession();
    }
    
    /**
     * @return Returns the Properties from bluesky.properties file.
     */
    public static Properties getProps() {
        if (props == null) {
            props = PropertyLoader.loadProperties("voxmail.properties");
        }
        return props;
    }
    
    /**
     * Initializes the Voxmail application, should be called when the application is instantiated.
     * @throws Voxmail Exception
     */
    public void init() throws VoxmailException {
        vmsm.init();
    }
    
    /**
     * Cleans up the Voxmail application, should be called when application context is destroyed.
     * @throws VoxmailException
     */
    public void destroy() throws VoxmailException {
        vmsm.destroy();
        props = null;
    }

	public void setBasePath(String realPath) {
		basePath = realPath;
	}
	
	public String getBasePath()
	{
		return basePath;
	}
}
