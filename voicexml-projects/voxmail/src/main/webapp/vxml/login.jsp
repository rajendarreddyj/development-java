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
<%
    Mailbox mailbox = (Mailbox) request.getSession().getAttribute("mailbox");
    boolean doPhoneNumber=false;
    if (mailbox == null)
    {
        doPhoneNumber = true;
    }
%>
<%@ include file="/vxml/imports.jsp"%>
<?xml version="1.0" encoding="UTF-8"?>
<vxml version="2.0" application="<%=request.getContextPath()%>/vxml/approot.jsp">
    
    <var name="cmd" />
    <var name="phone" /> 
<%
if ("invalid".equals(request.getParameter("result"))) {
%>
<form id="error">
    <block>
        <prompt>
            <audio expr="audioDir + 'InvalidLogin.wav'">
                I'm sorry, but that was an invalid login.  
                
            </audio>
        </prompt>
        <clear namelist="pin"/>
        <%if (doPhoneNumber) {%>
        <goto next="#collectPhoneNumber"/>
        <%} else{%>
        <goto next="#login"/>
        <%}%>
    </block>
</form>
<% } %>

<%if (doPhoneNumber) {%>
<form id="collectPhoneNumber">
   
    <field name="phoneNumber" type="digits?length=10">
        <prompt bargein="true" timeout="5s">
            <audio expr="audioDir + 'GetPhoneNumber.wav'">
                Please enter your 10 digit phone number.
            </audio>
        </prompt>
        <filled>
            <assign name="phone" expr="phoneNumber" />
            <goto next="#login"/>
        </filled>
        <noinput count="1"> 
          <reprompt/>
        </noinput>
         <noinput count="2"> 
          <reprompt/>
        </noinput>
    </field>
</form>
<% } %>

<form id="login">
    
    <field name="pin" type="digits?length=4">
        <prompt bargein="true" timeout="5s">
            <audio expr="audioDir + 'GetPin.wav'">
                Now enter your 4 digit pin.
            </audio>
        </prompt>
        <filled>
            <assign name="cmd" expr="'login'" />
            <submit next="login.do" namelist="cmd pin phone" method="post" />
        </filled>
    </field>
</form>

</vxml>