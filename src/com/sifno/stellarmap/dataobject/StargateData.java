package com.sifno.stellarmap.dataobject;

/**
 * Created by Алёна on 18.08.2015.
 */
public class StargateData {
    private int id;
    private String name;
    private int solarSystemID;
    private int destinationStargateID;

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

    public int getSolarSystemID() {
        return solarSystemID;
    }

    public void setSolarSystemID(int solarSystemID) {
        this.solarSystemID = solarSystemID;
    }

    public int getDestinationStargateID() {
        return destinationStargateID;
    }

    public void setDestinationStargateID(int destinationStargateID) {
        this.destinationStargateID = destinationStargateID;
    }
}
