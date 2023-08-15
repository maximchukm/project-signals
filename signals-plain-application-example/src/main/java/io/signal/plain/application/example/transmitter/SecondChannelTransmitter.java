package io.signal.plain.application.example.transmitter;

import io.signal.SignalTransmitter;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
public class SecondChannelTransmitter extends SignalTransmitter.AbstractSignalTransmitter {
    public SecondChannelTransmitter() {
        super("second");
    }
}
