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
<%@include file="/security.jsp"%>
<%@page import="
javax.mail.*,
javax.mail.internet.*,
org.voxmail.mail.MailConnection,
java.text.SimpleDateFormat,
java.util.Date,
java.util.StringTokenizer
"
%>
<% 
    String error = request.getParameter("error");
    boolean hasError = false;
    if (error != null && error.length() > 0)
        {
        hasError = true;
        }
%>
<jsp:include page="_header.jsp" flush="true" />
<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td align="left" style="text-align: left; border: none">
            &nbsp;
        </td>
        <td align="right" style="text-align: right; border: none">
            <a href="<%=request.getContextPath()%>/inbox.do?cmd=_refresh&platform=html&folder=inbox">check for new mail</a>
            &nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<%=request.getContextPath()%>/inbox.do?cmd=_emptytrash&platform=html&folder=inbox">empty trash</a>
            &nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<%=request.getContextPath()%>/inbox.do?cmd=_pin">setup</a>
            &nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<%=request.getContextPath()%>/inbox.do?cmd=doLogout">log out</a>
        </td>
    </tr>
</table>
<br>
<h1><%=mailbox.getFirstName() + " " + mailbox.getLastName()%>&nbsp;::&nbsp;Setup</h1>

<table>
    <tr><td>&nbsp;</td></tr>
    <tr><td>
        <form method="post" action="<%=request.getContextPath()%>/inbox.do" >
            <input type="hidden" name="cmd" value="doPin">
            <input type="hidden" name="mailboxId" value="<%=mailbox.getMailboxId()%>">
            <table border="0" >
                <% if (hasError) {%>
                <tr>
                    <td colspan="2"><font color="red">
                            The pins below did not match. 
                        </font>
                    </td>
                </tr>
                <%}%>
                <tr>
                    <td width ="120">
                        4 Digit Pin:
                    </td>
                    <td width="500">
                        <input type="text" name="pin" value="<%=mailbox.getPin()%>" maxlength="4">
                    </td>
                </tr>
                <tr>
                    <td colspan=2><br><br>
                        The IMAP values below are only used if you are using IMAP as your mail store.
                    </td>
                    
                </tr>
                <tr>
                    <td >
                        IMAP Username
                    </td>
                    <td>
                        <input type="text" name="imapUsername" value="<%=mailbox.getImapUsername()%>" >
                    </td>
                </tr>
                <tr>
                    <td >
                        IMAP Password 
                    </td>
                    <td>
                        <input type="text" name="imapPassword" value="<%=mailbox.getImapPassword()%>" >
                    </td>
                </tr>
                <tr>
                    <td >
                        &nbsp;
                    </td>
                    <td>
                        <input type="submit" name="save" value="save">
                    </td>
                </tr>
                
            </table>
        </form>
    </td></tr>
</table>
<jsp:include page="_footer.jsp" flush="true" />