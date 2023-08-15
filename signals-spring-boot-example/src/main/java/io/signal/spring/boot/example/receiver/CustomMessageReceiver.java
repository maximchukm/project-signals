package io.signal.spring.boot.example.receiver;

import io.signal.Signal;
import io.signal.SignalReceiver;
import io.signal.spring.boot.example.Application;
import io.signal.springframework.boot.annotation.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
@Receiver(channelName = "first")
public class CustomMessageReceiver implements SignalReceiver<Application.CustomMessage> {

    private final Logger logger = LoggerFactory.getLogger(CustomMessageReceiver.class);

    @Override
    public Class<Application.CustomMessage> getMessageClass() {
        return Application.CustomMessage.class;
    }

    @Override
    public void receive(Signal<Application.CustomMessage> signal) {
        logger.info(String.format("signal with id %s is received: %s", signal.getId(), signal.getMessage()));
    }
}
