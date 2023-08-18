package io.signal;

import io.signal.spi.Channel;
import io.signal.spi.Signal;
import io.signal.spi.SignalReceiver;

public abstract class AbstractSignalReceiver<T> implements SignalReceiver<T> {

    @Override
    public boolean filter(Signal<T> signal) {
        return true;
    }

    @Override
    public void tune(Channel channel) {
        channel.getTransmission()
                .filter(signal -> getMessageClass().isAssignableFrom(signal.getMessage().getClass()))
                .map(signal -> new Signal<>(signal.getId(), getMessageClass().cast(signal.getMessage())))
                .filter(this::filter)
                .subscribe(this::receive);
    }

}
