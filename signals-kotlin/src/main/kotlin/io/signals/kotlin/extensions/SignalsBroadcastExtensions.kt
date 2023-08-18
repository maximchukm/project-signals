package io.signals.kotlin.extensions

import io.signal.DefaultSignalReceiver
import io.signal.extra.SignalTransformingChain
import io.signal.extra.SignalsBroadcast
import io.signal.spi.Signal

fun SignalsBroadcast.Builder.transformingChain(
    inputChannelName: String,
    chainInit: SignalTransformingChain.() -> Unit
): SignalsBroadcast.Builder {
    transformingChainForChannel(inputChannelName) { chain -> chain.apply(chainInit) }
    return this
}

inline fun <reified T> SignalsBroadcast.receiver(
    channelName: String,
    noinline receive: (Signal<T>) -> Unit
): SignalsBroadcast {
    tuneReceiver(channelName, DefaultSignalReceiver(T::class.java, receive))
    return this
}
