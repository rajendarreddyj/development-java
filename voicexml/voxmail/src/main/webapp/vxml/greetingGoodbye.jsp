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
<%@ include file="/vxml/imports.jsp"%>
<%
System.out.println("greetingGoodbye.jsp called");
String type = (String) request.getAttribute("confirm");
%>
<?xml version="1.0" encoding="UTF-8"?>
<vxml version="2.0" application="<%=request.getContextPath()%>/vxml/approot.jsp">
    
<form id="end">
    <block name="Goodbye">
        <prompt>
            <% if (type.equals("standardConfirm")) {%>
            <audio expr="audioDir + 'SelectedStandardGreeting.wav'">
                OK, you've selected the standard greeting, and you're all set.
                Just so you know, you can return to voicemail to change your
                greeting at any time.  Goodbye!
            </audio>
            <%} else {%>
            <audio expr="audioDir + 'SetupGreetingSaved.wav'">
                OK, your greeting is saved, and you're all set.  Just so you know,
                you can return to voicemail to change your greeting at any time.
                Goodbye!
            </audio>
            <%}%>
        </prompt>
        <disconnect/>
    </block>
</form>
</vxml>