package io.signal.spi;

/**
 * @author Maxim Maximchuk
 * created on 08.02.2019
 */
public interface SignalReceiver<T> {

    Class<T> getMessageClass();

    boolean filter(Signal<T> signal);

    void receive(Signal<T> signal);

    void tune(Channel channel);

}
