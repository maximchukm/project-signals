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

    Channel getChannel();

    void transmit(Signal<?> signal);

    void shutdown();

    abstract class AbstractSignalTransmitter implements SignalTransmitter {

        private final Logger logger = LoggerFactory.getLogger(this.getClass());

        private final FluxEmitter signalEmitter = new FluxEmitter();

        private final Channel channel;

        public AbstractSignalTransmitter(String channelName) {
            this.channel = new Channel(channelName, Flux.create(signalEmitter).share());
        }

        @Override
        public Channel getChannel() {
            return channel;
        }

        @Override
        public void transmit(Signal<?> signal) {
            signalEmitter.emit(signal);
            logger.debug(String.format("Transmitted signal %s", signal));
        }

        @Override
        public void shutdown() {
            logger.info("Shutdown procedure initiated. Stopping transmitting");
            signalEmitter.stop();
        }

        private static class FluxEmitter implements Consumer<FluxSink<Signal<?>>> {

            private FluxSink<Signal<?>> fluxSink;

            @Override
            public void accept(FluxSink<Signal<?>> signalFluxSink) {
                this.fluxSink = signalFluxSink;
            }

            void emit(Signal<?> signal) {
                if (fluxSink != null) {
                    fluxSink.next(signal);
                }
            }

            void stop() {
                if (fluxSink != null) {
                    fluxSink.complete();
                }
            }
        }
    }

}
