package com.sifno.stellarmap;

import java.util.Collection;

/**
 * Created by Алёна on 18.08.2015.
 */
public interface StellarMap {

    public Region getRegion(int regionID);
    public Constellation getConstellation(int constellationID);
    public SolarSystem getSolarSystem(int solarSystemID);

    public Collection<Stargate> getStargates(int solarSystemID);


}
