package io.signal.spi;

import reactor.core.publisher.Flux;

public interface SignalTransformer<T> {

    Class<T> getInputMessageClass();

    String getOutputChannelName();

    Object transform(T inputMessage);

    default Channel connect(Channel channel) {
        Flux<Signal<?>> transmission = channel.getTransmission()
                .filter(signal -> getInputMessageClass().isAssignableFrom(signal.getMessage().getClass()))
                .map(signal -> new Signal<>(signal.getId(), transform(getInputMessageClass().cast(signal.getMessage()))));

        return new Channel(getOutputChannelName(), transmission);
    }

}
