package io.signal;

import io.signal.spi.Signal;
import io.signal.spi.SignalTransmitter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlockingSignalReceiverTest {

    @Test
    void testWaitForSignal() {
        String channelName = "test-channel";

        BlockingSignalReceiver<Integer> receiver = new BlockingSignalReceiver<>(Integer.class);

        SignalTransmitter transmitter = new DefaultSignalTransmitter(channelName);

        receiver.tune(transmitter.getChannel());

        transmitter.transmit(Signal.message("string test"));
        transmitter.transmit(Signal.message(10));

        Signal<Integer> recievedSignal = receiver.waitForSignal();

        assertEquals(10, recievedSignal.getMessage());
    }

}