package com.rajendarreddyj.rivrclient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.nuecho.rivr.core.channel.synchronous.step.Step;
import com.nuecho.rivr.core.util.Duration;
import com.nuecho.rivr.voicexml.dialogue.VoiceXmlDialogueContext;
import com.nuecho.rivr.voicexml.test.VoiceXmlTestDialogueChannel;
import com.nuecho.rivr.voicexml.turn.first.VoiceXmlFirstTurn;
import com.nuecho.rivr.voicexml.turn.last.VoiceXmlLastTurn;
import com.nuecho.rivr.voicexml.turn.output.VoiceXmlOutputTurn;
import com.nuecho.rivr.voicexml.util.json.JsonUtils;

/**
 * @author rajendarreddy
 */
public class DialogueTests {
    private VoiceXmlTestDialogueChannel mDialogueChannel;

    @Before
    public void init() {
        this.mDialogueChannel = new VoiceXmlTestDialogueChannel("Dialog Tests", Duration.seconds(5));
    }

    @Test
    public void test() {
        this.mDialogueChannel.dumpLogs();
        this.startDialogue(new VoiceXmlFirstTurn());

        this.mDialogueChannel.processValue(JsonUtils.createObjectBuilder().add("clid", "5145551234").add("dnis", "5551234").build());

    }

    private Step<VoiceXmlOutputTurn, VoiceXmlLastTurn> startDialogue(final VoiceXmlFirstTurn firstTurn) {
        HelloWorldDialogue dialogue = new HelloWorldDialogue();
        VoiceXmlDialogueContext context = new VoiceXmlDialogueContext(this.mDialogueChannel, LoggerFactory.getLogger(this.getClass()), "x", "contextPath",
                "servletPath");
        return this.mDialogueChannel.startDialogue(dialogue, firstTurn, context);
    }

    @After
    public void terminate() {
        this.mDialogueChannel.dispose();
    }
}
