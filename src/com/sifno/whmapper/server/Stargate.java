package com.sifno.whmapper.server;

/**
 * Created by Крочак on 16.07.15.
 */
public class Stargate implements Endpoint {


    private int id;
    private String name;
    private SolarSystem system;
    private StargateJump jump;



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

    public StargateJump getJump() {
        return jump;
    }
    public void setJump(StargateJump jump) {
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
