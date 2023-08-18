package io.signal;

import io.signal.spi.Signal;
import io.signal.spi.SignalTransformer;

import java.util.function.Function;

public class DefaultSignalTransformer<T> implements SignalTransformer<T> {

    private final Class<T> inputMessageClass;

    private final String outputChannelName;

    private final Function<T, ?> transformingFunction;

    public DefaultSignalTransformer(Class<T> inputMessageClass,
                                    String outputChannelName,
                                    Function<T, ?> messageTransformingFunction) {
        this.inputMessageClass = inputMessageClass;
        this.outputChannelName = outputChannelName;
        this.transformingFunction = messageTransformingFunction;
    }

    @Override
    public Class<T> getInputMessageClass() {
        return inputMessageClass;
    }

    @Override
    public String getOutputChannelName() {
        return outputChannelName;
    }

    @Override
    public Signal<Object> transform(Signal<T> inputSignal) {
        return new Signal<>(inputSignal.getId(), transformingFunction.apply(inputSignal.getMessage()));
    }
}
