package io.signal;

import java.util.UUID;

/**
 * @author Maxim Maximchuk
 * created on 08.02.2019
 */
final public class Signal {

    private String id;

    private Object message;

    public Signal(String id, Object data) {
        this.id = id;
        this.message = data;
    }

    public String getId() {
        return id;
    }

    public Object getMessage() {
        return message;
    }

    public static Signal message(Object message) {
        return new Signal(UUID.randomUUID().toString(), message);
    }

}
