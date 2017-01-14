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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/** 
 * MyEclipse Struts
 * Creation date: 06-04-2006
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class TestAction extends Action {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

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
		
                String callerId = request.getParameter("callerID");
		String lang = request.getParameter("lang");
		String token = request.getParameter("token");
                if (token == null || token.equals(""))
                {
                    return mapping.findForward("testPage");
                }
//                Prize prize = null;
//		try {
//			prize = Voxmail.getInstance().getBlueSkyService().isWinner(token, callerId, lang);
//		} catch (VoxmailException e) {
//			// this is bad, cover it up! but send email and log.
//			e.printStackTrace();
//                        request.setAttribute("msg","tryAgain: BlueSky exception: " + e.toString());
//                        return mapping.findForward("testPage");
//                        
//                        
//		} catch (InvalidTokenException e) {
//                       request.setAttribute("msg","Invalid Token");
//                       return mapping.findForward("testPage");
//
//		} catch (AlreadyUsedTokenException e) {
//                        request.setAttribute("msg","Already Used");
//                        return mapping.findForward("testPage");
//                        
//		} catch (PreviousWinnerException e) {
//			request.setAttribute("msg","Previous Winner");
//                        return mapping.findForward("testPage");
//			
//		}
//
//		if (prize != null) {
//			request.setAttribute("prize", prize);
//			if (prize.getPrizeType().equalsIgnoreCase(Prize.MAJOR)) {
//				
//                                request.setAttribute("msg","Major Winner");
//			}
//			if (prize.getPrizeType().equalsIgnoreCase(Prize.MINOR)) {
//                                request.setAttribute("msg","Minor Winner");
//			}
//		}
		
                else
                {
                    request.setAttribute("msg","Not a winner, token updated");   
                }
                return mapping.findForward("testPage");
                
	}

}

