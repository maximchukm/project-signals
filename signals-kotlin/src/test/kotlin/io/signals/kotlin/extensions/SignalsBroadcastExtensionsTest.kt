package io.signals.kotlin.extensions

import io.signal.DefaultSignalTransmitter
import io.signal.extra.SignalsBroadcast
import io.signal.spi.Signal
import org.junit.jupiter.api.Test

class SignalsBroadcastExtensionsTest {

    @Test
    fun testExtensions() {
        val channelName = "test-channel"
        val transmitter = DefaultSignalTransmitter(channelName)

        SignalsBroadcast.builder()
            .withTransmitter(transmitter)
            .transformingChain(channelName) {
                transformer<Int>("int-channel") { it * 2 }
                transformer<Int>("string-channel") { it.toString() }
            }
            .build()
            .receiver<String>("string-channel") {
                println(it.message)
            }

        transmitter.transmit(Signal.message(2))

    }

}