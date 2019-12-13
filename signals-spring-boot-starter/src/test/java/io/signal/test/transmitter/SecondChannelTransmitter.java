package io.signal.test.transmitter;

import io.signal.SignalTransmitter;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
@Component
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
