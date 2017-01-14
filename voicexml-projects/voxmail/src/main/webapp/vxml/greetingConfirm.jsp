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
    
    <form id="GreetingConfirm">
        <field name="confirm" >
            <grammar src="grammars/GreetingConfirm.xml" type="application/grammar-xml"/> 
            <prompt>
                <audio expr="audioDir + 'GreetingChanged.wav'">
                    Your greeting has been changed.  
                    To go back to your messages, press 1.  
                    Or, if you're done, press 2.
                </audio>
            </prompt>
            <filled> 
                <if cond="confirm=='messages'"> 
                    <submit next="mainMenu.do" namelist="" method="post" />
                    
                <else/> 
                    <prompt>
                        <audio expr="audioDir + 'Goodbye.wav'">
                            Goodbye
                        </audio>
                    </prompt>
                    <disconnect/>
                </if> 
            </filled>
        </field>
    </form>
</vxml>