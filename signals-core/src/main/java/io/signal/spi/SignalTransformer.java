package io.signal.spi;

import reactor.core.publisher.Flux;

public interface SignalTransformer<T> {

    Class<T> getInputMessageClass();

    String getOutputChannelName();

    Signal<Object> transform(Signal<T> inputSignal);

    default Channel connect(Channel channel) {
        Flux<Signal<Object>> transmission = channel.getTransmission()
                .filter(signal -> getInputMessageClass().isAssignableFrom(signal.getMessage().getClass()))
                .map(signal -> new Signal<>(signal.getId(), getInputMessageClass().cast(signal.getMessage())))
                .map(this::transform);

        return new Channel(getOutputChannelName(), transmission);
    }

}
