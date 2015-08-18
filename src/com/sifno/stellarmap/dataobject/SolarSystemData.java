package com.sifno.stellarmap.dataobject;

/**
 * Created by Алёна on 18.08.2015.
 */
public class SolarSystemData extends LocationData {
    private int constellationID;
    private double security;
    private double luminosity;
    private int sunTypeID;
    private int systemClassID;

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
}
