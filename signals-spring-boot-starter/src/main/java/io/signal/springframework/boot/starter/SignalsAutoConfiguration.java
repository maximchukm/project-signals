package io.signal.springframework.boot.starter;

import io.signal.SignalReceiver;
import io.signal.SignalTransmitter;
import io.signal.springframework.boot.annotation.Receiver;
import io.signal.springframework.boot.annotation.Transmitter;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Maxim Maximchuk
 * created on 08.02.2019
 */
@Configuration
public class SignalsAutoConfiguration {

    public SignalsAutoConfiguration(List<SignalTransmitter> transmitters, List<SignalReceiver<?>> receivers) {
        final Map<String, SignalTransmitter> transmitterMap =
                transmitters.stream()
                        .filter(it -> it.getClass().isAnnotationPresent(Transmitter.class))
                        .collect(
                                Collectors.toMap(
                                        t -> t.getChannel().getName(),
                                        Function.identity()
                                )
                        );
        receivers
                .stream()
                .filter(it -> it.getClass().isAnnotationPresent(Receiver.class))
                .forEach(r -> {
                            SignalTransmitter transmitter = transmitterMap.get(r.getClass().getAnnotation(Receiver.class).channelName());
                            if (transmitter != null) {
                                r.tune(transmitter.getChannel());
                            }
                        }
                );
    }
}
