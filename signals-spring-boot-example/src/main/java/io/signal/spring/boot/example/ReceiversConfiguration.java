package io.signal.spring.boot.example;

import io.signal.Channel;
import io.signal.SignalReceiver;
import io.signal.spring.boot.example.receiver.AnotherSecondChannelReceiver;
import io.signal.spring.boot.example.receiver.CustomMessageReceiver;
import io.signal.spring.boot.example.receiver.FirstChannelReceiver;
import io.signal.spring.boot.example.receiver.SecondChannelReceiver;
import io.signal.spring.boot.example.transmitter.FirstChannelTransmitter;
import io.signal.spring.boot.example.transmitter.SecondChannelTransmitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
@Configuration
public class ReceiversConfiguration {

    private final Channel firstChannel;

    private final Channel secondChannel;

    public ReceiversConfiguration(FirstChannelTransmitter firstChannelTransmitter,
                                  SecondChannelTransmitter secondChannelTransmitter) {
        firstChannel = firstChannelTransmitter.getChannel();
        secondChannel = secondChannelTransmitter.getChannel();
    }

    @Bean
    public SignalReceiver<String> firstChannelReceiver() {
        return new FirstChannelReceiver().tune(firstChannel);
    }

    @Bean
    public SignalReceiver<String> secondChannelReceiver() {
        return new SecondChannelReceiver().tune(secondChannel);
    }

    @Bean
    public SignalReceiver<String> anotherSecondChannelReceiver() {
        return new AnotherSecondChannelReceiver().tune(secondChannel);
    }

    @Bean
    public SignalReceiver<Application.CustomMessage> customMessageReceiver() {
        return new CustomMessageReceiver().tune(firstChannel);
    }

}
