package io.signal.plain.application.example.transmitter;

import io.signal.DefaultSignalTransmitter;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
public class SecondChannelTransmitter extends DefaultSignalTransmitter {
    public SecondChannelTransmitter() {
        super("second");
    }
}
