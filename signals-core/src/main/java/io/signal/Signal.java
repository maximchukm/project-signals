package io.signal;

/**
 * @author Maxim Maximchuk
 * created on 08.02.2019
 */
public class Signal {

    private String channel;

    private String operation;

    private Object data;

    public Signal(String channel, String operation, Object data) {
        this.channel = channel;
        this.operation = operation;
        this.data = data;
    }

    public String getChannel() {
        return channel;
    }

    public String getOperation() {
        return operation;
    }

    public Object getData() {
        return data;
    }

}
