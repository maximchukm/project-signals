package io.signal.spring.boot.example.transmitter;

import io.signal.SignalTransmitter;
import io.signal.springframework.boot.annotation.Transmitter;
import jakarta.annotation.PreDestroy;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
@Transmitter
public class SecondChannelTransmitter extends SignalTransmitter.AbstractSignalTransmitter {
    public SecondChannelTransmitter() {
        super("second");
    }

    @PreDestroy
    @Override
    public void shutdown() {
        super.shutdown();
    }
}
