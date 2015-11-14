package com.sifno.stellarmap.dataobject;

import java.io.Serializable;

/**
 * Created by Алёна on 18.08.2015.
 */
public abstract class Location implements Serializable {
    private int id;
    private String name;


    public int getID() {
        return id;
    }


    public void setID(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
