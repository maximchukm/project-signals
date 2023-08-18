package io.signal.spi;

/**
 * @author Maxim Maximchuk
 * created on 08.02.2019
 */
public interface SignalTransmitter {

    Channel getChannel();

    void transmit(Signal<Object> signal);

    void shutdown();

}
