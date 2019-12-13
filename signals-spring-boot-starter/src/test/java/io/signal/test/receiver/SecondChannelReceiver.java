package io.signal.test.receiver;

import io.signal.Signal;
import io.signal.SignalReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
@Component
public class SecondChannelReceiver implements SignalReceiver {
    private final Logger logger = LoggerFactory.getLogger(SecondChannelReceiver.class);

    @Override
    public String getChannelName() {
        return "second";
    }

    @Override
    public void receive(Signal signal) {
        logger.info(String.format("signal with id %s is received: %s", signal.getId(), signal.getMessage()));
    }
}
