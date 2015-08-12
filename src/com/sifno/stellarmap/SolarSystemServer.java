package com.sifno.stellarmap;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Крочак
 * Date: 28.10.14
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SolarSystemServer extends AbstractSolarSystem implements SolarSystem {


    private List<Signature> signatureList;
    private Map<Signature,Signature>  signatureMap;

    private transient Set<Stargate> stargateSet = new HashSet<>();

    public SolarSystemServer() {    }

    public Collection<Stargate> getStargates() {
        return  stargateSet;
    }

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



    public void add(Stargate stargate) {
        stargateSet.add(stargate);
    }



/*
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof SolarSystemServer))
            return false;

        SolarSystemServer solarSystem = (SolarSystemServer) obj;
        return solarSystem.name.equals(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
*/
}
