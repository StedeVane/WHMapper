package com.sifno.stellarmap.dataobject;

/**
 * Created by Алёна on 18.08.2015.
 */
public abstract class LocationData implements Location {
    private int id;
    private String name;

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
