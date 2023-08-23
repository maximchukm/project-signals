package io.signal;

import io.signal.spi.Signal;
import io.signal.spi.SignalTransformer;
import io.signal.spi.SignalTransmitter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultSignalTransformerTest {

    @Test
    public void transform() {
        String inputChannelName = "input-channel";
        String outputChannelName = "output-channel";

        SignalTransmitter transmitter = new DefaultSignalTransmitter(inputChannelName);

        SignalTransformer<Integer> transformer = new DefaultSignalTransformer<>(
                Integer.class,
                outputChannelName,
                inputMessage -> inputMessage * 2
        );

        BlockingSignalReceiver<Integer> receiver = new BlockingSignalReceiver<>(Integer.class);
        receiver.tune(transformer.connect(transmitter.getChannel()));
        
        transmitter.transmit(Signal.message(8));

        Signal<Integer> resultSignal = receiver.waitForSignal();
        assertEquals(16, resultSignal.getMessage());

    }
}