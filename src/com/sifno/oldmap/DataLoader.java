package com.sifno.oldmap;

import com.sifno.stellarmap.dataobject.Constellation;
import com.sifno.stellarmap.dataobject.Region;
import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.stellarmap.dataobject.Stargate;

import java.util.Collection;

/**
 * Created by Алёна on 19.08.2015.
 */
public interface DataLoader {

    public Region downloadRegion(int regionID);
    public Constellation downloadConstellation(int constellationID);
    public StarSystem downloadStarSystem(int starSystemID);

    public Collection<Stargate> downloadStargates(Collection<Integer> stargatesID);
    public Stargate downloadStargate(int stargateID);

    public Collection<Region> downloadRegionsAll();
    public Collection<Constellation> downloadConstellationsAll();
    public Collection<StarSystem> downloadStarSystemsAll();
    public Collection<Stargate> downloadStargatesAll();

}
