package com.sifno.stellarmap;

/**
 * Created by Pavel on 26.07.2015.
 */
public class ConstellationServer extends AbstractLocation implements Constellation {

    private Region region;

    public ConstellationServer() {}

    @Override
    public Region getRegion() {
        return region;
    }

    @Override
    public void setRegion(Region region) {
        this.region = region;
    }

}
