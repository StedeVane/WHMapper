package com.sifno.oldmap;

/**
 * Created by Алёна on 18.08.2015.
 */
public class Region extends AbstractStellarMapObject {
    private com.sifno.stellarmap.dataobject.Region data;

    public Region(StellarMap stellarMap) {
        super(stellarMap);
    }

    public Region(StellarMap stellarMap, com.sifno.stellarmap.dataobject.Region data) {
        super(stellarMap);
        this.data = data;
    }

    public com.sifno.stellarmap.dataobject.Region getData() {
        return data;
    }

    public void setData(com.sifno.stellarmap.dataobject.Region data) {
        this.data = data;
    }

    public int getID() {
        return data.getID();
    }

    public String getName() {
        return data.getName();
    }

    public int getFractionID() {
        return data.getFactionID();
    }

}
