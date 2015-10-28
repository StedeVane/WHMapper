package com.sifno.stellarmap;

import com.sifno.stellarmap.dataobject.ConstellationData;
import com.sifno.stellarmap.dataobject.RegionData;
import com.sifno.stellarmap.dataobject.SolarSystemData;
import com.sifno.stellarmap.dataobject.StargateData;
import sun.awt.dnd.SunDropTargetContextPeer;

import java.util.*;

/**
 * Created by Алёна on 18.08.2015.
 */
public class StellarMap {

    DataLoader loader;

    private Map<Integer, Region> regionMap = new HashMap<>();
    private Map<Integer, Constellation> constellationMap = new HashMap<>();
    private Map<Integer, SolarSystem> solarSystemMap = new HashMap<>();
    private Map<Integer, Stargate> stargateMap = new HashMap<>();

    //TODO после отладки убрать
    public StellarMap(DataLoader loader) {
        this.loader = loader;

        Set<RegionData> regionDataSet = new HashSet<>(loader.downloadRegionsAll());
        for (RegionData data: regionDataSet) {
            Region region = new Region(this,data);
            regionMap.put(data.getID(),region);
        }
        Set<ConstellationData> constellationDataSet = new HashSet<>(loader.downloadConstellationsAll());
        for (ConstellationData data: constellationDataSet) {
            Constellation constellation = new Constellation(this,data);
            constellationMap.put(data.getID(),constellation);
        }
        Set<SolarSystemData> solarSystemDataSet = new HashSet<>(loader.downloadSolarSystemsAll());
        for (SolarSystemData data: solarSystemDataSet) {
            SolarSystem solarSystem = new SolarSystem(this,data);
            solarSystemMap.put(data.getID(),solarSystem);
        }
        Set<StargateData> stargateDataSet = new HashSet<>(loader.downloadStargatesAll());
        for (StargateData data: stargateDataSet) {
            Stargate stargate =  new Stargate(this,data);
            stargateMap.put(data.getID(),stargate);
        }

    }

    public Region getRegion(int regionID) {
        Region region = regionMap.get(regionID);
        if (region==null) {
            RegionData data = loader.downloadRegion(regionID);
            region = new Region(this, data);
            regionMap.put(regionID,region);
        }
        return region;
    }

    public Constellation getConstellation(int constellationID) {
        Constellation constellation = constellationMap.get(constellationID);
        if (constellation == null) {
            ConstellationData data = loader.downloadConstellation(constellationID);
            constellation = new Constellation(this,data);
            constellationMap.put(constellationID,constellation);
        }
        return constellation;
    }

    public SolarSystem getSolarSystem(int solarSystemID) {
        SolarSystem solarSystem = solarSystemMap.get(solarSystemID);
        if (solarSystem == null) {
            SolarSystemData data = loader.downloadSolarSystem(solarSystemID);
            solarSystem = new SolarSystem(this, data);
            solarSystemMap.put(solarSystemID,solarSystem);
        }
        return solarSystem;
    }

    public Stargate getStargate(int stargateID) {
        Stargate stargate = stargateMap.get(stargateID);
        if (stargate == null) {
            StargateData data = loader.downloadStargate(stargateID);
            stargate = new Stargate(this,data);
            stargateMap.put(stargateID,stargate);
        }
        return stargate;
    }

    public Collection<Stargate> getStargates(Collection<Integer> stargatesID) {
        Set<Stargate> stargates = new HashSet<>();

        Set<Integer> request = new HashSet<>();

        for (Integer i: stargatesID) {
            Stargate stargate = stargateMap.get(i);
            if (stargate == null) {
                request.add(i);
            } else {
                stargates.add(stargate);
            }
        }

        Set<StargateData> resultSet = new HashSet<>(loader.downloadStargates(request));

        for (StargateData data: resultSet) {
            Stargate stargate = new Stargate(this,data);
            stargateMap.put(data.getID(),stargate);
            stargates.add(stargate);
        }

        return stargates;
    }

    public StargateLink getStargateLink(int stargate1ID, int stargate2ID) {

        Stargate stargate1 = getStargate(stargate1ID);
        Stargate stargate2 = getStargate(stargate2ID);

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
