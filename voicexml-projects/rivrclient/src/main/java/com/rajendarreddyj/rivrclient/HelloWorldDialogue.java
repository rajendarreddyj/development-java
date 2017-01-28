package com.rajendarreddyj.rivrclient;

import static com.nuecho.rivr.voicexml.turn.input.VoiceXmlEvent.CONNECTION_DISCONNECT_HANGUP;
import static com.nuecho.rivr.voicexml.turn.input.VoiceXmlEvent.ERROR;
import static com.nuecho.rivr.voicexml.turn.input.VoiceXmlEvent.hasEvent;
import static com.nuecho.rivr.voicexml.turn.output.OutputTurns.interaction;
import static com.nuecho.rivr.voicexml.util.json.JsonUtils.wrap;

import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuecho.rivr.core.dialogue.DialogueUtils;
import com.nuecho.rivr.voicexml.dialogue.VoiceXmlDialogue;
import com.nuecho.rivr.voicexml.dialogue.VoiceXmlDialogueContext;
import com.nuecho.rivr.voicexml.turn.VariableList;
import com.nuecho.rivr.voicexml.turn.first.VoiceXmlFirstTurn;
import com.nuecho.rivr.voicexml.turn.input.VoiceXmlInputTurn;
import com.nuecho.rivr.voicexml.turn.last.Exit;
import com.nuecho.rivr.voicexml.turn.last.VoiceXmlLastTurn;
import com.nuecho.rivr.voicexml.turn.output.Interaction;
import com.nuecho.rivr.voicexml.turn.output.audio.SpeechSynthesis;
import com.nuecho.rivr.voicexml.util.ResultUtils;
import com.nuecho.rivr.voicexml.util.json.JsonUtils;

public class HelloWorldDialogue implements VoiceXmlDialogue {
    private static final String CAUSE_PROPERTY = "cause";
    private final Logger mDialogueLog = LoggerFactory.getLogger("hello-world");

    @Override
    public VoiceXmlLastTurn run(final VoiceXmlFirstTurn firstTurn, final VoiceXmlDialogueContext context) throws Exception {
        // The dialogue termination cause. "Normal" by default.
        JsonValue cause = wrap("Normal");

        this.mDialogueLog.info("Starting dialogue");
        try {
            // Play a prompt
            Interaction turn = interaction("hello").addPrompt(new SpeechSynthesis("Hello World!")).build();
            VoiceXmlInputTurn inputTurn = DialogueUtils.doTurn(turn, context);

            // Handling hangup or error events
            if (hasEvent(CONNECTION_DISCONNECT_HANGUP, inputTurn.getEvents())) {
                cause = wrap("Hangup");
            }
            if (hasEvent(ERROR, inputTurn.getEvents())) {
                cause = wrap(inputTurn.getEvents().get(0).getMessage());
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            cause = wrap("Interrupted");
        } catch (Exception exception) {
            this.mDialogueLog.error("Error during dialogue execution", exception);
            cause = ResultUtils.toJson(exception);
        }
        this.mDialogueLog.info("Ending dialogue");

        // Build the JSON result returned to the calling application/context.
        JsonObjectBuilder resultObjectBuilder = JsonUtils.createObjectBuilder();
        resultObjectBuilder.add(CAUSE_PROPERTY, cause);
        VariableList variables = VariableList.create(resultObjectBuilder.build());

        return new Exit("result", variables);
    }

}
