package com.sifno.whmapper.server;

import com.sifno.whmapper.client.Signature;
import com.sifno.whmapper.client.SolarSystemClient;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Крочак
 * Date: 28.10.14
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SolarSystem implements Location {
    private int id;
    private String name;
    private Constellation constellation;
    private double security;

    private double luminosity;
    private int sunType;

    private String classType;

    private List<Signature> signatureList;
    private Map<Signature,Signature>  signatureMap;

    private transient Set<Stargate> stargateSet = new HashSet<>();

    public SolarSystem() {    }

    public SolarSystemClient getSolarSystemClient() {
        SolarSystemClient result = new SolarSystemClient();
        result.setId(id);
        result.setName(name);
        result.setSecurity(security);
        result.setLuminosity(luminosity);
        result.setSunType(sunType);
        result.setClassType(classType);

        return result;
    }


    public int getSunType() {
        return sunType;
    }

    public void setSunType(int sunType) {
        this.sunType = sunType;
    }

    public double getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(double luminosity) {
        this.luminosity = luminosity;
    }






    public void add(Stargate stargate) {
        stargateSet.add(stargate);
    }

    public Collection<Stargate> getStargates() {
        return  stargateSet;
    }

   /* SolarSystem(String classType,String name) {
        this.name = name;
        this.classType = classType;

        signatureList = new ArrayList<Signature>();
    }*/


    public void add(Signature signature) {
        signatureList.add(signature);
    }



    public List<Signature> getSignatureList() {
        return signatureList;
    }

    public List<Wormhole> getWormholeList() {
        List<Wormhole> wormholeList = new ArrayList<>();

        for (Signature signature: signatureList) {
            if (signature instanceof Wormhole) {
                wormholeList.add((Wormhole)signature);
            }
        }
        return wormholeList;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Constellation getConstellation() {
        return constellation;
    }

    public void setConstellation(Constellation constellation) {
        this.constellation = constellation;
    }

    public double getSecurity() {
        return security;
    }

    public void setSecurity(double security) {
        this.security = security;
    }

    public Region getRegion() {
        return constellation.getRegion();
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    @Override
    public String toString() {
        return name;
    }
/*
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof SolarSystem))
            return false;

        SolarSystem solarSystem = (SolarSystem) obj;
        return solarSystem.name.equals(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
*/
}
