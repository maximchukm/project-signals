package io.signal.plain.application.example;

import io.signal.Channel;
import io.signal.DefaultSignalReceiver;
import io.signal.DefaultSignalTransmitter;
import io.signal.Signal;
import io.signal.plain.application.example.receiver.AnotherSecondChannelReceiver;
import io.signal.plain.application.example.receiver.CustomMessageReceiver;
import io.signal.plain.application.example.receiver.FirstChannelReceiver;
import io.signal.plain.application.example.receiver.SecondChannelReceiver;
import io.signal.plain.application.example.transmitter.FirstChannelTransmitter;
import io.signal.plain.application.example.transmitter.SecondChannelTransmitter;
import org.slf4j.LoggerFactory;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
public class Application {

    public static void main(String[] args) {
        // Configuration

        DefaultSignalTransmitter defaultTransmitter = new DefaultSignalTransmitter("default");
        new DefaultSignalReceiver<>(String.class,
                stringSignal ->
                        LoggerFactory.getLogger(DefaultSignalReceiver.class)
                                .info(stringSignal.getMessage())
        ).tune(defaultTransmitter.getChannel());

        FirstChannelTransmitter firstChannelTransmitter = new FirstChannelTransmitter();
        SecondChannelTransmitter secondChannelTransmitter = new SecondChannelTransmitter();

        Channel firstChannel = firstChannelTransmitter.getChannel();
        Channel secondChannel = secondChannelTransmitter.getChannel();

        FirstChannelReceiver firstChannelReceiver = new FirstChannelReceiver();
        firstChannelReceiver.tune(firstChannel);

        SecondChannelReceiver secondChannelReceiver = new SecondChannelReceiver();
        secondChannelReceiver.tune(secondChannel);

        AnotherSecondChannelReceiver anotherSecondChannelReceiver = new AnotherSecondChannelReceiver();
        anotherSecondChannelReceiver.tune(secondChannel);

        CustomMessageReceiver customMessageReceiver = new CustomMessageReceiver();
        customMessageReceiver.tune(firstChannel);

        // Run message transmission

        defaultTransmitter.transmit(Signal.message("Default Hello World!"));
        firstChannelTransmitter.transmit(Signal.message("First Hello World!"));
        firstChannelTransmitter.transmit(Signal.message(new CustomMessage()));
        secondChannelTransmitter.transmit(Signal.message("Second Hello World!"));

        // Shutdown

        defaultTransmitter.shutdown();
        firstChannelTransmitter.shutdown();
        secondChannelTransmitter.shutdown();
    }

    public static class CustomMessage {
        @Override
        public String toString() {
            return "Custom message";
        }
    }

}
