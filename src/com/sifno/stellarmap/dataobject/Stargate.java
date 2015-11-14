package com.sifno.stellarmap.dataobject;

/**
 * Created by Алёна on 18.08.2015.
 */
public class Stargate {
    private int id;
    private String name;
    private int starSystemID;
    private int destinationStargateID;
    private int jumpID;

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

    public int getStarSystemID() {
        return starSystemID;
    }

    public void setStarSystemID(int starSystemID) {
        this.starSystemID = starSystemID;
    }

    public int getDestinationStargateID() {
        return destinationStargateID;
    }

    public void setDestinationStargateID(int destinationStargateID) {
        this.destinationStargateID = destinationStargateID;
    }

    public int getJumpID() {
        return jumpID;
    }

    public void setJumpID(int jumpID) {
        this.jumpID = jumpID;
    }
}
