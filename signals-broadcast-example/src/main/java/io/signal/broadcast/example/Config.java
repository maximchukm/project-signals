package io.signal.broadcast.example;

import io.signal.extra.SignalsBroadcast;

public class Config {

    public static final SignalsBroadcast BROADCAST = SignalsBroadcast.builder()
            .withTransmitter("first")
            .withTransmitter("second")
            .withTransformingChain("second",
                    chain -> chain.add(new MultiplyingTransformer(2, "multiplied"))
            )
            .build();

    private Config() {}
}
