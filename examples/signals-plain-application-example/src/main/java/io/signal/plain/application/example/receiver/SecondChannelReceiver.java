package io.signal.plain.application.example.receiver;

import io.signal.AbstractSignalReceiver;
import io.signal.spi.Signal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
public class SecondChannelReceiver extends AbstractSignalReceiver<String> {
    private final Logger logger = LoggerFactory.getLogger(SecondChannelReceiver.class);

    @Override
    public Class<String> getMessageClass() {
        return String.class;
    }

    @Override
    public void receive(Signal<String> signal) {
        logger.info(String.format("signal with id %s is received: %s", signal.getId(), signal.getMessage()));
    }
}
