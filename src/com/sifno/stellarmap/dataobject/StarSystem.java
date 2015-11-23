package com.sifno.stellarmap.dataobject;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Алёна on 18.08.2015.
 */
public class StarSystem extends Location {
    private int constellationID;
    private double security;
    private double luminosity;
    private int sunTypeID;
    private String systemClassID;
    private Set<Integer> stargates;

    public StarSystem() {}
    public StarSystem(int id) {setID(id);}


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

    public String getSystemClassID() {
        return systemClassID;
    }

    public void setSystemClassID(String systemClassID) {
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
