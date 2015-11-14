package com.sifno.stellarmap.dataobject;

/**
 * Created by Алёна on 18.08.2015.
 */
public class Region extends Location {

    private Integer factionID;

    public Integer getFactionID() {
        return factionID;
    }

    public void setFactionID(int factionID) {
        this.factionID = factionID;
    }
}
