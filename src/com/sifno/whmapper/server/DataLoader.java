package com.sifno.whmapper.server;

import com.sifno.stellarmap.dataobject.Constellation;
import com.sifno.stellarmap.dataobject.Region;
import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.stellarmap.dataobject.Stargate;

import java.util.Collection;

/**
 * Created by Алёна on 19.08.2015.
 */
public interface DataLoader {

    public Region loadRegion(int regionID);
    public Constellation loadConstellation(int constellationID);
    public StarSystem loadStarSystem(int starSystemID);

    public Collection<Stargate> loadStargates(Collection<Integer> stargatesID);
    public Stargate loadStargate(int stargateID);

    public Collection<Region> loadRegionsAll();
    public Collection<Constellation> loadConstellationsAll();
    public Collection<StarSystem> loadStarSystemsAll();
    public Collection<Stargate> loadStargatesAll();

}
