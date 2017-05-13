/*
 * The contents of this file are subject to the Mozilla Public License Version 1.1 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for the specific language governing rights and limitations under the License. The
 * Original Code is vox-mail. The Initial Developer of the Original Code is Voxeo Corporation. Portions created by Voxeo
 * are Copyright (C) 2000-2007. All rights reserved. Contributor(s): ICOA Inc. <info@icoa.com> (http://icoa.com)
 */

package org.voxmail.struts.action;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.voxmail.Voxmail;
import org.voxmail.VoxmailException;
import org.voxmail.model.Mailbox;
import org.voxmail.utils.WebUtil;

public class LoginAction extends Action {
    private static final Logger logger = Logger.getAnonymousLogger();
    static DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

    /**
     * Method execute
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     */
    @Override
    public ActionForward execute(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) {

        response.setContentType("vxml");
        response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // prevents caching at the proxy server

        String mailboxId = request.getParameter("mailboxId");
        String cmd = request.getParameter("cmd");
        String phone = request.getParameter("phone");
        String pin = request.getParameter("pin");

        logger.info(df.format(new Date()) + ": LoginAction mailboxId is " + mailboxId + ", cmd: " + cmd);

        if (WebUtil.isEmpty(mailboxId) && WebUtil.isEmpty(cmd)) {
            return mapping.findForward("login");
        }
        if (WebUtil.isEmpty(cmd)) {
            Mailbox mailbox = null;
            try {

                mailbox = Voxmail.getInstance().getVoxmailService().retrieveMailbox(mailboxId);

                if (mailbox != null) {
                    logger.info("LoginAction mailbox found for mailboxId: " + mailboxId);
                } else {
                    logger.info("LoginAction mailbox not found for mailboxId: " + mailboxId);
                }
                request.getSession().setAttribute("mailbox", mailbox);

            } catch (VoxmailException e) {
                // this is bad, cover it up! but send email and log.
                e.printStackTrace();
                request.setAttribute("msg", "tryAgain: Voxmail exception: " + e.toString());
                return mapping.findForward("error");
            }

            return mapping.findForward("login");

        } else {

            Mailbox mailbox = null;

            if (!WebUtil.isEmpty(phone)) {
                logger.info("LoginAction retrieving mailbox by phone number: " + phone);
                List<Mailbox> mailboxes = null;
                try {
                    mailboxes = Voxmail.getInstance().getVoxmailService().retrieveMailboxByPhoneNumber(phone);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (mailboxes == null) {
                    // do nothing
                    logger.info("No mailboxes for phone number: " + phone);
                } else {
                    if (mailboxes.size() > 1) {
                        logger.info("More than one mailbox retrieved for phone number: " + phone);
                    }
                    Iterator<Mailbox> it = mailboxes.iterator();
                    while (it.hasNext()) {
                        Mailbox box = it.next();
                        if (box.getPin().equals(pin)) {
                            mailbox = box;
                        }
                    }

                }

            } else {
                mailbox = (Mailbox) request.getSession().getAttribute("mailbox");
            }

            if (mailbox == null) {
                logger.info("LoginAction: on login, mailbox not found in session.");
                return mapping.findForward("invalidLogin");
            }
            if (mailbox.getPin().equals(pin)) {
                logger.info("LoginAction: valid login");
                logger.info("isFirstTime: " + mailbox.isFirstTime());

                request.getSession().setAttribute("mailbox", mailbox);

                if (mailbox.isFirstTime()) {
                    logger.info("LoginAction: isFirst time - forward to greeting setup");
                    return mapping.findForward("firstVisit");
                } else {
                    return mapping.findForward("mainMenu");
                }
            } else {
                logger.info("LoginAction: invalid login");
                return mapping.findForward("invalidLogin");
            }
        }
    }

}
