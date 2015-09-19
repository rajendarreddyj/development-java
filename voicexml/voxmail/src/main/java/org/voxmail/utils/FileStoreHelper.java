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

package org.voxmail.utils;

import java.io.File;

/**
 *
 * @author  Rick
 */
public class FileStoreHelper {
    
    /** Creates a new instance of FileStoreHelper */
    public FileStoreHelper() {
    }
    
    //used to test and create local mailbox storage directories for maildir
    public void verifyMailboxExists(String mailboxPath) throws Exception {
        File[] dir = { new File(mailboxPath + "/tmp"), new File(mailboxPath + "/new"), new File(mailboxPath + "/cur") };
        
        try {
            for (int i = 0; i < dir.length; i++) {
                if (!dir[i].exists()) {
                    dir[i].mkdirs();
                }
            }
        } catch (Exception e) {
            throw new Exception("FileStoreHelper::verifyMailboxExists() - failed: " + e.getMessage());
        }
    }    
}
