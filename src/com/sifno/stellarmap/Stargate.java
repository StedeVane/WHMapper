package com.sifno.stellarmap;

/**
 * Created by Крочак on 16.07.15.
 */
public class Stargate implements Endpoint {


    private int id;
    private String name;
    private SolarSystem system;
    private StargateLink link;



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public SolarSystem getSystem() {
        return system;
    }
    public void setSystem(SolarSystem system) {
        this.system = system;
    }

    public StargateLink getLink() {
        return link;
    }
    public void setLink(StargateLink link) {
        this.link = link;
    }

    public Stargate() {
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
