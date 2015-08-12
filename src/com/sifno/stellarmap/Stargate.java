package com.sifno.stellarmap;

import com.sifno.stellarmap.Endpoint;
import com.sifno.stellarmap.SolarSystemServer;
import com.sifno.stellarmap.StargateLink;

/**
 * Created by Крочак on 16.07.15.
 */
public class Stargate implements Endpoint {


    private int id;
    private String name;
    private SolarSystemServer system;
    private StargateLink jump;



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
    public SolarSystemServer getSystem() {
        return system;
    }
    public void setSystem(SolarSystemServer system) {
        this.system = system;
    }

    public StargateLink getJump() {
        return jump;
    }
    public void setJump(StargateLink jump) {
        this.jump = jump;
    }

    public Stargate() {
    }

    @Override
    public Endpoint getOpposite() {
        if (jump ==null)
            return null;
        return jump.getOpposite(this);
    }


    @Override
    public String toString() {
        return name;
    }
}
