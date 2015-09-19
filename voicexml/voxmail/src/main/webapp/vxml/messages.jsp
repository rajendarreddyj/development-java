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
Messages messages = (Messages) request.getAttribute("messages");
String folder = (String) request.getAttribute("folder");
ArrayList msgs = new ArrayList();

if (folder != null && folder.equals("saved"))
{
    //Saved messages
    ArrayList newMessages = messages.getSavedMessages();
    msgs = newMessages;
}
else
{
    folder="new";
    //New/Skipped messages
    ArrayList newMessages = messages.getNewMessages();
    ArrayList skippedMessages = messages.getSkippedMessages();
    newMessages.addAll(skippedMessages);
    msgs = newMessages;
}

Iterator msgIt = msgs.iterator();
int newMessageCount = messages.getNewMessages().size();
int skippedMessageCount = messages.getSkippedMessages().size();
int totalNewCount = newMessageCount + skippedMessageCount;
int savedMessageCount = messages.getSavedMessages().size();
String messagePath = (String) request.getAttribute("messagePath");
System.out.println("messages.jsp mailboxId: " + mailbox.getMailboxId() + ", new: " + newMessageCount + ", skipped: " + skippedMessageCount + ", saved: " + savedMessageCount);

boolean playedFirstNew = false;
boolean playedFirstSkipped = false;
boolean hasSkipped = false;
if (skippedMessageCount > 0 && folder.equals("new")) {
    hasSkipped = true;
}



%>
<?xml version="1.0" encoding="UTF-8"?>
<vxml version="2.0" application="<%=request.getContextPath()%>/vxml/approot.jsp">
    
    <var name="mailboxId" expr="'<%=mailbox.getMailboxId()%>'" />
    <var name="cmd" />
    <var name="msgNum" />
    <var name="updateMessage"/>
    <var name="playIntro" expr="true" />
    
    <form id="MessageIntro">
        <block name="Intro">
            <prompt>
                <% if (folder.equals("new")) {%>
                
                <% if (skippedMessageCount == 0) {%>
                    <% if (newMessageCount <= 20) {%>
                    <audio expr="audioDir + '<%=newMessageCount%>_NewMessages.wav'">
                        You have <%=newMessageCount%> new messages.
                    </audio>
                    <%} else {%>
                    <audio expr="audioDir + 'YouHave.wav'">
                        You have <%=newMessageCount%> new messages.
                    </audio>
                    <audio expr="audioDir + '<%=newMessageCount%>.wav'" />
                    <audio expr="audioDir + 'NewMessages.wav'" />
                    <%}%>
                
                <%} else if (skippedMessageCount > 0) {%>
                    
                    <% if (newMessageCount <= 20) {%>
                    <audio expr="audioDir + '<%=newMessageCount%>_NewMessagesConcat.wav'">
                        You have <%=newMessageCount%> new messages.
                    </audio>
                    <%} else {%>
                    <audio expr="audioDir + 'YouHave.wav'">
                        You have <%=newMessageCount%> new messages.
                    </audio>
                    <audio expr="audioDir + '<%=newMessageCount%>.wav'" />
                    <audio expr="audioDir + 'NewMessages.wav'" />
                    <%}%>
                    
                    <audio expr="audioDir + '<%=skippedMessageCount%>_SkippedMessages.wav'">
                        and <%=skippedMessageCount%> skipped messages.
                    </audio>
                <%}%>
                <%} else {
                    playedFirstNew = false;
                %>
                    <% if (savedMessageCount <= 20) {%>
                    <audio expr="audioDir + '<%=savedMessageCount%>_SavedMessages.wav'">
                        You have <%=savedMessageCount%> saved messages.
                    </audio>
                    <%} else {%>
                    <audio expr="audioDir + 'YouHave.wav'">
                        You have <%=savedMessageCount%> saved messages.
                    </audio>
                    <audio expr="audioDir + '<%=savedMessageCount%>.wav'" />
                    <audio expr="audioDir + 'SavedMessages.wav'" />
                    <%}%>
                <%}%>
            </prompt>
            <goto next="#Message0"/>
        </block>
    </form>
    
    <%  int i =0;
    while (msgIt.hasNext()) {
        MessageWrapper mw = (MessageWrapper) msgIt.next(); 
        String imapFileUrl = "play.do;jsessionid=" + session.getId() + "?msgindex=" + mw.getMessageNumber();
        System.out.println("Imap url: " + imapFileUrl);
        %>
    
    <form id="Message<%=i%>">
        <block name="Intro">
            <assign name="playIntro" expr="true" />
            <assign name="skipIntro" expr="false" />
            <prompt>
                
                
                <% 
                int type = MessageWrapper.getStartMessageType(playedFirstNew, playedFirstSkipped, mw, hasSkipped);
                System.out.println("Message: " + i + ", play type: " + type + ", playedFirstNew: " + playedFirstNew + ", playedFirstSkipped: " + playedFirstSkipped + ", hasSkipped: " + hasSkipped);
                if (type == MessageWrapper.PLAY_FIRST_MESSAGE) {
                    playedFirstNew = true;    
                %>
                <% if (folder.equals("new")) {%>
                <% if (mw.isToday()) {%>
                    <audio expr="audioDir + 'FirstMessageToday.wav'">
                        First message received today at
                    </audio>
                    <audio>
                        <%=mw.getMessageDateText()%>
                    </audio>
                <%} else if (mw.isYesterday()) { %>
                    <audio expr="audioDir + 'FirstMessageYesterday.wav'">
                        First message received yesterday at
                    </audio>
                    <audio>
                        <%=mw.getMessageDateText()%>
                    </audio>
                <%} else { %>
                    <audio expr="audioDir + 'FirstMessageOn.wav'">
                        First message received on
                    </audio>
                    <audio expr="audioDir + '<%=mw.getMonth()%>.wav'">
                        <%=mw.getMonth()%>
                    </audio>
                    <audio expr="audioDir + '<%=mw.getOrdinalDay()%>.wav'">
                        <%=mw.getOrdinalDay()%>
                    </audio>
                <%}%>
                <%} else {%>
                    <audio expr="audioDir + 'FirstMessage.wav'">
                        First message
                    </audio>
                <%}%>
                
                <%} else if (type == MessageWrapper.PLAY_FIRST_NEW_MESSAGE) {
                    playedFirstNew = true;    
                %>
                    <% if (folder.equals("new")) {%>
                    <% if (mw.isToday()) {%>
                        <audio expr="audioDir + 'FirstMessageToday.wav'">
                            First new message received today at
                        </audio>
                        <audio>
                            <%=mw.getMessageDateText()%>
                        </audio>
                    <%} else if (mw.isYesterday()) { %>
                        <audio expr="audioDir + 'FirstMessageYesterday.wav'">
                            First new message received yesterday at
                        </audio>
                        <audio>
                            <%=mw.getMessageDateText()%>
                        </audio>
                    <%} else { %>
                        <audio expr="audioDir + 'FirstMessageOn.wav'">
                            First new message received on
                        </audio>
                        <audio expr="audioDir + '<%=mw.getMonth()%>.wav'">
                            <%=mw.getMonth()%>
                        </audio>
                        <audio expr="audioDir + '<%=mw.getOrdinalDay()%>.wav'">
                            <%=mw.getOrdinalDay()%>
                        </audio>
                    <%}%>
                    <%} else {%>
                        <audio expr="audioDir + 'FirstMessage.wav'">
                            First message
                        </audio>
                    <%}%>
                
                <%} else if (type == MessageWrapper.PLAY_FIRST_SKIPPED) {
                    playedFirstSkipped = true;    
                %>
                    <% if (folder.equals("new")) {%>
                        <% if (mw.isToday()) {%>
                            <audio expr="audioDir + 'FirstSkippedMessageToday.wav'">
                                First skipped message received today at
                            </audio>
                            <audio>
                                <%=mw.getMessageDateText()%>
                            </audio>
                        <%} else if (mw.isYesterday()) { %>
                            <audio expr="audioDir + 'FirstSkippedMessageYesterday.wav'">
                                First skipped message received yesterday at
                            </audio>
                            <audio>
                                <%=mw.getMessageDateText()%>
                            </audio>
                        <%} else { %>
                            <audio expr="audioDir + 'FirstSkippedMessageOn.wav'">
                                First skipped message received on
                            </audio>
                            <audio expr="audioDir + '<%=mw.getMonth()%>.wav'">
                                <%=mw.getMonth()%>
                            </audio>
                            <audio expr="audioDir + '<%=mw.getOrdinalDay()%>.wav'">
                                <%=mw.getOrdinalDay()%>
                            </audio>
                        <%}%>
                    <%} else {%>
                        <audio expr="audioDir + 'FirstSkippedMessage.wav'">
                            First skipped message
                        </audio>
                    <%}%>

                <%} else if (type == MessageWrapper.PLAY_NEXT_MESSAGE) {%>
                    <% if (folder.equals("new")) {%>
                        <% if (mw.isToday()) {%>
                            <audio expr="audioDir + 'NextMessageToday.wav'">
                                Next message received today at
                            </audio>
                            <audio>
                                <%=mw.getMessageDateText()%>
                            </audio>
                        <%} else if (mw.isYesterday()) { %>
                            <audio expr="audioDir + 'NextMessageYesterday.wav'">
                                Next message received yesterday at
                            </audio>
                            <audio>
                                <%=mw.getMessageDateText()%>
                            </audio>
                        <%} else { %>
                            <audio expr="audioDir + 'NextMessageOn.wav'">
                                Next skipped message received on
                            </audio>
                            <audio expr="audioDir + '<%=mw.getMonth()%>.wav'">
                                <%=mw.getMonth()%>
                            </audio>
                            <audio expr="audioDir + '<%=mw.getOrdinalDay()%>.wav'">
                                <%=mw.getOrdinalDay()%>
                            </audio>
                        <%}%>
                     <%} else {%>
                        <audio expr="audioDir + 'NextMessage.wav'">
                            Next Message
                        </audio>
                    <%}%>
                <%}%>
                
            </prompt>
        </block>
        <block name="MessageReadback">
            <prompt>
                <% if (WebUtil.isEmpty(mw.getAudioFileName(messagePath)))
                {%>
                    <audio expr="'<%=imapFileUrl%>'">
                        Imap Audio file not found.
                    </audio>
                <%} else {%>
                    <audio expr="'<%=mw.getAudioFileName(messagePath)%>'">
                        Audio file not found.
                    </audio>
                <%}%>
                
            </prompt>
        </block>
        
        <field name="choice">
            
            <property name="timeout" value="3000ms"/>
            <grammar src="grammars/MessageOptions.xml?t=1" type="application/grammar-xml"/>
            <% if (folder.equals("new")) {%>
            <prompt cond="playIntro">
                <audio expr="audioDir + 'ToDelete.wav'">
                    To delete this message, press 7.
                    To hear it again, press 8.
                    Or, to save it, press 9.
                    For more options, press 0.
                </audio>
            </prompt>
            <%} else {%>
            <prompt cond="playIntro">
                <audio expr="audioDir + 'ToDeleteSaved.wav'">
                    To delete this message, press 7.
                    To hear it again, press 8.
                    Or, to continue saving it, press 9.
                    For more options, press 0.
                </audio>
            </prompt>
            <%}%>
            
            <filled>
                <log expr="'******************************'"/>
                <log expr=" '***** SELECTED VALUE = ' + choice$.interpretation.choice + '*****'"/>
                <log expr="'******************************'"/>
                
                <if cond="choice=='delete'">
                    
                    <assign name="cmd" expr="'delete'" />
                    <assign name="msgNum" expr="'<%=mw.getMessageNumber()%>'" />
                    <data name="updateMessage" src="updateMessage.do" namelist="msgNum cmd" method="post"/>
                    <prompt>
                        <audio expr="audioDir + 'MessageDeleted.wav'">
                            Message deleted.
                        </audio>
                    </prompt>
                    <goto next="#Message<%=i+1%>"/>
                    
                <elseif cond ="choice =='repeat'"/>
                    <clear namelist="choice"/>
                    <goto nextitem="MessageReadback" />
                    
                <elseif cond ="choice =='save'"/>
                    <assign name="cmd" expr="'save'" />
                    <assign name="msgNum" expr="'<%=mw.getMessageNumber()%>'" />
                    <data name="updateMessage" src="updateMessage.do" namelist="msgNum cmd" method="post"/>
                    <prompt>
                        <audio expr="audioDir + 'MessageSaved.wav'">
                            Message saved.
                        </audio>
                    </prompt>
                    <goto next="#Message<%=i+1%>"/>
                    
                <elseif cond="choice =='options'"/>
                    <clear namelist="choice"/>
                    <goto nextitem="Options"/>
                <elseif cond="choice =='info'"/>
                    <clear namelist="choice"/>
                    <goto nextitem="Info"/>    
                <elseif cond="choice =='skip'"/>
                    <assign name="cmd" expr="'skipped'" />
                    <assign name="msgNum" expr="'<%=mw.getMessageNumber()%>'" />
                    <%if (folder.equals("new")) { %>
                        <data name="updateMessage" src="updateMessage.do" namelist="msgNum cmd" method="post"/>
                    <%}%>
                    <goto next="#Message<%=i+1%>"/>
                <else/>
                    <prompt>
                        <audio expr="audioDir + 'SaveOptions2.wav'">
                            Should not get here.
                        </audio>
                    </prompt>
                </if>
                
            </filled>
            <noinput > 
                <assign name="cmd" expr="'skipped'" />
                <assign name="msgNum" expr="'<%=mw.getMessageNumber()%>'" />
                <data name="updateMessage" src="updateMessage.do" namelist="msgNum cmd" method="post"/>
                <goto next="#Message<%=i+1%>"/>
            </noinput>
        </field>
        
        <block name="Options">
            <assign name="playIntro" expr="false" />
            <prompt>
                <audio expr="audioDir + 'HearOptions.wav'">
                    Here are all your options when listening to messages.
                    Press 7 to delete, 8 to hear it again, or 9 to save it.
                    To hear message info, like date received, press 5.
                    To skip ahead to the next message at any time,
                    press 6.
                </audio>
            </prompt>
            <goto nextitem="choice"/>
        </block>
        
        <block name="Info">
            <prompt>
                <audio expr="audioDir + 'MessageReceivedOn.wav'">
                    This message was received on 
                </audio>
                
                <audio expr="audioDir + '<%=mw.getMonth()%>.wav'">
                    <%=mw.getMonth()%>
                </audio>
                <audio expr="audioDir + '<%=mw.getOrdinalDay()%>.wav'">
                    <%=mw.getOrdinalDay()%>
                </audio>
                <audio >
                   <%=mw.getAtTimeText()%>
                </audio>
                    
                <audio expr="audioDir + 'FromPhoneNumber.wav'">
                    From phone number
                </audio>
                <audio>
                    <%=mw.getCallerIdText()%>
                </audio>
            </prompt>
            <prompt>
                <% if (folder.equals("new")) {%>
                <audio expr="audioDir + 'ToDelete.wav'">
                    To delete this message, press 7.
                    To hear it again, press 8.
                    Or, to save it, press 9.
                </audio>
                <%} else {%>
                <audio expr="audioDir + 'ToDeleteSaved.wav'">
                    To delete this message, press 7.
                    To hear it again, press 8.
                    Or, to continue saving it, press 9.
                </audio>
                <%}%>
            </prompt>
            
            <assign name="playIntro" expr="false" />
            <goto nextitem="choice"/>
        </block>
    </form>
    
    <%
    i++;
    } //end while iterator%>
    
    
    <form id="Message<%=i%>">
        
        <field name="choice">
            
            <property name="timeout" value="3000ms"/>
            <% if (folder.equals("new")){%>
            <grammar src="grammars/PostMessageOptions.xml" type="application/grammar-xml"/>
            <%} else {%>
            <grammar src="grammars/PostSavedMessageOptions.xml" type="application/grammar-xml"/>
            <%}%>
            
            <prompt>
                <% if (folder.equals("new")) {%>
                    <% if (totalNewCount > 0) {%>
                    <audio expr="audioDir + 'AllNewMessages.wav'">
                        That's all your messages.
                        To check saved messages, press 1.
                        To change your greeting, press 2.
                        Or if you're done, press 3.
                    </audio>
                    <%} else {%>
                    <audio expr="audioDir + 'CheckSavedMsgs.wav'">
                        To check saved messages, press 1.
                        To change your greeting, press 2.
                        Or if you're done, press 3.
                    </audio>
                    
                    <%}%>
                <%} else {%>
                    <% if (savedMessageCount > -1) {%>
                    <audio expr="audioDir + 'ThatsAllSaved.wav'">
                        That's all your saved messages.
                        To change your greeting, press 1.
                        Or, if you're done, press 2.
                    </audio>
                    <%}%>
                <%}%>
            </prompt>
            
            <filled>
                <log expr="'******************************'"/>
                <log expr=" '***** SELECTED VALUE = ' + choice$.interpretation.choice + '*****'"/>
                <log expr="'******************************'"/>
                
                <if cond="choice=='saved'">
                    <assign name="cmd" expr="'saved'" />
                    <submit next="mainMenu.do" namelist="cmd" method="post" /> 
                
                <elseif cond ="choice =='greeting'"/>
                    <assign name="cmd" expr="'changeGreeting'" />
                    <submit next="greeting.do" namelist=" cmd " method="post" />
                    
                <elseif cond ="choice =='done'"/>
                    
                    <prompt>
                        <audio expr="audioDir + 'Goodbye.wav'">
                            Goodbye!
                        </audio>
                    </prompt>
                    <disconnect/>
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
    
    <form id="NoMessages">
        <block>
            <prompt>
                <audio expr="audioDir + 'Goodbye.wav'">
                    Your mailbox is empty.
                </audio>
            </prompt>
        </block>
    </form>
</vxml>