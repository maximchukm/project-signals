package io.signal.test.receiver;

import io.signal.Signal;
import io.signal.SignalReceiver;
import io.signal.springframework.boot.annotation.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
@Receiver(channelName = "second")
public class AnotherSecondChannelReceiver implements SignalReceiver<String> {

    private String receivedMessage;
    private final Logger logger = LoggerFactory.getLogger(AnotherSecondChannelReceiver.class);

    @Override
    public Class<String> getMessageClass() {
        return String.class;
    }

    @Override
    public void receive(Signal<String> signal) {
        receivedMessage = signal.getMessage();
        logger.info(String.format("signal with id %s is received: %s", signal.getId(), receivedMessage));
    }

    public String getReceivedMessage() {
        return receivedMessage;
    }
}