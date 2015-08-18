package com.sifno.stellarmap.dataobject;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Алёна on 18.08.2015.
 */
public class SolarSystemData extends LocationData {
    private int constellationID;
    private double security;
    private double luminosity;
    private int sunTypeID;
    private int systemClassID;
    
    private Set<Integer> stargates;

    public int getConstellationID() {
        return constellationID;
    }

    public void setConstellationID(int constellationID) {
        this.constellationID = constellationID;
    }

    public double getSecurity() {
        return security;
    }

    public void setSecurity(double security) {
        this.security = security;
    }

    public double getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(double luminosity) {
        this.luminosity = luminosity;
    }

    public int getSunTypeID() {
        return sunTypeID;
    }

    public void setSunTypeID(int sunTypeID) {
        this.sunTypeID = sunTypeID;
    }

    public int getSystemClassID() {
        return systemClassID;
    }

    public void setSystemClassID(int systemClassID) {
        this.systemClassID = systemClassID;
    }


    public Set<Integer> getStargates() {
        return stargates;
    }

    public void setStargates(Set<Integer> stargates) {
        this.stargates = stargates;
    }

    public boolean add(int stargateID) {
        if (stargates == null)
            stargates = new HashSet<>();
        return stargates.add(stargateID);
    }
}
