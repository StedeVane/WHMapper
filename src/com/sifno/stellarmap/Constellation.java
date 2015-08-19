package com.sifno.stellarmap;

import com.sifno.stellarmap.dataobject.ConstellationData;

/**
 * Created by Алёна on 18.08.2015.
 */
public class Constellation extends AbstractStellarMapObject  {
    private ConstellationData data;

    private Region region;

    public Constellation(StellarMap stellarMap) {
        super(stellarMap);
    }

    public Constellation(StellarMap stellarMap, ConstellationData data) {
        super(stellarMap);
        this.data = data;
    }

    public ConstellationData getData() {
        return data;
    }

    public void setData(ConstellationData data) {
        this.data = data;
    }

    public int getID() {
        return data.getID();
    }

    public String getName() {
        return data.getName();
    }

    public Region getRegion() {
        if (region == null)
            region = stellarMap.getRegion(data.getRegionID());
        return region;
    }
}
