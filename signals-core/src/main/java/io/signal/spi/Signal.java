package io.signal.spi;

import java.util.UUID;

/**
 * @author Maxim Maximchuk
 * created on 08.02.2019
 */
public final class Signal<T> {

    private final String id;

    private final T message;

    public Signal(String id, T data) {
        this.id = id;
        this.message = data;
    }

    public String getId() {
        return id;
    }

    public T getMessage() {
        return message;
    }

    public static <M> Signal<M> message(M message) {
        return new Signal<>(UUID.randomUUID().toString(), message);
    }

}
