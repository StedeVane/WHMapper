package com.sifno.whmapper.server;

import java.io.Serializable;

/**
 * Created by ������ on 16.07.15.
 */
public abstract class Jump implements Serializable {
    abstract Endpoint[] getEndpoints();

    abstract Endpoint getSource();
    abstract Endpoint getDest();

    abstract boolean isSource(Endpoint endpoint);
    abstract boolean isDest(Endpoint endpoint);

    abstract Endpoint getOpposite(Endpoint endpoint);
}
