package com.sifno.stellarmap;

/**
 * Created by Pavel on 26.07.2015.
 */
public class RegionServer extends AbstractLocation implements Region {


    private int fractionID;

    public RegionServer() {}

    @Override
    public int getFractionID() {
        return fractionID;
    }
    @Override
    public void setFractionID(int fractionID) {
        this.fractionID = fractionID;
    }

}
