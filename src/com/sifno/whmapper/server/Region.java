package com.sifno.whmapper.server;

/**
 * Created by Pavel on 26.07.2015.
 */
public class Region implements Location {

    private int id;
    private String name;
    private int fractionID;

    public Region() {}

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

    public int getFractionID() {
        return fractionID;
    }

    public void setFractionID(int fractionID) {
        this.fractionID = fractionID;
    }

    @Override
    public String toString() {
        return name;
    }
}
