package io.signal;

import io.signal.spi.Signal;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class DefaultSignalReceiver<T> extends AbstractSignalReceiver<T> {

    private final Class<T> messageClass;
    private final Consumer<Signal<T>> signalConsumer;

    private Predicate<Signal<T>> filter = signal -> true;

    public DefaultSignalReceiver(Class<T> messageClass, Consumer<Signal<T>> signalConsumer) {
        this.messageClass = messageClass;
        this.signalConsumer = signalConsumer;
    }

    public DefaultSignalReceiver<T> withFilter(Predicate<Signal<T>> filter) {
        this.filter = filter;
        return this;
    }

    @Override
    public boolean filter(Signal<T> signal) {
        return filter.test(signal);
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
