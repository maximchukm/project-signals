package io.signal.spi;

import reactor.core.publisher.Flux;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
public class Channel {

    private final String name;

    private final Flux<Signal<Object>> transmission;

    public Channel(String name, Flux<Signal<Object>> transmission) {
        this.name = name;
        this.transmission = transmission;
    }

    public String getName() {
        return name;
    }

    public Flux<Signal<Object>> getTransmission() {
        return transmission;
    }

}
