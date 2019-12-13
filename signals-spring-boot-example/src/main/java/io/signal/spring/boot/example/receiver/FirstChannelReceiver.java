package io.signal.spring.boot.example.receiver;

import io.signal.Signal;
import io.signal.SignalReceiver;
import io.signal.spring.boot.example.transmitter.FirstChannelTransmitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
@Component
public class FirstChannelReceiver implements SignalReceiver<String> {

    private final Logger logger = LoggerFactory.getLogger(FirstChannelReceiver.class);

    public FirstChannelReceiver(FirstChannelTransmitter transmitter) {
        tune(transmitter.getChannel());
    }

    @Override
    public Class<String> getMessageClass() {
        return String.class;
    }

    @Override
    public void receive(Signal<String> signal) {
        logger.info(String.format("signal with id %s is received: %s", signal.getId(), signal.getMessage()));
    }
}
