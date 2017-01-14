<%--
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
 *--%>
<%@page contentType="text/html"%>
<%
String event = request.getParameter("event");

if ("logout".equals(event))
    event = "SUCCESSFUL LOGOUT";
else if ("login-failed".equals(event))
    event = "INVALID LOGIN";
else
    event = null;
%>
<html>
    <head>
        <title>Voxmail Voicemail</title>
        <link rel="stylesheet" href="html/voxmail.css" type="text/css">
    </head>
    <body topmargin="0" leftmargin="0" rightmargin="0" background="html/images/cheat_2.gif">
         <input type="hidden" name="cmd" value="doLogin">
        <table border="0" border="0" cellspacing="0" cellpadding="0" align="left">
        <tr><td>
                <table width="98%" border="0" cellspacing="0" cellpadding="0" align="left">
                    <tr>
                        <td width="721"><img src="html/images/head.jpg" width="267" height="177"></td>
                        
                    </tr>
                    <tr><td><img name="home" border="0" src="html/images/dark.jpg" width="721" height="33"></td></tr>
                    <tr>
                        <td><img src="html/images/lt_blue_blank_1.jpg" width="721" height="42"></td>
                    </tr>
                </table>
        </td></tr>
        <tr><td>
        
        <table>
            <tr>
                <td width="35">&nbsp;</td>
                <td>
                    <p class="heading">Voxmail Login</p>
                    <form method="POST" action="<%=request.getContextPath()%>/inbox.do" name="login">
                        <input type="hidden" name="cmd" value="doLogin">
                        <table>
                            <tr>
                                <td>
                                    <font color="#000000" face="verdana, sans-serif" size="1">
                                        <%
                                        if (event != null) {
                                            out.println("<font color=\"#000000\">" + event + "</font><hr><br>");
                                        }
                                        %>
                                    <b>phone:</b>&nbsp;(no dashes)</font><br>
                                    <input type="text" name="phone" size="17" value="" tabindex="1">
                                    <br>
                                    <font color="#000000" face="verdana, sans-serif" size="1"><b>pin:</b></font><br>
                                    <input name="pin" size="17" type="password" tabindex="2">
                                    <br>
                                    <input type="submit" value="logon" class="button" name="action">
                                </td>
                            </tr>
                        </table>
                    </form>
            </td></tr>
        </table>
        <br><br><br>
    </body>
</html>