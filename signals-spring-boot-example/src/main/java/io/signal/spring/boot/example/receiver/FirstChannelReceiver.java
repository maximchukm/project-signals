package io.signal.spring.boot.example.receiver;

import io.signal.Signal;
import io.signal.SignalReceiver;
import io.signal.springframework.boot.annotation.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
@Receiver(channelName = "first")
public class FirstChannelReceiver implements SignalReceiver<String> {

    private final Logger logger = LoggerFactory.getLogger(FirstChannelReceiver.class);

    @Override
    public Class<String> getMessageClass() {
        return String.class;
    }

    @Override
    public void receive(Signal<String> signal) {
        logger.info(String.format("signal with id %s is received: %s", signal.getId(), signal.getMessage()));
    }
}
