package io.signal.plain.application.example.transformer;

import io.signal.plain.application.example.message.CustomMessage;
import io.signal.spi.SignalTransformer;

public class CustomMessageTransformer implements SignalTransformer<CustomMessage> {

    @Override
    public Class<CustomMessage> getInputMessageClass() {
        return CustomMessage.class;
    }

    @Override
    public String getOutputChannelName() {
        return "custom-transformed";
    }

    @Override
    public Object transform(CustomMessage inputMessage) {
        return inputMessage.getContent();
    }
}
