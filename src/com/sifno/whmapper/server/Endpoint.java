package com.sifno.whmapper.server;

import java.io.Serializable;

/**
 * Created by Крочак on 16.07.15.
 */
public interface Endpoint extends Serializable {

    Endpoint getOpposite();
    SolarSystem getSystem();

}
