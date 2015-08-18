package com.sifno.stellarmap;

import com.sifno.stellarmap.dataobject.RegionData;

/**
 * Created by Алёна on 18.08.2015.
 */
public class Region extends AbstractStellarMapObject {
    private RegionData data;

    public Region(StellarMap stellarMap) {
        super(stellarMap);
    }

    public RegionData getData() {
        return data;
    }

    public void setData(RegionData data) {
        this.data = data;
    }

    public int getID() {
        return data.getID();
    }

    public String getName() {
        return data.getName();
    }

    public int getFractionID() {
        return data.getFractionID();
    }

}
