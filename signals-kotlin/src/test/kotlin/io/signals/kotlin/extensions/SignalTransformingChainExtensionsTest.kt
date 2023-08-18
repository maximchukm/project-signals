package io.signals.kotlin.extensions

import io.signal.DefaultSignalReceiver
import io.signal.DefaultSignalTransmitter
import io.signal.extra.SignalTransformingChain
import io.signal.spi.Signal
import org.junit.jupiter.api.Test

class SignalTransformingChainExtensionsTest {

    @Test
    fun testExtensions() {
        val transmitter = DefaultSignalTransmitter("test-channel")

        SignalTransformingChain.from(transmitter.channel)
            .transformer<Int>("int-channel") { it * 2 }
            .transformer<Int>("string-channel") { it.toString() }
            .let { chain ->
                DefaultSignalReceiver(String::class.java) {
                    println(it.message)
                }.apply {
                    tune(chain.channel)
                }
            }

        transmitter.transmit(Signal.message(2))
    }

}