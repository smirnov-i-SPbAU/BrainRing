package ru.spbhse.brainring.network.messages.messageTypes;

import android.support.annotation.NonNull;

import ru.spbhse.brainring.network.messages.MessageCodes;
import ru.spbhse.brainring.network.messages.MessageGenerator;
import ru.spbhse.brainring.network.messages.Message;

public class CorrectAnswerMessage extends Message {
    public CorrectAnswerMessage() {
        super(MessageCodes.CORRECT_ANSWER);
    }

    @Override
    protected byte[] toByteArray(@NonNull MessageGenerator generator) {
        return generator.toByteArray();
    }
}
