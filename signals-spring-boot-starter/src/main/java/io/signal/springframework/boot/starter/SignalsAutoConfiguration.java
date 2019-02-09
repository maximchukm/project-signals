package io.signal.springframework.boot.starter;

import io.signal.SignalTransmitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author Maxim Maximchuk
 * created on 08.02.2019
 */
@Import(SignalsCommutationConfiguration.class)
public class SignalsAutoConfiguration {

    @Bean(destroyMethod = "shutdown")
    public SignalTransmitter signalTransmitter() {
        return new SignalTransmitter.DefaultSignalTransmitter();
    }

}
