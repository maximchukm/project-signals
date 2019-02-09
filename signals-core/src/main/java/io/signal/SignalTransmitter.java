package io.signal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

/**
 * @author Maxim Maximchuk
 * created on 08.02.2019
 */
public interface SignalTransmitter {

    void emit(Signal signal);

    Flux<Signal> tune(String channel);

    void shutdown();

    class DefaultSignalTransmitter implements SignalTransmitter {

        private Logger logger = LoggerFactory.getLogger(SignalTransmitter.class);

        private FluxEmitter fluxEmitter = new FluxEmitter();

        private Flux<Signal> flux = Flux.create(fluxEmitter);

        @Override
        public void emit(Signal signal) {
            fluxEmitter.emit(signal);
            logger.debug("Emitted signal " + signal);
        }

        @Override
        public Flux<Signal> tune(String channel) {
            Flux<Signal> tunedFlux = flux.share().filter(signal -> signal.getChannel().equals(channel));
            logger.info("Tuned on channel " + channel);
            return tunedFlux;
        }

        @Override
        public void shutdown() {
            logger.info("Shutdown procedure initiated. Stopping transmitting");
            fluxEmitter.stop();
        }

        private class FluxEmitter implements Consumer<FluxSink<Signal>> {

            private FluxSink<Signal> fluxSink;

            @Override
            public void accept(FluxSink<Signal> signalFluxSink) {
                this.fluxSink = signalFluxSink;
            }

            void emit(Signal signal) {
                fluxSink.next(signal);
            }

            void stop() {
                fluxSink.complete();
            }
        }
    }

}
