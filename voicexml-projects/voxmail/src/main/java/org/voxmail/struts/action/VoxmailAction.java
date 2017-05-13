package org.voxmail.struts.action;

import java.text.DateFormat;
import java.util.Date;
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

public class VoxmailAction extends Action {
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

        String contactId = request.getParameter("contactId");

        logger.info(df.format(new Date()) + ": INCOMING contactId is " + contactId);

        Mailbox mailbox = null;
        try {
            mailbox = Voxmail.getInstance().getVoxmailService().retrieveMailbox(contactId);
        } catch (VoxmailException e) {
            // this is bad, cover it up! but send email and log.
            e.printStackTrace();
            request.setAttribute("msg", "tryAgain: Voxmail exception: " + e.toString());
            return mapping.findForward("testPage");
        }
        logger.info("Mailbox id: " + mailbox.getMailboxId());

        return mapping.findForward("voxmail");
    }

}
