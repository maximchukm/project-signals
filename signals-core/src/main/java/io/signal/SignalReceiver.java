package io.signal;

/**
 * @author Maxim Maximchuk
 * created on 08.02.2019
 */
public interface SignalReceiver<T> {

    Class<T> getMessageClass();

    void receive(Signal<T> signal);

    default void tune(Channel channel) {
        channel.getTransmission()
                .filter(signal -> getMessageClass().isAssignableFrom(signal.getMessage().getClass()))
                .subscribe(signal ->
                        receive(
                                new Signal<>(signal.getId(), getMessageClass().cast(signal.getMessage()))
                        )
                );
    }

}
