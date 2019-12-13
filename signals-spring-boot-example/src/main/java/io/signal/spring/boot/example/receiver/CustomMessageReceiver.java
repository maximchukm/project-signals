package io.signal.spring.boot.example.receiver;

import io.signal.Signal;
import io.signal.SignalReceiver;
import io.signal.spring.boot.example.Application;
import io.signal.spring.boot.example.transmitter.FirstChannelTransmitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
@Component
public class CustomMessageReceiver implements SignalReceiver<Application.CustomMessage> {

    private final Logger logger = LoggerFactory.getLogger(CustomMessageReceiver.class);

    public CustomMessageReceiver(FirstChannelTransmitter transmitter) {
        tune(transmitter.getChannel());
    }

    @Override
    public Class<Application.CustomMessage> getMessageClass() {
        return Application.CustomMessage.class;
    }

    @Override
    public void receive(Signal<Application.CustomMessage> signal) {
        logger.info(String.format("signal with id %s is received: %s", signal.getId(), signal.getMessage()));
    }
}
