package com.sifno.stellarmap;




import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Pavel on 02.08.2015.
 */
public class SolarSystemClient extends AbstractSolarSystem implements SolarSystem {

    @Override
    public void add(Stargate stargate) {
        try {
            throw new Exception("You can not add a stargate to the client object");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Stargate> getStargates() {
        try {
            throw new Exception("not implemented yet");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
