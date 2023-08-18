package io.signal;

import io.signal.spi.Channel;
import io.signal.spi.Signal;
import io.signal.spi.SignalReceiver;

import java.util.concurrent.CompletableFuture;

public class BlockingSignalReceiver<T> implements SignalReceiver<T> {

    private CompletableFuture<Signal<T>> future;

    private final Class<T> messageClass;

    public BlockingSignalReceiver(Class<T> messageClass) {
        this.messageClass = messageClass;
    }

    @Override
    public Class<T> getMessageClass() {
        return messageClass;
    }

    @Override
    public void receive(Signal<T> signal) {
        throw new UnsupportedOperationException("Use method waitForSignal instead of receive");
    }

    @Override
    public void tune(Channel channel) {
        future = channel.getTransmission()
                .filter(signal -> getMessageClass().isAssignableFrom(signal.getMessage().getClass()))
                .map(signal -> new Signal<>(signal.getId(), getMessageClass().cast(signal.getMessage())))
                .take(1)
                .single()
                .toFuture();
    }

    public Signal<T> waitForSignal() {
        return future.join();
    }
}
