package com.sifno.stellarmap;

/**
 * Created by Алёна on 12.08.2015.
 */
public class AbstractLocation implements Location{
    private String name;
    private int id;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
