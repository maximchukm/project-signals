package io.signal.springframework.boot.starter;

import io.signal.SignalReceiver;
import io.signal.SignalTransmitter;

import java.util.List;

/**
 * @author Maxim Maximchuk
 * created on 09.02.2019
 */
public class SignalsCommutationConfiguration {

    public SignalsCommutationConfiguration(SignalTransmitter transmitter, List<SignalReceiver> receivers) {
        receivers.forEach(r -> r.receive(
                transmitter.tune(r.getChannel())
                )
        );
    }
}
