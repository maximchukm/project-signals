package io.signal.test;

import io.signal.Signal;
import io.signal.springframework.boot.starter.SignalsAutoConfiguration;
import io.signal.test.receiver.AnotherSecondChannelReceiver;
import io.signal.test.receiver.FirstChannelReceiver;
import io.signal.test.receiver.SecondChannelReceiver;
import io.signal.test.transmitter.FirstChannelTransmitter;
import io.signal.test.transmitter.SecondChannelTransmitter;
import io.signal.test.transmitter.ThirdChannelTransmitter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {TestConfiguration.class, SignalsAutoConfiguration.class})
public class SignalsTest {

    @Autowired
    private FirstChannelTransmitter firstChannelTransmitter;

    @Autowired
    private SecondChannelTransmitter secondChannelTransmitter;

    @Autowired
    private ThirdChannelTransmitter thirdChannelTransmitter;

    @Autowired
    private FirstChannelReceiver firstChannelReceiver;

    @Autowired
    private SecondChannelReceiver secondChannelReceiver;

    @Autowired
    private AnotherSecondChannelReceiver anotherSecondChannelReceiver;

    @Test
    public void testSignalTransmitting() {
        String firstMessage = "First Hello World!";
        String secondMessage = "Second Hello World!";
        String thirdMessage = "Third Hello World!";

        firstChannelTransmitter.transmit(Signal.message(firstMessage));
        secondChannelTransmitter.transmit(Signal.message(secondMessage));
        thirdChannelTransmitter.transmit(Signal.message(thirdMessage));

        assertEquals(firstMessage, firstChannelReceiver.getReceivedMessage());
        assertEquals(secondMessage, secondChannelReceiver.getReceivedMessage());
        assertEquals(secondMessage, anotherSecondChannelReceiver.getReceivedMessage());
    }


}
