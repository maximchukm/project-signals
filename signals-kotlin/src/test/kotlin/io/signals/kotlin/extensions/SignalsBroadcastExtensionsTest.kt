package io.signals.kotlin.extensions

import io.signal.BlockingSignalReceiver
import io.signal.DefaultSignalTransmitter
import io.signal.extra.SignalsBroadcast
import io.signal.spi.Signal
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SignalsBroadcastExtensionsTest {

    @Test
    fun testExtensions() {
        val channelName = "test-channel"
        val resultChannelName = "result-channel"
        val transmitter = DefaultSignalTransmitter(channelName)
        val receiver = BlockingSignalReceiver(String::class.java)

        val broadcast = SignalsBroadcast.builder()
            .withTransmitter(transmitter)
            .transformingChain(channelName) {
                transformer<Int>(outputChannelName = "int-channel") { it * 2 }
                transformer<Int>(outputChannelName = resultChannelName) { "result = $it" }
            }
            .build()
            .receiver<String>(resultChannelName) {
                println(it.message)
            }

        broadcast.tuneReceiver(resultChannelName, receiver)

        transmitter.transmit(Signal.message(2))
        transmitter.transmit(Signal.message("3"))

        val resultSignal = receiver.waitForSignal()
        assertEquals("result = 4", resultSignal.message)
    }

}