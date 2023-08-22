package io.signal.broadcast.example;

import io.signal.spi.SignalTransformer;

public class MultiplyingTransformer implements SignalTransformer<Integer> {

    private final Integer factor;

    private final String outputChannelName;

    public MultiplyingTransformer(Integer factor, String outputChannelName) {
        this.factor = factor;
        this.outputChannelName = outputChannelName;
    }

    @Override
    public Class<Integer> getInputMessageClass() {
        return Integer.class;
    }

    @Override
    public String getOutputChannelName() {
        return outputChannelName;
    }

    @Override
    public Object transform(Integer inputMessage) {
        return inputMessage * factor;
    }
}
