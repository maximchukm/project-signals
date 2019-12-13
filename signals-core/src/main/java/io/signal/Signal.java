package io.signal;

import java.util.UUID;

/**
 * @author Maxim Maximchuk
 * created on 08.02.2019
 */
final public class Signal<T> {

    private String id;

    private T message;

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
