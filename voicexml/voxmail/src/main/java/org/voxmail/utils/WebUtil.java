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

/*
 * WebUtil.java
 *
 * Created on January 14, 2007, 12:27 PM
 */

package org.voxmail.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 *
 * @author James
 */
public class WebUtil {
    
    /** Creates a new instance of WebUtil */
    public WebUtil() {
    }
    
    public static boolean isEmpty(String value) {
        if (value == null || value.equals("")) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean postToUrl(String page) {
        URL url;
        URLConnection urlConn;
        DataOutputStream printout;
        DataInputStream input;
        try
        {
            url = new URL(page);
            // URL connection channel.
            urlConn = url.openConnection();
            // Let the run-time system (RTS) know that we want input.
            urlConn.setDoInput(true);
            // Let the RTS know that we want to do output.
            urlConn.setDoOutput(true);
            // No caching, we want the real thing.
            urlConn.setUseCaches(false);
            // Specify the content type.
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Send POST output.
            printout = new DataOutputStream(urlConn.getOutputStream());
            String content =
                    "name=" + URLEncoder.encode("test","UTF-8") +
                    "&email=" + URLEncoder.encode("test@email.com","UTF-8");
            printout.writeBytes(content);
            printout.flush();
            printout.close();

            // Get response data.
            input = new DataInputStream(urlConn.getInputStream());
            String str;
            while (null != ((str = input.readLine()))) {
                System.out.println(str);
            }
            input.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
}
