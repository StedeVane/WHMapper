package com.sifno.oldmap;

import com.sifno.stellarmap.dataobject.StarSystem;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Алёна on 18.08.2015.
 */
public class SolarSystem extends AbstractStellarMapObject {
    private StarSystem data;

    private Constellation constellation;
    private Set<Stargate> stargates;

    public SolarSystem(StellarMap stellarMap) {
        super(stellarMap);
    }

    public SolarSystem(StellarMap stellarMap, StarSystem data) {
        super(stellarMap);
        this.data = data;
    }

    public StarSystem getData() {
        return data;
    }

    public void setData(StarSystem data) {
        this.data = data;
    }

    public int getID() {
        return data.getID();
    }

    public String getName() {
        return data.getName();
    }

    public Constellation getConstellation() {
        if (constellation == null)
            constellation = stellarMap.getConstellation(data.getConstellationID());
        return constellation;
    }

    public double getSecurity() {
        return data.getSecurity();
    }

    public double getLuminosity() {
        return data.getLuminosity();
    }

    public int getSunType() {
        return data.getSunTypeID();
    }

    public String getSystemClass() {
        return data.getSystemClassID();
    }

    public Collection<Stargate> getStargates() {
        if (stargates == null)
            stargates = new HashSet<>(stellarMap.getStargates(data.getStargates()));
        return stargates;
    }


}
