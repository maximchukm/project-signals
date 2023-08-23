package io.signal.test.transmitter;

import io.signal.DefaultSignalTransmitter;
import io.signal.springframework.boot.annotation.Transmitter;
import jakarta.annotation.PreDestroy;
import org.slf4j.LoggerFactory;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
@Transmitter
public class FirstChannelTransmitter extends DefaultSignalTransmitter {
    public FirstChannelTransmitter() {
        super("first");
        LoggerFactory.getLogger(FirstChannelTransmitter.class).info("####### FirstChannelTransmitter created");
    }

    @PreDestroy
    @Override
    public void shutdown() {
        super.shutdown();
    }
}
