package io.signal.test;

import io.signal.Signal;
import io.signal.springframework.boot.starter.SignalsAutoConfiguration;
import io.signal.test.transmitter.FirstChannelTransmitter;
import io.signal.test.transmitter.SecondChannelTransmitter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Maksym Maksymchuk
 * date 12/13/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfiguration.class, SignalsAutoConfiguration.class})
public class SignalsTest {

    @Autowired
    private FirstChannelTransmitter firstChannelTransmitter;

    @Autowired
    private SecondChannelTransmitter secondChannelTransmitter;

    @Test
    public void testSignalTransmitting() {
        firstChannelTransmitter.transmit(Signal.message("First Hello World!"));
        secondChannelTransmitter.transmit(Signal.message("Second Hello World!"));
    }


}
