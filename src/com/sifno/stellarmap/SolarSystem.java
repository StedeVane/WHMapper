package com.sifno.stellarmap;

import com.sifno.stellarmap.dataobject.SolarSystemData;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Алёна on 18.08.2015.
 */
public class SolarSystem extends AbstractStellarMapObject {
    private SolarSystemData data;

    private Constellation constellation;
    private Set<Stargate> stargates;

    public SolarSystem(StellarMap stellarMap) {
        super(stellarMap);
    }

    public SolarSystemData getData() {
        return data;
    }

    public void setData(SolarSystemData data) {
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

    public int getSystemClass() {
        return data.getSystemClassID();
    }

    public Collection<Stargate> getStargates() {
        if (stargates == null)
            stargates = new HashSet<>(stellarMap.getStargates(data.getID()));
        return stargates;
    }


}
