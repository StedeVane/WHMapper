package com.sifno.whmapper.server;

/**
 * Created by Pavel on 26.07.2015.
 */
public class Constellation implements Location {

    private int id;
    private String name;
    private Region region;

    public Constellation() {}

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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return name;
    }
}
