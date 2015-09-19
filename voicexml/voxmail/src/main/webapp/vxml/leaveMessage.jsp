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
Mailbox mailbox = (Mailbox) request.getSession().getAttribute("mailbox");
int greetingType = mailbox.getGreetingType();
String greetingPath = (String) request.getAttribute("greetingPath");

System.out.println("leaveMessage.jsp mailboxId: " + mailbox.getMailboxId());
%>
<?xml version="1.0" encoding="UTF-8"?>
<vxml version="2.0" application="<%=request.getContextPath()%>/vxml/approot.jsp">
    
    <var name="mailboxId" expr="'<%=mailbox.getMailboxId()%>'" />
    <var name="cmd" />
    <var name="selected" />
    <var name="recording" />
    <var name="playIntro" expr="true"/>
    
    <form id="LeaveMessageIntro">
        <field name="selected" >
            <grammar src="grammars/Login.xml" type="application/grammar-xml"/> 
            <prompt timeout="0.1s">
                <% if (greetingType == Mailbox.GREETING_TYPE_GREETING) {%>
                <audio expr="'<%=mailbox.getRecordedGreeting(greetingPath)%>'">
                    The person you called is not available.  Please leave a message
                    after the tone.  When you're done, you can hang up or press pound
                    for more options.
                </audio>
                <%} else if (greetingType == Mailbox.GREETING_TYPE_NAME) {%>
                <audio expr="audioDir + 'ReachedVoicemail.wav'">
                    You've reached the voicemail for
                </audio>
                <audio expr="'<%=mailbox.getRecordedName(greetingPath)%>'">
                    Name here
                </audio>
                <audio expr="audioDir + 'LeaveMessageAfterTone.wav'">
                    Please leave a message after the tone.
                    When you're done, you can hang up or press pound for more options.
                </audio>
                <%} else {%>
                <audio expr="audioDir + 'StandardGreeting.wav'">
                    The person you called is not available.  Please leave a message
                    after the tone.  When you're done, you can hang up or press pound
                    for more options.
                </audio>
                <%}%>
            </prompt>
            <filled>
                <if cond="selected=='login'">
                    <submit next="login.do" namelist="mailboxId" method="post"/>
                <else/>
                    <goto next="#LeaveMessage"/>
                </if>
            </filled>
            <noinput>
                <goto next="#LeaveMessage"/>
            </noinput>
            <nomatch>
                <goto next="#LeaveMessage"/>
            </nomatch>
        </field>
    </form>
    
    <form id="LeaveMessage">
        <property name="interdigittimeout" value="4s"/>
        <record name="CallersMessage" beep="true" maxtime="120s" dtmfterm="true" finalsilence="4s" type="audio/x-wav" > 
            <filled>
                <assign name="recording" expr="CallersMessage"/>
            </filled>
            <noinput>
                <prompt>
                    <audio expr="audioDir + 'SorryDidntHear.wav'">
                        I'm sorry, but I didn't hear anything.  Let's try that once more.
                    </audio>
                </prompt> 
            </noinput>
            <catch event="connection.disconnect.hangup"> 
                <assign name="recording" expr="CallersMessage"/>
                <assign name="callerId" expr="session.telephone.ani" />
                <assign name="cmd" expr="'save'" />
                <submit next="leaveMessage.do" namelist="cmd callerId mailboxId recording" method="post" enctype="multipart/form-data" fetchhint="safe"/>
            </catch> 
        </record>
        
        
        <field name="confirm">
            <property name="timeout" value="3000ms"/>
            <grammar src="grammars/LeaveMessage.xml" type="application/grammar-xml"/>
            <prompt>
                <audio expr="audioDir + 'ReviewLeftMessage.wav'">
                    To review your message, press 1.
                    To rerecord it, press 2.
                    To cancel, press 3.
                    To save the message and then call someone else, press 4.
                    Or, to save it and disconnect, press 5.
                </audio>
            </prompt>  
            <filled>
                
                <log expr="'******************************'"/>
                <log expr=" '***** CONFIDENCE = ' + confirm$.confidence + '*****'"/>
                <log expr=" '***** SELECTED VALUE = ' + confirm$.interpretation.selected + '*****'"/>
                <log expr="'******************************'"/>
                <assign name="selected" expr="confirm$.interpretation.selected"/> 
                
                <if cond="selected=='save'">
                    
                    <assign name="callerId" expr="session.telephone.ani" />
                    <assign name="cmd" expr="'save'" />
                    <submit next="leaveMessage.do" namelist="cmd callerId mailboxId recording" method="post" enctype="multipart/form-data" fetchhint="safe"/>
                    
                <elseif cond ="selected =='review'"/>
                    <log expr=" '***** Got to review *****'"/>
                    <prompt>
                        <value expr="recording"/> 
                    </prompt>
                    <log expr=" '*****Right before confirm *****'"/>
                    <clear namelist="confirm"/>
                    <log expr=" '***** Right after confirm *****'"/>
                    <prompt>
                        <audio expr="audioDir + 'HearMessageAgain.wav'">
                            To hear that again, press 1.
                            To rerecord the message, press 2.
                            To cancel, press 3.
                            To save the message and then call someone else, press 4.
                            Or, to save it and disconnect, press 5.
                        </audio>
                    </prompt>
                    <goto nextitem="confirm" />
                    
                <elseif cond ="selected =='rerecord'"/>
                    <clear namelist="recording CallersMessage confirm" />
                    <prompt>
                        <audio expr="audioDir + 'RecordMessage.wav'">
                            Record your message after the tone.  When you're done, you
                            can hang up or press pound.
                        </audio>
                    </prompt>
                    <goto nextitem="CallersMessage"/>
                    
                <elseif cond="selected =='cancel'"/>
                    <goto next="#Cancel"/>
                    
                <elseif cond="selected =='call'"/>
                    <prompt>
                        <audio expr="audioDir + 'MessageSaved.wav'">
                            Message Saved
                        </audio>
                    </prompt>
                    
                    <assign name="callerId" expr="session.telephone.ani" />
                    <assign name="cmd" expr="'saveandtransfer'" />
                    <submit next="leaveMessage.do" namelist="cmd callerId mailboxId recording" method="post" enctype="multipart/form-data" fetchhint="safe"/>
                    
                <elseif cond="selected =='login'"/>
                    <submit next="login.do" namelist="mailboxId" method="post"/>
                    
                <else/>
                    <prompt>
                        <audio expr="audioDir + 'SaveOptions2.wav'">
                            Should not get here.
                        </audio>
                    </prompt>
                </if>
            </filled>
            
        </field>
        
        <catch event="connection.disconnect.hangup"> 
            <assign name="callerId" expr="session.telephone.ani" />
            <assign name="cmd" expr="'save'" />
            <submit next="leaveMessage.do" namelist="cmd callerId mailboxId recording" method="post" enctype="multipart/form-data" fetchhint="safe"/>
        </catch> 
    </form>
    
    <form id="Cancel">
        <field name="confirm" >
            <grammar src="grammars/LeaveMessageCancel.xml" type="application/grammar-xml"/> 
            <prompt>
                <audio expr="audioDir + 'MessageCancelled.wav'">
                    Message canceled.  
                    If you'd like to record a new message, press 1.
                    To call someone else in the company, press 2. 
                    Or If you're done, press 3.
                </audio>
            </prompt>
            <filled> 
                <if cond="confirm=='rerecord'"> 
                    <prompt>
                        <audio expr="audioDir + 'RecordMessage.wav'">
                            Record your message after the tone.  When you're done, you
                            can hang up or press pound.
                        </audio>
                    </prompt>
                    <assign name="playIntro" expr="false" />
                    <goto next="#LeaveMessage"/>
                    
                <elseif cond="confirm =='call'"/>
                    <submit next="transferAttendant.do" namelist="" method="post" />
                    
                <else/> 
                    <prompt>
                        <audio expr="audioDir + 'Goodbye.wav'">
                            Goodbye.
                        </audio>
                    </prompt>
                    <disconnect/>
                </if> 
            </filled>
            
        </field>
        
    </form>
</vxml>