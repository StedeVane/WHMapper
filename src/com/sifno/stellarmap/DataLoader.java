package com.sifno.stellarmap;

import com.sifno.stellarmap.dataobject.ConstellationData;
import com.sifno.stellarmap.dataobject.RegionData;
import com.sifno.stellarmap.dataobject.SolarSystemData;
import com.sifno.stellarmap.dataobject.StargateData;

import java.util.Collection;

/**
 * Created by Алёна on 19.08.2015.
 */
public interface DataLoader {

    public RegionData downloadRegion(int regionID);
    public ConstellationData downloadConstellation(int constellationID);
    public SolarSystemData downloadSolarSystem(int solarSystemID);

    public Collection<StargateData> downloadStargates(Collection<Integer> stargatesID);
    public StargateData downloadStargate(int stargateID);

    public Collection<RegionData> downloadRegionsAll();
    public Collection<ConstellationData> downloadConstellationsAll();
    public Collection<SolarSystemData> downloadSolarSystemsAll();
    public Collection<StargateData> downloadStargatesAll();

}
