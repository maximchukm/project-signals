package io.signal.plain.application.example.transmitter;

import io.signal.DefaultSignalTransmitter;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
public class FirstChannelTransmitter extends DefaultSignalTransmitter {
    public FirstChannelTransmitter() {
        super("first");
    }
}
