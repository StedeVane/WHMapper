package com.sifno.stellarmap;

import java.io.Serializable;

/**
 * Created by Крочак on 16.07.15.
 */
public interface Endpoint extends Serializable {

    Endpoint getOpposite();
    SolarSystemServer getSystem();

}
