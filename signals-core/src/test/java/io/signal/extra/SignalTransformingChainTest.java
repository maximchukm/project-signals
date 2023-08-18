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
        DefaultSignalTransmitter numberGenerator = new DefaultSignalTransmitter("number-generator");

        SignalTransformingChain chain =
                SignalTransformingChain.from(numberGenerator.getChannel())
                        .add(new MultiplicationTransformer(3))
                        .add(new AdditionTransformer(15));

        new DefaultSignalReceiver<>(
                Double.class,
                signal ->
                        assertEquals(45, signal.getMessage())
        ).tune(chain.getChannel());

        numberGenerator.transmit(Signal.message(10));

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
        public Signal<Object> transform(Signal<Integer> inputSignal) {
            return Signal.message(operation(inputSignal.getMessage()));
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