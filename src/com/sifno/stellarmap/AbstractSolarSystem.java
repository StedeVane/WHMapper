package com.sifno.stellarmap;

/**
 * Created by Алёна on 12.08.2015.
 */
public abstract class AbstractSolarSystem extends AbstractLocation implements SolarSystem {

    private Constellation constellation;
    private Region region;

    private double security;
    private double luminosity;
    private int sunType;
    private String classType;

    @Override
    public Constellation getConstellation() {
        return constellation;
    }

    @Override
    public void setConstellation(Constellation constellation) {
        this.constellation = constellation;
    }

    @Override
    public Region getRegion() {
        return region;
    }

    @Override
    public double getSecurity() {
        return security;
    }

    @Override
    public void setSecurity(double security) {
        this.security = security;
    }

    @Override
    public double getLuminosity() {
        return luminosity;
    }

    @Override
    public void setLuminosity(double luminosity) {
        this.luminosity = luminosity;
    }

    @Override
    public int getSunType() {
        return sunType;
    }

    @Override
    public void setSunType(int sunType) {
        this.sunType = sunType;
    }

    @Override
    public String getClassType() {
        return classType;
    }

    @Override
    public void setClassType(String classType) {
        this.classType = classType;
    }

}
