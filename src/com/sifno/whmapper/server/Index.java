package com.sifno.whmapper.server;

import java.io.Serializable;

/**
 * Created by Алёна on 21.07.2015.
 */
public class Index implements Serializable {
    private String name;
    private String index;

    private long totalMass;
    private long massJump;
    private long mssRegeneration;
    private int lifeTime;

    private String dest;

    public Index() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        index = name.substring(name.length()-4);
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public long getTotalMass() {
        return totalMass;
    }

    public void setTotalMass(int totalMass) {
        this.totalMass = totalMass;
    }

    public long getMassJump() {
        return massJump;
    }

    public void setMassJump(int massJump) {
        this.massJump = massJump;
    }

    public long getMssRegeneration() {
        return mssRegeneration;
    }

    public void setMssRegeneration(int mssRegeneration) {
        this.mssRegeneration = mssRegeneration;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    @Override
    public String toString() {
        return index;
    }
}
