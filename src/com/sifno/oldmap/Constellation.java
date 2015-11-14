package com.sifno.oldmap;

/**
 * Created by Алёна on 18.08.2015.
 */
public class Constellation extends AbstractStellarMapObject  {
    private com.sifno.stellarmap.dataobject.Constellation data;

    private Region region;

    public Constellation(StellarMap stellarMap) {
        super(stellarMap);
    }

    public Constellation(StellarMap stellarMap, com.sifno.stellarmap.dataobject.Constellation data) {
        super(stellarMap);
        this.data = data;
    }

    public com.sifno.stellarmap.dataobject.Constellation getData() {
        return data;
    }

    public void setData(com.sifno.stellarmap.dataobject.Constellation data) {
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
