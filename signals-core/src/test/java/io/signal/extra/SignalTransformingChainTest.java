package io.signal.extra;

import io.signal.DefaultSignalReceiver;
import io.signal.DefaultSignalTransmitter;
import io.signal.spi.Signal;
import io.signal.spi.SignalTransformer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SignalTransformingChainTest {

    @Test
    void testChaining() {
        DefaultSignalTransmitter transmitter = new DefaultSignalTransmitter("number-generator");

        SignalTransformingChain chain =
                SignalTransformingChain.from(transmitter.getChannel())
                        .add(new MultiplicationTransformer(3))
                        .add(new AdditionTransformer(15));

        new DefaultSignalReceiver<>(
                Double.class,
                signal ->
                        assertEquals(45, signal.getMessage())
        ).tune(chain.getChannel());

        transmitter.transmit(Signal.message(10));

        transmitter.shutdown();
    }

    abstract class MathTransformer implements SignalTransformer<Integer> {
        @Override
        public Class<Integer> getInputMessageClass() {
            return Integer.class;
        }

        @Override
        public String getOutputChannelName() {
            return "math";
        }

        @Override
        public Object transform(Integer inputMessage) {
            return operation(inputMessage);
        }

        abstract Integer operation(Integer number);
    }

    class AdditionTransformer extends MathTransformer {

        private final Integer argument;

        AdditionTransformer(Integer argument) {
            this.argument = argument;
        }

        @Override
        protected Integer operation(Integer number) {
            return number + argument;
        }
    }

    class MultiplicationTransformer extends MathTransformer {

        private final Integer factor;

        MultiplicationTransformer(Integer factor) {
            this.factor = factor;
        }

        @Override
        protected Integer operation(Integer number) {
            return number * factor;
        }
    }

}