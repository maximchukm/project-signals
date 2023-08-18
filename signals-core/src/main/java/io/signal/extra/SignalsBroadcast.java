package io.signal.extra;

import io.signal.DefaultSignalTransmitter;
import io.signal.spi.Channel;
import io.signal.spi.SignalTransmitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public final class SignalsBroadcast {

    private final Map<String, SignalTransmitter> transmittersMap;
    private final Map<String, Channel> channelsMap;

    SignalsBroadcast(List<SignalTransmitter> transmitters, Map<String, Channel> channels) {
        transmittersMap = transmitters.stream()
                .collect(Collectors.toMap(it -> it.getChannel().getName(), it -> it));

        this.channelsMap = channels;
    }

    public SignalsBroadcast(List<SignalTransmitter> transmitters) {
        transmittersMap = transmitters.stream()
                .collect(Collectors.toMap(it -> it.getChannel().getName(), it -> it));

        this.channelsMap = transmitters.stream()
                .collect(Collectors.toMap(it -> it.getChannel().getName(), SignalTransmitter::getChannel));
    }

    public Channel getChannel(String channelName) {
        Channel channel = channelsMap.get(channelName);
        if (channel == null) {
            throw new NoChannelException(channelName);
        }

        return channel;
    }

    public SignalTransmitter getTransmitter(String channelName) {
        SignalTransmitter transmitter = transmittersMap.get(channelName);
        if (transmitter == null) {
            throw new NoTransmitterException(channelName);
        }

        return transmitter;
    }

    public void shutdown() {
        transmittersMap.values().forEach(SignalTransmitter::shutdown);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final List<SignalTransmitter> transmitters = new ArrayList<>();

        private final Map<String, Channel> channels = new HashMap<>();

        protected Builder() {
        }

        public Builder withTransmitter(String channelName) {
            SignalTransmitter transmitter = new DefaultSignalTransmitter(channelName);
            transmitters.add(transmitter);
            channels.put(transmitter.getChannel().getName(), transmitter.getChannel());
            return this;
        }

        public Builder withTransmitter(SignalTransmitter transmitter) {
            transmitters.add(transmitter);
            channels.put(transmitter.getChannel().getName(), transmitter.getChannel());
            return this;
        }

        public Builder withTransformingChain(String channelName,
                                             UnaryOperator<SignalTransformingChain> chainConfigFunc) {
            SignalTransformingChain chain =
                    chainConfigFunc.apply(SignalTransformingChain.from(channels.get(channelName)));
            channels.put(chain.getChannel().getName(), chain.getChannel());
            return this;
        }

        public SignalsBroadcast build() {
            return new SignalsBroadcast(transmitters, channels);
        }

    }

    public static class NoChannelException extends RuntimeException {

        public NoChannelException(String channelName) {
            super(String.format("No channel with name: %s", channelName));
        }
    }

    public static class NoTransmitterException extends RuntimeException {

        public NoTransmitterException(String channelName) {
            super(String.format("No transmitter for channel with name: %s", channelName));
        }
    }

}
