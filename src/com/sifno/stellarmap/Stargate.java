package com.sifno.stellarmap;

import com.sifno.stellarmap.dataobject.StargateData;

/**
 * Created by Крочак on 16.07.15.
 */
public class Stargate extends AbstractStellarMapObject implements Endpoint {

    private StargateData data;

    private int id;
    private String name;
    private SolarSystem system;
    private StargateLink link;

    public Stargate(StellarMap stellarMap) {
        super(stellarMap);
    }

    public StargateData getData() {
        return data;
    }

    public void setData(StargateData data) {
        this.data = data;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public SolarSystem getSystem() {
        if (system == null)
            system = stellarMap.getSolarSystem(data.getSolarSystemID());
        return system;
    }

    //TODO LINK
    public StargateLink getLink() {
        return link;
    }

    public void setLink(StargateLink link) {
        this.link = link;
    }



    @Override
    public Endpoint getOpposite() {
        if (link ==null)
            return null;
        return link.getOpposite(this);
    }


    @Override
    public String toString() {
        return name;
    }
}
