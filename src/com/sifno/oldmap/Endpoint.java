package com.sifno.oldmap;

import java.io.Serializable;

/**
 * Created by ������ on 16.07.15.
 */
public interface Endpoint extends Serializable {

    Endpoint getOpposite();
    com.sifno.oldmap.SolarSystem getSystem();

}
