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
java.util.StringTokenizer,
org.voxmail.Voxmail,
java.util.Properties
"
%>
<%
String useImap = Voxmail.getProps().getProperty("useImap");
String newMsgs = (String)request.getAttribute("newmsgs");
String savedMsgs = (String)request.getAttribute("savedmsgs");
String deletedMsgs = (String)request.getAttribute("deletedmsgs");
int newCount = 0;
int savedCount = 0;
int deletedCount = 0;

try {
    newCount = new StringTokenizer(newMsgs, ",").countTokens();
} catch (Exception e) {}

try {
    savedCount = new StringTokenizer(savedMsgs, ",").countTokens();
} catch (Exception e) {}

try {
    deletedCount = new StringTokenizer(deletedMsgs, ",").countTokens();
} catch (Exception e) {}

//setup the current folder
String folder = request.getParameter("folder");
if (!"saved".equals(folder) && !"deleted".equals(folder)) {
    folder = "inbox";
}
%>
<jsp:include page="_header.jsp" flush="true" />
<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td align="left" style="text-align: left; border: none">
            <a href="<%=request.getContextPath()%>/inbox.do?cmd=_getmail&platform=html&folder=inbox">inbox (<%=String.valueOf(newCount)%>)</a>
            &nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<%=request.getContextPath()%>/inbox.do?cmd=_getmail&platform=html&folder=saved">saved (<%=String.valueOf(savedCount)%>)</a>
            &nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<%=request.getContextPath()%>/inbox.do?cmd=_getmail&platform=html&folder=deleted">trash (<%=String.valueOf(deletedCount)%>)</a>
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
<h1><%=mailbox.getFirstName() + " " + mailbox.getLastName()%>&nbsp;::&nbsp;<%=folder%></h1>
<%
//get messages from session object
MailConnection mail = (MailConnection)session.getAttribute("MailConnection");
if (mail == null) {
    if (useImap != null && useImap.toLowerCase().equals("true"))
    {
        out.println("IMAP is enabled in your configuration.  Please first configure " +
        "your IMAP account settings on the \"Setup\" link above.");
    }
    else
    {
        out.println("Unable to open folder.  Please contact your administrator.");
    }
    
} else {    
    Folder inbox = mail.getInbox();
    Message[] messages = inbox.getMessages();

    int totalMessageCount = messages.length;
    int countDisplayed = 0;
    %>
    <table border="0" cellpadding="0" cellspacing="0">
    <tr>
        <th>date</th>
        <th>message</th>
        <th>move to...</th>
    </tr>
    <%
    if (totalMessageCount > 0) {
        //FLAG BITS:  1=recent, 2=seen, 4=deleted
        int folderFlagBit = 1; //default
        if ("saved".equals(folder))
            folderFlagBit = 2;
        else if ("deleted".equals(folder))
            folderFlagBit = 4;

        for (int i=0; i < totalMessageCount; i++) {
            
            int msgFlagBit = 0;
            Message message = messages[i];
            
            if (message.isSet(Flags.Flag.RECENT))
                msgFlagBit += 1;

            if (message.isSet(Flags.Flag.SEEN))
                msgFlagBit += 2;

            if (message.isSet(Flags.Flag.DELETED))
                msgFlagBit += 4;

            if ((msgFlagBit & folderFlagBit) > 0 || (msgFlagBit == 0 && folderFlagBit == 1)) {
                String strDate = "<i>unknown</i>";  //default
                try {
                    strDate = new SimpleDateFormat("MM/dd/yyyy hh:mm a").format(message.getReceivedDate());
                } catch (Exception e) {
                    out.println("DATEERROR=" + e.getMessage());
                }
                %>
                <tr>
                    <td><%=strDate%></td>
                    <td><a href="<%=request.getContextPath()%>/inbox.do?cmd=_getmsg&msgindex=<%=i+1%>&platform=html&folder=<%=folder%>" target="_blank"><%=message.getSubject()%></a></td>
                    <td><a href="<%=request.getContextPath()%>/play.do?msgindex=<%=i+1%>" target="_blank"><%=message.getSubject()%> 2</a></td>
                    
                    <td>
                        <a href="<%=request.getContextPath()%>/inbox.do?cmd=_movemsg&msgindex=<%=i+1%>&platform=html&folder=<%=folder%>&newfolder=inbox">inbox</a>
                        &nbsp;<a href="<%=request.getContextPath()%>/inbox.do?cmd=_movemsg&msgindex=<%=i+1%>&platform=html&folder=<%=folder%>&newfolder=saved">saved</a>
                        &nbsp;<a href="<%=request.getContextPath()%>/inbox.do?cmd=_movemsg&msgindex=<%=i+1%>&platform=html&folder=<%=folder%>&newfolder=deleted">deleted</a>
                    </td>
                </tr>
                <%
                ++countDisplayed;
            }
        }
    }
    out.println("</table>");
    out.println("<br><br><div align=\"left\">MESSAGE COUNT: " + countDisplayed + "</div>");
}
%>
<jsp:include page="_footer.jsp" flush="true" />