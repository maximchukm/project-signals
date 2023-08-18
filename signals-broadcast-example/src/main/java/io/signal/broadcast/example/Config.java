package io.signal.broadcast.example;

import io.signal.extra.SignalsBroadcast;

public class Config {

    public static final SignalsBroadcast BROADCAST = SignalsBroadcast.builder()
            .transmitterForChannel("first")
            .transmitterForChannel("second")
            .transformingChainForChannel("second",
                    chain -> chain.add(new MultiplyingTransformer(2, "multiplied"))
            )
            .build();

    private Config() {}
}
