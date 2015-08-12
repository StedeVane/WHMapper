package com.sifno.stellarmap;

/**
 * Created by Pavel on 26.07.2015.
 */
public class ConstellationServer implements Location {

    private int id;
    private String name;
    private RegionServer region;

    public ConstellationServer() {}

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RegionServer getRegion() {
        return region;
    }

    public void setRegion(RegionServer region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return name;
    }
}
