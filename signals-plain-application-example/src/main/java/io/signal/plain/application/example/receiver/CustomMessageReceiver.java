package io.signal.plain.application.example.receiver;

import io.signal.AbstractSignalReceiver;
import io.signal.plain.application.example.message.CustomMessage;
import io.signal.spi.Signal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
public class CustomMessageReceiver extends AbstractSignalReceiver<CustomMessage> {

    private final Logger logger = LoggerFactory.getLogger(CustomMessageReceiver.class);

    @Override
    public Class<CustomMessage> getMessageClass() {
        return CustomMessage.class;
    }

    @Override
    public void receive(Signal<CustomMessage> signal) {
        logger.info(String.format("signal with id %s is received: %s",
                signal.getId(), signal.getMessage().getContent()));
    }
}
