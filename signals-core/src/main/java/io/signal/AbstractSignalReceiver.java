package io.signal;

import io.signal.spi.Channel;
import io.signal.spi.Signal;
import io.signal.spi.SignalReceiver;

public abstract class AbstractSignalReceiver<T> implements SignalReceiver<T> {

    @Override
    public void tune(Channel channel) {
        channel.getTransmission()
                .filter(signal -> getMessageClass().isAssignableFrom(signal.getMessage().getClass()))
                .subscribe(signal ->
                        receive(
                                new Signal<>(signal.getId(), getMessageClass().cast(signal.getMessage()))
                        )
                );
    }

}
