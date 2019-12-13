package io.signal.springframework.boot.starter;

import io.signal.SignalReceiver;
import io.signal.SignalTransmitter;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Maxim Maximchuk
 * created on 08.02.2019
 */
public class SignalsAutoConfiguration {

    public SignalsAutoConfiguration(List<SignalTransmitter> transmitters, List<SignalReceiver> receivers) {
        final Map<String, SignalTransmitter> transmitterMap =
                transmitters.stream()
                        .collect(
                                Collectors.toMap(
                                        t -> t.getChannel().getName(),
                                        Function.identity()
                                )
                        );
        receivers.forEach(r ->
                r.tune(transmitterMap.get(r.getChannelName()).getChannel())
        );
    }
}
