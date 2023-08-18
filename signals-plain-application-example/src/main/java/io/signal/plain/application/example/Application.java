package io.signal.plain.application.example;

import io.signal.*;
import io.signal.plain.application.example.message.CustomMessage;
import io.signal.plain.application.example.receiver.AnotherSecondChannelReceiver;
import io.signal.plain.application.example.receiver.CustomMessageReceiver;
import io.signal.plain.application.example.receiver.FirstChannelReceiver;
import io.signal.plain.application.example.receiver.SecondChannelReceiver;
import io.signal.plain.application.example.transformer.CustomMessageTransformer;
import io.signal.plain.application.example.transmitter.FirstChannelTransmitter;
import io.signal.plain.application.example.transmitter.SecondChannelTransmitter;
import io.signal.spi.Channel;
import io.signal.spi.Signal;
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

        // Connect transformer

        CustomMessageTransformer customMessageTransformer = new CustomMessageTransformer();
        Channel transformerChannel = customMessageTransformer.connect(firstChannelTransmitter.getChannel());

        // Run message transmission

        defaultTransmitter.transmit(Signal.message("Default Hello World!"));
        firstChannelTransmitter.transmit(Signal.message("First Hello World!"));
        firstChannelTransmitter.transmit(Signal.message(new CustomMessage("custom message")));
        secondChannelTransmitter.transmit(Signal.message("Second Hello World!"));

        firstChannelReceiver.tune(transformerChannel);
        firstChannelTransmitter.transmit(Signal.message(new CustomMessage("Transform Hello World!")));

        // Blocking Receiver

        BlockingSignalReceiver<String> blockingSignalReceiver = new BlockingSignalReceiver<>(String.class);
        blockingSignalReceiver.tune(firstChannel);

        firstChannelTransmitter.transmit(Signal.message("test blocking"));
        Signal<String> blockingSignal = blockingSignalReceiver.waitForSignal();

        LoggerFactory.getLogger(BlockingSignalReceiver.class).info(blockingSignal.getMessage());

        // Shutdown

        defaultTransmitter.shutdown();
        firstChannelTransmitter.shutdown();
        secondChannelTransmitter.shutdown();
    }

}
