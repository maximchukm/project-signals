package io.signal.plain.application.example.transformer;

import io.signal.spi.Signal;
import io.signal.spi.SignalTransformer;
import io.signal.plain.application.example.message.CustomMessage;

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
    public Signal<Object> transform(Signal<CustomMessage> inputSignal) {
        return Signal.message(inputSignal.getMessage().getContent());
    }
}
