package com.sifno.oldmap;

import com.sifno.stellarmap.dataobject.Constellation;
import com.sifno.stellarmap.dataobject.Region;
import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.stellarmap.dataobject.Stargate;

import java.util.*;

/**
 * Created by Алёна on 18.08.2015.
 */
public class StellarMap {

    DataLoader loader;

    private Map<Integer, com.sifno.oldmap.Region> regionMap = new HashMap<>();
    private Map<Integer, com.sifno.oldmap.Constellation> constellationMap = new HashMap<>();
    private Map<Integer, com.sifno.oldmap.SolarSystem> solarSystemMap = new HashMap<>();
    private Map<Integer, com.sifno.oldmap.Stargate> stargateMap = new HashMap<>();

    //TODO после отладки убрать
    public StellarMap(DataLoader loader) {
        this.loader = loader;

        Set<Region> regionDataSet = new HashSet<>(loader.downloadRegionsAll());
        for (Region data: regionDataSet) {
            com.sifno.oldmap.Region region = new com.sifno.oldmap.Region(this,data);
            regionMap.put(data.getID(),region);
        }
        Set<Constellation> constellationDataSet = new HashSet<>(loader.downloadConstellationsAll());
        for (Constellation data: constellationDataSet) {
            com.sifno.oldmap.Constellation constellation = new com.sifno.oldmap.Constellation(this,data);
            constellationMap.put(data.getID(),constellation);
        }
        Set<StarSystem> solarSystemDataSet = new HashSet<>(loader.downloadStarSystemsAll());
        for (StarSystem data: solarSystemDataSet) {
            com.sifno.oldmap.SolarSystem solarSystem = new com.sifno.oldmap.SolarSystem(this,data);
            solarSystemMap.put(data.getID(),solarSystem);
        }
        Set<Stargate> stargateDataSet = new HashSet<>(loader.downloadStargatesAll());
        for (Stargate data: stargateDataSet) {
            com.sifno.oldmap.Stargate stargate =  new com.sifno.oldmap.Stargate(this,data);
            stargateMap.put(data.getID(),stargate);
        }

    }

    public com.sifno.oldmap.Region getRegion(int regionID) {
        com.sifno.oldmap.Region region = regionMap.get(regionID);
        if (region==null) {
            Region data = loader.downloadRegion(regionID);
            region = new com.sifno.oldmap.Region(this, data);
            regionMap.put(regionID,region);
        }
        return region;
    }

    public com.sifno.oldmap.Constellation getConstellation(int constellationID) {
        com.sifno.oldmap.Constellation constellation = constellationMap.get(constellationID);
        if (constellation == null) {
            Constellation data = loader.downloadConstellation(constellationID);
            constellation = new com.sifno.oldmap.Constellation(this,data);
            constellationMap.put(constellationID,constellation);
        }
        return constellation;
    }

    public com.sifno.oldmap.SolarSystem getSolarSystem(int solarSystemID) {
        com.sifno.oldmap.SolarSystem solarSystem = solarSystemMap.get(solarSystemID);
        if (solarSystem == null) {
            StarSystem data = loader.downloadStarSystem(solarSystemID);
            solarSystem = new com.sifno.oldmap.SolarSystem(this, data);
            solarSystemMap.put(solarSystemID,solarSystem);
        }
        return solarSystem;
    }

    public com.sifno.oldmap.Stargate getStargate(int stargateID) {
        com.sifno.oldmap.Stargate stargate = stargateMap.get(stargateID);
        if (stargate == null) {
            Stargate data = loader.downloadStargate(stargateID);
            stargate = new com.sifno.oldmap.Stargate(this,data);
            stargateMap.put(stargateID,stargate);
        }
        return stargate;
    }

    public Collection<com.sifno.oldmap.Stargate> getStargates(Collection<Integer> stargatesID) {
        Set<com.sifno.oldmap.Stargate> stargates = new HashSet<>();

        Set<Integer> request = new HashSet<>();

        for (Integer i: stargatesID) {
            com.sifno.oldmap.Stargate stargate = stargateMap.get(i);
            if (stargate == null) {
                request.add(i);
            } else {
                stargates.add(stargate);
            }
        }

        Set<Stargate> resultSet = new HashSet<>(loader.downloadStargates(request));

        for (Stargate data: resultSet) {
            com.sifno.oldmap.Stargate stargate = new com.sifno.oldmap.Stargate(this,data);
            stargateMap.put(data.getID(),stargate);
            stargates.add(stargate);
        }

        return stargates;
    }

    public StargateLink getStargateLink(int stargate1ID, int stargate2ID) {

        com.sifno.oldmap.Stargate stargate1 = getStargate(stargate1ID);
        com.sifno.oldmap.Stargate stargate2 = getStargate(stargate2ID);

        StargateLink link;

        if (stargate1.getData().getDestinationStargateID() != stargate2ID
                || stargate2.getData().getDestinationStargateID() != stargate1ID) {
            try {
                throw new Exception("Data corruption. StargateID: "+stargate1ID + ", " + stargate2ID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (stargate1.getLink()!= null) {
            link = stargate1.getLink();
        } else if (stargate2.getLink() != null) {
            link = stargate2.getLink();
        } else {
            link = new StargateLink(stargate1, stargate2);
        }
        return link;
    }

}
