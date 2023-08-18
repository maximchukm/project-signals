package io.signals.kotlin.extensions

import io.signal.DefaultSignalTransformer
import io.signal.extra.SignalTransformingChain

inline fun <reified T> SignalTransformingChain.transformer(
    outputChannelName: String,
    noinline transform: (T) -> Any
): SignalTransformingChain {
    add(DefaultSignalTransformer(T::class.java, outputChannelName, transform))
    return this
}