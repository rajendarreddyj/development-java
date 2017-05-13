/*
 * The contents of this file are subject to the Mozilla Public License Version 1.1 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for the specific language governing rights and limitations under the License. The
 * Original Code is vox-mail. The Initial Developer of the Original Code is Voxeo Corporation. Portions created by Voxeo
 * are Copyright (C) 2000-2007. All rights reserved. Contributor(s): ICOA Inc. <info@icoa.com> (http://icoa.com)
 */

package org.voxmail.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.voxmail.mail.MailConnection;

/**
 * @author James
 */
public class AudioUtil {
    private static final Logger logger = Logger.getAnonymousLogger();
    /** Creates a new instance of AudioUtil */
    public AudioUtil() {
    }

    public void streamAudio(final HttpServletRequest request, final HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);

        try {
            String msgindex = request.getParameter("msgindex");
            logger.info("Msg index: " + msgindex);

            MailConnection mail = (MailConnection) httpSession.getAttribute("MailConnection");
            if (mail == null) {
                logger.info("MailConnection is null");
            } else {
                logger.info("Got mail connection");
            }

            Folder inbox = mail.getInbox();
            Message message = inbox.getMessage(Integer.parseInt(msgindex));

            Multipart multipart = (Multipart) message.getContent();

            int i = 0;

            for (i = 0; i < multipart.getCount(); i++) {
                Part p2 = multipart.getBodyPart(i);

                if (p2.isMimeType("text/plain")) {
                    logger.info("MimeType: text/plain");
                    break;
                } else {
                    response.setContentType("audio/wav");
                    if (p2.getFileName() != null) {
                        logger.info("Setting content disposition");
                        // response.setHeader("Content-Disposition", "attachment; filename=\"" + p2.getFileName() +
                        // "\"");
                        OutputStream out = response.getOutputStream();
                        InputStream in = p2.getInputStream();
                        int c = in.read();
                        while (c != -1) {
                            out.write(c);
                            c = in.read();
                        }
                    } else {
                        logger.info("Filename is null");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            // logger.error("MainController::streamAudio() - failed: " + e.getMessage());
        }
    }
}
