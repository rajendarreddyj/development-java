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
response.setHeader("pragma", "no-cache");
response.setHeader("expires", "0");
response.setHeader("cache-control", "no-cache, must-revalidate");
String promptLocation = request.getContextPath() + "/content/";
String userPromptLocation = request.getContextPath() + "/content/";
%>

<?xml version="1.0" encoding="UTF-8"?>
<vxml version="2.0">
    
    <var name="audioDir" expr="'<%=promptLocation%>'"/>    
    <var name="userAudioDir" expr="'<%=userPromptLocation%>'"/>    
    
    
    <!-- *** SETUP GLOBAL CATCH HANDLERS *** -->
    <catch event="error.badfetch error.semantic">
        <log expr=" '****** ERROR THROWN ******' "/>
    </catch>
    
    <noinput count="1"> 
      <reprompt/>
    </noinput> 
    <noinput count="2"> 
      <prompt>
            <audio expr="audioDir + 'ErrorSelectOption.wav'">
                Please select one of these options.
            </audio>
        </prompt>
        <reprompt/>
    </noinput> 
    <noinput count="3">
        <prompt>
            <audio expr="audioDir + 'ErrorTrouble.wav'">
                Sorry, i'm having trouble understanding.  Please try your voicemail again later.
            </audio>
        </prompt>
        <disconnect/>
    </noinput> 
    <nomatch count="1"> 
        <prompt>
            <audio expr="audioDir + 'ErrorNotValid.wav'">
                I'm sorry, that option isn't valid here.
            </audio>
        </prompt>
        <reprompt/>
    </nomatch> 
    <nomatch count="2"> 
        <prompt>
            <audio expr="audioDir + 'ErrorTryOnceMore.wav'">
                Let's try that once more.
            </audio>
        </prompt>
        <reprompt/>
    </nomatch> 
    <nomatch count="3">
        <prompt>
            <audio expr="audioDir + 'ErrorTrouble.wav'">
                Sorry, i'm having trouble understanding.  Please try your voicemail again later.
            </audio>
        </prompt>
        <disconnect/>
    </nomatch> 
    
    <!-- *** SETUP PROPERTY SETTINGS *** -->
    <!-- speed up DTMF return time-->
    <property name="inputmodes" value="'dtmf'" />
    
    <property name="interdigittimeout" value="2s"/>
    
    <!-- defines which built in utterences are allowed  -->
    <!-- note that unless we have defined help events, then the nuance default will be executed -->
    <!-- that is Bad -->
    <property name="universals" value="help"/>
    
    <!-- allows a bit more room for grammar matches -->
    <property name="confidencelevel" value="0.4"/>
    
    <!--  set the fetchaudio delay to avoid trunctuated fetchaudio filler-->
    <property name="fetchaudiodelay" value="2s"/>
    
    <!-- set the minimum duration a fetchaudio file should play-->
    <property name="fetchaudiominimum" value="3s"/>
    
    <!--  sets some 'filler' music in the event of a long fetch time -->
    <property name="fetchaudio" value="fetching.wav"/>
    
    <!--  set all fetching parameters to 'prefetch' for speed -->
    <property name="grammarfetchhint" value="prefetch"/>
    <property name="documentfetchhint" value="prefetch"/>
    <property name="audiofetchhint" value="prefetch"/>
    <property name="scriptfetchhint" value="prefetch"/>
    
    <!-- set the amount of time a fetch is allowed before throwing a badfetch -->
    <property name="fetchtimeout" value="10s"/>
    
    <!-- set up a 'null' form to prevent unexpected parse errors -->
    
    <form>
        <block>
            <log expr=" '******* LOADED THE APPROOT **********' "/>
        </block>
    </form>
</vxml>