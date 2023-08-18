package io.signal.extra;

import io.signal.BlockingSignalReceiver;
import io.signal.DefaultSignalTransformer;
import io.signal.spi.Signal;
import io.signal.spi.SignalTransmitter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignalsBroadcastTest {

    @Test
    void testSignalsBroadcast() {
        String channelName = "test-channel";
        String calculatedChannelName = "calculated-channel";

        SignalsBroadcast broadcast = SignalsBroadcast.builder()
                .transmitterForChannel(channelName)
                .transformingChainForChannel(
                        channelName,
                        chain ->
                                chain
                                        .add(
                                                new DefaultSignalTransformer<>(
                                                        Integer.class, calculatedChannelName,
                                                        message -> message * 2
                                                )
                                        )
                                        .add(
                                                new DefaultSignalTransformer<>(
                                                        Integer.class, calculatedChannelName,
                                                        message -> message + 15
                                                )
                                        )
                )
                .build();

        SignalTransmitter transmitter = broadcast.getTransmitter(channelName);

        BlockingSignalReceiver<Integer> signalReceiver = new BlockingSignalReceiver<>(Integer.class);
        broadcast.tuneReceiver(channelName, signalReceiver);

        transmitter.transmit(Signal.message(2));

        Signal<Integer> originalSignal = signalReceiver.waitForSignal();

        broadcast.tuneReceiver(calculatedChannelName, signalReceiver);

        transmitter.transmit(Signal.message(2));

        Signal<Integer> calculatedSignal = signalReceiver.waitForSignal();

        assertEquals(2, originalSignal.getMessage());
        assertEquals(19, calculatedSignal.getMessage());

        broadcast.shutdown();
    }

}