package io.signal.broadcast.example;

import io.signal.DefaultSignalReceiver;
import io.signal.spi.Signal;
import io.signal.spi.SignalReceiver;
import io.signal.spi.SignalTransmitter;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) {
        String firstChannelName = "first";
        String secondChannelName = "second";
        String multipliedChannelName = "multiplied";

        SignalReceiver<String> stringReceiver = new DefaultSignalReceiver<>(
                String.class,
                stringSignal -> LoggerFactory.getLogger("string-receiver").info(stringSignal.getMessage())
        );
        stringReceiver.tune(Config.BROADCAST.getChannel(firstChannelName));

        SignalReceiver<Integer> integerSignalReceiver = new DefaultSignalReceiver<>(
                Integer.class,
                integerSignal -> LoggerFactory.getLogger("integer-receiver").info(integerSignal.getMessage().toString())
        );
        integerSignalReceiver.tune(Config.BROADCAST.getChannel(firstChannelName));

        SignalTransmitter firstTransmitter = Config.BROADCAST.getTransmitter(firstChannelName);
        firstTransmitter.transmit(Signal.message("String message"));
        firstTransmitter.transmit(Signal.message(30));

        SignalTransmitter secondTransmitter = Config.BROADCAST.getTransmitter(secondChannelName);

        integerSignalReceiver.tune(Config.BROADCAST.getChannel(multipliedChannelName));
        secondTransmitter.transmit(Signal.message(30));

        Config.BROADCAST.shutdown();

    }

}