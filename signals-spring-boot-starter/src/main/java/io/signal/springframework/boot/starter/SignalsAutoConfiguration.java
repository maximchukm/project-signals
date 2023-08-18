package io.signal.springframework.boot.starter;

import io.signal.spi.SignalReceiver;
import io.signal.spi.SignalTransmitter;
import io.signal.extra.SignalsBroadcast;
import io.signal.springframework.boot.annotation.Receiver;
import io.signal.springframework.boot.annotation.Transmitter;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maxim Maximchuk
 * created on 08.02.2019
 */
@Configuration
public class SignalsAutoConfiguration {

    private final SignalsBroadcast broadcast;

    public SignalsAutoConfiguration(List<SignalTransmitter> transmitters, List<SignalReceiver<?>> receivers) {
        broadcast = new SignalsBroadcast(
                transmitters.stream()
                        .filter(it -> it.getClass().isAnnotationPresent(Transmitter.class))
                        .collect(Collectors.toList())
        );

        receivers
                .stream()
                .filter(it -> it.getClass().isAnnotationPresent(Receiver.class))
                .forEach(r -> r.tune(broadcast.getChannel(r.getClass().getAnnotation(Receiver.class).channelName())));
    }

    @PreDestroy
    public void shutdown() {
        broadcast.shutdown();
    }
}
