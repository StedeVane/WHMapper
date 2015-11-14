package com.sifno.oldmap;

/**
 * Created by Крочак on 16.07.15.
 */
public class Stargate extends AbstractStellarMapObject implements Endpoint {

    private com.sifno.stellarmap.dataobject.Stargate data;

    private SolarSystem system;
    private StargateLink link;

    public Stargate(StellarMap stellarMap) {
        super(stellarMap);
    }

    public Stargate(StellarMap stellarMap, com.sifno.stellarmap.dataobject.Stargate data) {
        super(stellarMap);
        this.data = data;
    }

    public com.sifno.stellarmap.dataobject.Stargate getData() {
        return data;
    }

    public void setData(com.sifno.stellarmap.dataobject.Stargate data) {
        this.data = data;
    }

    public int getID() {
        return data.getID();
    }

    public String getName() {
        return data.getName();
    }

    @Override
    public SolarSystem getSystem() {
        if (system == null)
            system = stellarMap.getSolarSystem(data.getStarSystemID());
        return system;
    }

    public StargateLink getLink() {
        if (link==null)
            link = stellarMap.getStargateLink(data.getID(),data.getDestinationStargateID());
        return link;
    }

    @Override
    public Endpoint getOpposite() {
        return getLink().getOpposite(this);
    }


    @Override
    public String toString() {
        return data.toString();
    }
}
