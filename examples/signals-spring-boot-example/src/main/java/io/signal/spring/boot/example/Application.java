package io.signal.spring.boot.example;

import io.signal.spi.Signal;
import io.signal.spi.SignalReceiver;
import io.signal.spring.boot.example.transmitter.FirstChannelTransmitter;
import io.signal.spring.boot.example.transmitter.SecondChannelTransmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private FirstChannelTransmitter firstChannelTransmitter;

    @Autowired
    private SecondChannelTransmitter secondChannelTransmitter;

    @Autowired
    private SignalReceiver<CustomMessage> customMessageReceiver;

    @Override
    public void run(String... args) {
        firstChannelTransmitter.transmit(Signal.message("First Hello World!"));
        firstChannelTransmitter.transmit(Signal.message(new CustomMessage()));
        secondChannelTransmitter.transmit(Signal.message("Second Hello World!"));

        customMessageReceiver.tune(secondChannelTransmitter.getChannel());
        secondChannelTransmitter.transmit(Signal.message(new CustomMessage()));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public static class CustomMessage {
        @Override
        public String toString() {
            return "Custom message";
        }
    }

}
