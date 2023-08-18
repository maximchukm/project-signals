package io.signal;

import io.signal.spi.Signal;

import java.util.function.Consumer;

public class DefaultSignalReceiver<T> extends AbstractSignalReceiver<T> {

    private final Class<T> messageClass;
    private final Consumer<Signal<T>> signalConsumer;

    public DefaultSignalReceiver(Class<T> messageClass, Consumer<Signal<T>> signalConsumer) {
        this.messageClass = messageClass;
        this.signalConsumer = signalConsumer;
    }

    @Override
    public Class<T> getMessageClass() {
        return messageClass;
    }

    @Override
    public void receive(Signal<T> signal) {
        signalConsumer.accept(signal);
    }

}
