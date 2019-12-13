package io.signal;

/**
 * @author Maxim Maximchuk
 * created on 08.02.2019
 */
public interface SignalReceiver {

    String getChannelName();

    void receive(Signal signal);

    default void tune(Channel channel) {
        channel.getTransmission().subscribe(this::receive);
    }

}
