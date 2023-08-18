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

    @Test
    void testWaitForSignalWithId() {
        String channelName = "test-channel";

        SignalTransmitter transmitter = new DefaultSignalTransmitter(channelName);

        Signal<String> firstSignal = Signal.message("string test 1");
        Signal<String> secondSignal = Signal.message("string test 2");

        BlockingSignalReceiver<String> receiver = new BlockingSignalReceiver<>(String.class, secondSignal.getId());
        receiver.tune(transmitter.getChannel());

        transmitter.transmit(firstSignal);
        transmitter.transmit(secondSignal);

        Signal<String> recievedSignal = receiver.waitForSignal();

        assertEquals("string test 2", recievedSignal.getMessage());

        transmitter.shutdown();
    }

}