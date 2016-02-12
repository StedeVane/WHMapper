package com.sifno.stellarmap;


import com.sifno.stellarmap.dataobject.Constellation;
import com.sifno.stellarmap.dataobject.Region;
import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.stellarmap.dataobject.Stargate;

import java.util.*;


public class StellarMapInfoCache {

    private Map<Integer, Region> regionMap = new HashMap<>();
    private Map<Integer, Constellation> constellationMap = new HashMap<>();
    private Map<Integer, StarSystem> starSystemMap = new HashMap<>();
    private Map<Integer, Stargate> stargateMap = new HashMap<>();

    private Map<Integer, List<Stargate>> stargateJumpMap = new HashMap<>();




    public Region getRegion(int regionID) {
        Region region = regionMap.get(regionID);
        return region;
    }
    public Collection<Region> getRegions() {
        return regionMap.values();
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

    public Collection<Constellation> getConstellations() {
        return constellationMap.values();
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

    public Collection<StarSystem> getStarSystems() {
        return starSystemMap.values();
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

    public Collection<Stargate> getStargates() {
        return stargateMap.values();
    }

    public void addStargate(Stargate stargate) {
        stargateMap.put(stargate.getID(), stargate);
        addStargateJump(stargate.getJumpID(), stargate);
    }
    public void addStargates(Collection<Stargate> stargates) {
        for (Stargate stargate: stargates) {
            stargateMap.put(stargate.getID(),stargate);
            addStargateJump(stargate.getJumpID(), stargate);
        }
    }

    public Collection<Stargate> getStargateJump(Integer jumpID) {
        return stargateJumpMap.get(jumpID);
    }

    private void addStargateJump(Integer jumpID, Stargate stargate) {
        List<Stargate> jump = stargateJumpMap.get(jumpID);
        if (jump == null) {
            jump = new LinkedList<>();
            stargateJumpMap.put(jumpID,jump);
        }
        jump.add(stargate);
    }

}
