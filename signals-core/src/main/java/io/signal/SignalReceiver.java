package io.signal;

import reactor.core.publisher.Flux;

/**
 * @author Maxim Maximchuk
 * created on 08.02.2019
 */
public interface SignalReceiver {

    String getChannel();

    void receive(Flux<Signal> signals);

}
