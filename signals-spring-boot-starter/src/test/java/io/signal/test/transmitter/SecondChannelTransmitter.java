package io.signal.test.transmitter;

import io.signal.DefaultSignalTransmitter;
import io.signal.springframework.boot.annotation.Transmitter;
import jakarta.annotation.PreDestroy;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
@Transmitter
public class SecondChannelTransmitter extends DefaultSignalTransmitter {
    public SecondChannelTransmitter() {
        super("second");
    }

    @PreDestroy
    @Override
    public void shutdown() {
        super.shutdown();
    }
}
