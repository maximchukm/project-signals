package io.signal;

import java.util.function.Consumer;

public class DefaultSignalReceiver<T> implements SignalReceiver<T> {

    private Class<T> messageClass;
    private Consumer<Signal<T>> signalConsumer;

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

    @Override
    public void tune(Channel channel) {
        SignalReceiver.super.tune(channel);
    }

}
