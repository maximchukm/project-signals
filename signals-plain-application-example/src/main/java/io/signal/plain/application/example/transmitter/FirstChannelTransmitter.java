package io.signal.plain.application.example.transmitter;

import io.signal.SignalTransmitter;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
public class FirstChannelTransmitter extends SignalTransmitter.AbstractSignalTransmitter {
    public FirstChannelTransmitter() {
        super("first");
    }
}
