package io.signal;

import reactor.core.publisher.Flux;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
public class Channel {

    private final String name;

    private final Flux<Signal> transmission;

    public Channel(String name, Flux<Signal> transmission) {
        this.name = name;
        this.transmission = transmission;
    }

    public String getName() {
        return name;
    }

    public Flux<Signal> getTransmission() {
        return transmission;
    }
}
