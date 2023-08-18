package io.signal.extra;

import io.signal.spi.Channel;
import io.signal.spi.SignalTransformer;

public final class SignalTransformingChain {

    private Channel tailChannel;

    private SignalTransformingChain(Channel channel) {
        this.tailChannel = channel;
    }

    public static SignalTransformingChain from(Channel channel) {
        return new SignalTransformingChain(channel);
    }

    public Channel getChannel() {
        return tailChannel;
    }

    public SignalTransformingChain add(SignalTransformer<?> transformer) {
        tailChannel = transformer.connect(tailChannel);
        return this;
    }

}
