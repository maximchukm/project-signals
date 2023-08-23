package io.signal;

import io.signal.spi.Signal;
import io.signal.spi.SignalReceiver;
import io.signal.spi.SignalTransmitter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultSignalReceiverTest {

    @Test
    public void testReceiveSignal() {
        String channelName = "test-channel";
        String message = "test message";

        SignalTransmitter transmitter = new DefaultSignalTransmitter(channelName);

        SignalReceiver<String> receiver = new DefaultSignalReceiver<>(
                String.class,
                signal -> assertEquals(message, signal.getMessage())
        );

        receiver.tune(transmitter.getChannel());

        transmitter.transmit(Signal.message(message));

        transmitter.shutdown();
    }

    @Test
    public void testReceiveSignalWithFilter() {
        String channelName = "test-channel";

        SignalTransmitter transmitter = new DefaultSignalTransmitter(channelName);

        SignalReceiver<Integer> receiver = new DefaultSignalReceiver<>(
                Integer.class,
                signal -> assertEquals(8, signal.getMessage())
        )
                .withFilter(signal ->
                        signal.getMessage() % 2 == 0
                );

        receiver.tune(transmitter.getChannel());

        transmitter.transmit(Signal.message(9));
        transmitter.transmit(Signal.message(8));

        transmitter.shutdown();
    }

}