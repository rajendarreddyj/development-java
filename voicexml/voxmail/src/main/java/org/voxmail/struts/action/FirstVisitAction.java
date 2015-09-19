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

package org.voxmail.struts.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.voxmail.Voxmail;
import org.voxmail.model.Mailbox;

import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;


public class FirstVisitAction extends Action {
    
    static DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
    public String filePath_Greetings = null; //directory where recorded messages are saved
    public static boolean isInitialized = false;
    
    
    /**
     * Method execute
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        
        try
        {
            this.init();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        response.setContentType("vxml");
        response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
        response.setHeader("Pragma","no-cache"); //HTTP 1.0
        response.setDateHeader("Expires", 0); //prevents caching at the proxy server
        
        String cmd = "";
        String greetingType = "";
        
        
        System.out.println(df.format(new Date())+"FirstVisitAction: Saving a greeting.");
        String forward="";

        try
        {
            forward = this.processAndSaveGreeting(request,response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        if (forward.equals("standardConfirm"))
        {
            request.setAttribute("confirm","standardConfirm");
        }
        else
        {
            request.setAttribute("confirm","customConfirm");
        }
        return mapping.findForward("confirm");

        
    }
    
    public void init() throws ServletException {
        
        if (isInitialized)
        {
            return;
        }
        else
        {
            isInitialized = true;
        }
        
        
        //get our recorded greetings path
        filePath_Greetings = Voxmail.getProps().getProperty("filePath_Greetings");
        
        //test the directory where we'll be saving recorded greetings
        File dir2 = new File(filePath_Greetings);
        if (!dir2.exists() || !dir2.isDirectory()) {
            //System.out.println("Could not find filePath_Greetings: " + filePath_Greetings);
            throw new ServletException("Could not find filePath_Greetings: " + filePath_Greetings);
        } else {
            if (!filePath_Greetings.endsWith("/") && !filePath_Greetings.endsWith("\\"))
                filePath_Greetings += File.separator;
        }
        
        System.out.println("LeaveMessageAction filePath_Greetings=" + filePath_Greetings);
        
    }
    
  
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected String processAndSaveGreeting(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        
        long savedFileSize = 0; //size of file, if saved
        String mailboxId = "";
        String cmd="";
        String greetingType="";
        String nextPage = "";
        
        System.out.println("#---------------------------------------------------#");
        System.out.println("FirstVisitAction - Attempting to parse stream...");
        
        String platform = null;
        
        try {
            
            MultipartParser mp = new MultipartParser(request, 25*1024*1024); // 25MB
            Part part;
                               
            FilePart filePart = null;
            ByteArrayOutputStream bout = null;
            String audioFileName = null;
            Mailbox mailbox = (Mailbox) request.getSession().getAttribute("mailbox");
            
            while ((part = mp.readNextPart()) != null) {

                String name = part.getName();
                if (part.isParam()) {
                    // it's a parameter part
                    ParamPart paramPart = (ParamPart) part;
                    String value = paramPart.getStringValue();

                    //get important params
                    if ("mailboxId".equals(name))
                        mailboxId = value;
                    else if ("greetingType".equals(name))
                        greetingType = value;
                    else if ("cmd".equals(name))
                        cmd = value;
                    
                    System.out.println("name=" + name + ", value=" + value);                    
                    
                } else if (part.isFile()) {
                    //put the file into a ByteArrayOutputStream.  We'll save it when we finish parsing
                    filePart = (FilePart)part;
                    
                    audioFileName = filePart.getFileName();
                    System.out.println("filename=" + audioFileName);
                    
                    bout = new ByteArrayOutputStream();
                    filePart.writeTo(bout);
                }
            }//while
            
            
            //if we got a file, try to save it
            if (filePart != null && bout != null) {
                try {                    
                    if (audioFileName != null) { //we really do have a file!
                        System.out.println("we really do have a file");
                        
                        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_kk,mm,S").format(new Date());
                        String realPath = request.getRealPath(filePath_Greetings);
                        String myPath = realPath + File.separator;
                        String myFileName = mailboxId + "." + audioFileName + "." + timeStamp;

                        //test directory
                        File dir = new File (myPath);
                        if (!dir.exists()) {
                            System.out.println("Directory does not exist: " + myPath);
                        }

                        File file = new File(myPath + myFileName);

                        FileOutputStream fos = new FileOutputStream(file); //write data to temp file 
                        bout.writeTo(fos);
                        fos.close();
                        savedFileSize = file.length();                            

                        //Now save the path in the mailbox
                        
                        if (greetingType.equals("name"))
                        {
                            mailbox.setGreetingType(Mailbox.GREETING_TYPE_NAME);
                            mailbox.setRecordedName(myFileName);
                            nextPage = "customConfirm";
                        }
                        else if (greetingType.equals("custom"))
                        {
                            mailbox.setGreetingType(Mailbox.GREETING_TYPE_GREETING);
                            mailbox.setRecordedGreeting(myFileName);
                            nextPage = "customConfirm";
                        }
                        else
                        {
                            mailbox.setGreetingType(Mailbox.GREETING_TYPE_DEFAULT);
                            nextPage = "standardConfirm";
                        }
                        
                        //Save mailbox info
                        mailbox.setFirstTime(false);
                        System.out.println("FirstVisitAction - saving mailbox");
                        Voxmail.getInstance().getVoxmailService().updateGreeting(mailbox);


                    } else {
                        System.out.println("FirstVisitAction - Empty or missing file part");
                    }
                } catch (Exception e) {
                    System.out.println("Unable to save file: " + e.getMessage());
                }  
            } else {
                System.out.println("Selected the standard greeting");
                mailbox.setGreetingType(Mailbox.GREETING_TYPE_DEFAULT);
                nextPage = "standardConfirm";
                
                //Save mailbox info
                System.out.println("FirstVisitAction - saving mailbox with standard greeting");
                Voxmail.getInstance().getVoxmailService().updateGreeting(mailbox);
            }
            
            System.out.println("FirstVisitAction: mailboxId=" + mailboxId + "*");
            
        } catch (Exception e) {
            //try to at least grab the nextpage
            e.printStackTrace();
            System.out.println("Parsing failed: " + e.getMessage());
            System.out.println("We're still going to try and redirect to nextpage.");
        }
        
       return nextPage;
        
    }
    
        
}

