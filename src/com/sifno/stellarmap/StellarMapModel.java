package com.sifno.stellarmap;


import com.sifno.stellarmap.dataobject.Constellation;
import com.sifno.stellarmap.dataobject.Region;
import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.stellarmap.dataobject.Stargate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Алёна on 10.11.2015.
 */
public class StellarMapModel {

    private Map<Integer, Region> regionMap = new HashMap<>();
    private Map<Integer, Constellation> constellationMap = new HashMap<>();
    private Map<Integer, StarSystem> starSystemMap = new HashMap<>();
    private Map<Integer, Stargate> stargateMap = new HashMap<>();



    public Region getRegion(int regionID) {
        Region region = regionMap.get(regionID);
        return region;
    }

    public void addRegion(Region region) {
        regionMap.put(region.getID(), region);
    }
    public void addRegions(Collection<Region> regions) {
        for (Region region: regions) {
            regionMap.put(region.getID(), region);
        }
    }


    public Constellation getConstellation(int constellationID) {
        Constellation constellation = constellationMap.get(constellationID);
        return constellation;
    }

    public void addConstellation(Constellation constellation) {
        constellationMap.put(constellation.getID(), constellation);
    }
    public void addConstellations(Collection<Constellation> constellations) {
        for (Constellation constellation: constellations) {
            constellationMap.put(constellation.getID(),constellation);
        }
    }


    public StarSystem getStarSystem(int starSystemID) {
        StarSystem starSystem = starSystemMap.get(starSystemID);
        return starSystem;
    }

    public void addStarSystem(StarSystem starSystem) {
        starSystemMap.put(starSystem.getID(),starSystem);
    }
    public void addStarSystems(Collection<StarSystem> starSystems) {
        for (StarSystem starSystem: starSystems) {
            starSystemMap.put(starSystem.getID(),starSystem);
        }
    }

    public Stargate getStargate(int stargateID) {
        Stargate stargate = stargateMap.get(stargateID);
        return stargate;
    }

    public void addStargate(Stargate stargate) {
        stargateMap.put(stargate.getID(),stargate);
    }
    public void addStargates(Collection<Stargate> stargates) {
        for (Stargate stargate: stargates) {
            stargateMap.put(stargate.getID(),stargate);
        }
    }

}
