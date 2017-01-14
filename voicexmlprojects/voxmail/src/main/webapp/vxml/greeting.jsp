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
System.out.println("greeting.jsp mailboxId: " + mailbox.getMailboxId());
%>
<?xml version="1.0" encoding="UTF-8"?>
<vxml version="2.0" application="<%=request.getContextPath()%>/vxml/approot.jsp">
    
    <var name="mailboxId" expr="'<%=mailbox.getMailboxId()%>'" />
    <var name="cmd" expr="'save'" />
    <var name="selected" />
    <var name="recording" />
    <var name="playIntro" expr="true"/>
    
    <var name="greetingType" />
    
    <form id="GreetingIntro">
        <field name="choice" >
            <grammar src="grammars/GreetingOptions.xml" type="application/grammar-xml"/> 
            <prompt cond="playIntro">>
                <audio expr="audioDir + 'ReviewCurrentGreeting.wav'">
                    If you'd like to review your current greeting, press 1. 
                    To re-record your greeting, press 2.
                    Or, to hear the standard greeting, press 3.
                </audio>
            </prompt>
            <prompt cond="!playIntro">>
                <audio expr="audioDir + 'AllRightStartOverGreeting.wav'">
                    All right, we'll start over.
                    If you'd like to review your current greeting, press 1. 
                    To re-record your greeting, press 2.
                    Or, to hear the standard greeting, press 3.
                </audio>
            </prompt>
            <filled> 
            <if cond="choice=='review'"> 
                <% if (greetingType == Mailbox.GREETING_TYPE_DEFAULT) {%>
                    <goto next="#ReviewStandard"/>
                <%} else if (greetingType == Mailbox.GREETING_TYPE_GREETING) {%>
                <prompt>
                    <audio expr="'<%=mailbox.getRecordedGreeting(greetingPath)%>'">
                        <%=mailbox.getRecordedGreeting(greetingPath)%>
                    </audio>
                </prompt>
                <%} else {%>
                    <prompt>
                        <audio expr="audioDir + 'ReachedVoicemail.wav'">
                            You've reached the voicemail for 
                        </audio>
                        
                        <audio expr="'<%=mailbox.getRecordedName(greetingPath)%>'">
                            <%=mailbox.getRecordedName(greetingPath)%>
                        </audio>
                        
                        <audio expr="audioDir + 'LeaveMessageAfterTone.wav'">
                            Please leave a message after the tone.  When 
                            you're done, you can hang up or press pound for more options.
                        </audio>
                        <break time="1000"/>
                    </prompt>
                <%}%>
                <goto next="#GreetingIntro"/>
                
            <elseif cond="choice =='rerecord'"/>
                <goto next="#RecordCustom"/>
            
            <else/> 
                <goto next="#ReviewStandard"/>
            </if> 
            </filled>
        </field>
    </form>
    
    <form id="ReviewStandard">
        <block name="ReviewBlock" >
            <prompt>
                <break time="1000"/>
                <audio expr="audioDir + 'StandardGreeting.wav'">
                    The person you called is not available.  Please leave a message
                    after the tone.  When you're done, you can hang up or press pound for
                    more options.
                </audio>
            </prompt>
        </block>
        <field name="choice" >
            <grammar src="grammars/ConfirmStandard.xml" type="application/grammar-xml"/> 
            <prompt>
                <audio expr="audioDir + 'ChooseGreeting.wav'">
                    To choose that as your greeting, press 1.
                    To record your name to use with that greeting, press 2.
                    Or, for a custom greeting press 3.
                </audio>
            </prompt>
            
            <filled> 
            <if cond="choice=='save'"> 
                <assign name="greetingType" expr="'standard'" />
                <submit next="greeting.do" namelist="cmd greetingType mailboxId" method="post" enctype="multipart/form-data" fetchhint="safe"/>
                
            <elseif cond="choice =='name'"/>
                <goto next="#RecordName"/>
            
            <else/> 
                <goto next="#RecordCustom"/>
            </if> 
            
            </filled>
        </field>
    </form>
    
    <form id="RecordName">
        <block name="RecordNameIntro">
            <prompt>
                <audio expr="audioDir + 'RecordName.wav'">
                    Record your name after the tone.  Press pound when you're done.
                </audio>
            </prompt>
        </block>

        <record name="RecordedName" beep="true" maxtime="120s" dtmfterm="true" finalsilence="4s" type="audio/x-wav" > 
            <filled>
                <assign name="recording" expr="RecordedName"/>
            </filled>
        </record>

        <block name="ConfirmRecordedName">
            <prompt>
                <audio expr="audioDir + 'ReviewGreetingWithName.wav'">
                    To review the greeting with your name, press 1.
                    To re-record your name, press 2.
                    Or, to cancel, press 3.
                </audio>
            </prompt>  
        </block>

        <field name="confirm">
            <property name="timeout" value="3000ms"/>
            <grammar src="grammars/ConfirmName.xml" type="application/grammar-xml"/>
            
            <filled>
                
                <log expr="'******************************'"/>
                <log expr=" '***** CONFIDENCE = ' + confirm$.confidence + '*****'"/>
                <log expr=" '***** SELECTED VALUE = ' + confirm$.interpretation.confirm + '*****'"/>
                <log expr="'******************************'"/>
                
                <if cond ="confirm =='review'">
                    <log expr=" '***** Got to review *****'"/>
                    <prompt>
                        <audio expr="audioDir + 'HeresStandardGreetingWithName.wav'">
                            Here's the standard greeting with your name.
                        </audio>
                        <break time="1000"/>
                         <audio expr="audioDir + 'ReachedVoicemail.wav'">
                            You've reached the voicemail for 
                        </audio>
                        <value expr="recording"/> 
                        <audio expr="audioDir + 'LeaveMessageAfterTone.wav'">
                            Please leave a message after the tone.  When 
                            you're done, you can hang up or press pound for more options.
                        </audio>
                        <break time="1000"/>
                    </prompt>
                    
                    <goto next="#ConfirmNameGreeting"/>
                    
                <elseif cond ="confirm =='rerecord'"/>
                    <clear namelist="recording RecordedName confirm ConfirmRecordedName" />
                    <prompt>
                        <audio expr="audioDir + 'RecordNamePlease.wav'">
                           Please record your name after the tone.  Press pound
                           when you're done.
                        </audio>
                    </prompt>
                    <goto nextitem="RecordedName"/>
                    
                <elseif cond="confirm =='cancel'"/>
                    <assign name="playIntro" expr="false" />
                    <goto next="#GreetingIntro"/>
                    
                <else/>
                     <prompt>
                        <audio expr="audioDir + 'SaveOptions2.wav'">
                          Should not get here.
                        </audio>
                    </prompt>
                </if>
                
            </filled>
        </field>
    </form>
    
    <form id="RecordCustom">
        <block name="RecordCustomIntro">
            <prompt>
                <audio expr="audioDir + 'RecordGreeting.wav'">
                    Please record your greeting after the tone.
                    Press pound when you're done.
                </audio>
            </prompt>
        </block>

        <record name="RecordedCustom" beep="true" maxtime="120s" dtmfterm="true" finalsilence="4s" type="audio/x-wav" > 
            <filled>
                <assign name="recording" expr="RecordedCustom"/>
            </filled>
        </record>

        <block name="ConfirmCustom">
            <prompt>
                <audio expr="audioDir + 'SaveCustomGreeting.wav'">
                    To save your greeting, press 1.
                    To review it, press 2.
                    To re-record your name, press 3.
                    Or, to cancel, press 4.
                </audio>
            </prompt>  
        </block>

        <field name="confirm">
            <property name="timeout" value="3000ms"/>
            <grammar src="grammars/ConfirmCustom.xml" type="application/grammar-xml"/>
            
            <filled>
                
                <log expr="'******************************'"/>
                <log expr=" '***** CONFIDENCE = ' + confirm$.confidence + '*****'"/>
                <log expr=" '***** SELECTED VALUE = ' + confirm$.interpretation.confirm + '*****'"/>
                <log expr="'******************************'"/>
                
                <if cond ="confirm =='save'">
                    <assign name="greetingType" expr="'custom'" />
                    <submit next="greeting.do" namelist="recording cmd greetingType mailboxId" method="post" enctype="multipart/form-data" fetchhint="safe"/>
                    
                <elseif cond ="confirm =='review'"/>
                    <log expr=" '***** Got to review *****'"/>
                    <value expr="recording"/>
                    
                    <goto next="#ConfirmCustomGreeting"/>
                    
                <elseif cond ="confirm =='rerecord'"/>
                    <clear namelist="recording RecordedCustom confirm ConfirmCustom" />
                    <prompt>
                        <audio expr="audioDir + 'RecordGreeting.wav'">
                           Please record your greeting after the tone.
                           Press pound when you're done.
                        </audio>
                    </prompt>
                    <goto nextitem="RecordedCustom"/>
                    
                <elseif cond="confirm =='cancel'"/>
                    <goto next="#Cancel"/>
                    
                <else/>
                     <prompt>
                        <audio expr="audioDir + 'SaveOptions2.wav'">
                          Should not get here.
                        </audio>
                    </prompt>
                </if>
                
            </filled>
        </field>
    </form>
    
    <form id="ConfirmNameGreeting">
        <field name="confirm" >
            <grammar src="grammars/ConfirmName.xml?t=1" type="application/grammar-xml"/> 
            <prompt>
                <audio expr="audioDir + 'ChooseStandardGreeting.wav'">
                    To choose that as your greeting, press 1.
                    To re-record your name, press 2.
                    Or, to cancel, press 3.
                </audio>
            </prompt>
            
            <filled> 
            <if cond="confirm=='review'"> 
                <assign name="greetingType" expr="'name'" />
                <submit next="greeting.do" namelist="recording cmd greetingType mailboxId" method="post" enctype="multipart/form-data" fetchhint="safe"/>
                
            <elseif cond="confirm =='rerecord'"/>
                <goto next="#RecordName"/>
            
            <else/> 
                <assign name="playIntro" expr="false" />
                <goto next="#GreetingIntro"/>
            </if> 
            </filled>
        </field>
    </form>
    
    <form id="ConfirmCustomGreeting">
        <field name="confirm" >
            <grammar src="grammars/ConfirmCustom.xml" type="application/grammar-xml"/> 
            <prompt>
                <audio expr="audioDir + 'SaveCustomGreetingAgain.wav'">
                    To save your greeting, press 1.
                    To hear it again, press 2.
                    To re-record it, press 3.
                    Or, to cancel, press 4.
                </audio>
            </prompt>
            
            <filled> 
            <if cond="confirm=='save'">
                <assign name="greetingType" expr="'custom'" />
                <submit next="greeting.do" namelist="recording cmd greetingType mailboxId" method="post" enctype="multipart/form-data" fetchhint="safe"/>
                
            <elseif cond="confirm =='review'"/>
                <prompt>
                    <value expr="recording"/> 
                </prompt>
                <goto next="#ConfirmCustomGreeting"/>
                
            <elseif cond="confirm =='rerecord'"/>
                <goto next="#RecordCustom"/>
            
            <else/> 
                <assign name="playIntro" expr="false" />
                <goto next="#GreetingIntro"/>
            </if> 
            </filled>
        </field>
    </form>
    
   
</vxml>