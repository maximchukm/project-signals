package io.signal.test.receiver;

import io.signal.AbstractSignalReceiver;
import io.signal.spi.Signal;
import io.signal.springframework.boot.annotation.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
@Receiver(channelName = "second")
public class SecondChannelReceiver extends AbstractSignalReceiver<String> {

    private String receivedMessage;
    private final Logger logger = LoggerFactory.getLogger(SecondChannelReceiver.class);

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
