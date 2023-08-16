package io.signal.spring.boot.example.transmitter;

import io.signal.DefaultSignalTransmitter;
import io.signal.springframework.boot.annotation.Transmitter;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
@Transmitter
public class SecondChannelTransmitter extends DefaultSignalTransmitter {
    public SecondChannelTransmitter() {
        super("second");
    }
}
