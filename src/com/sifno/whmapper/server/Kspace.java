package com.sifno.whmapper.server;


import com.sifno.stellarmap.StellarMapInfoCache;
import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.stellarmap.dataobject.Stargate;
import com.sifno.stellarmap.graph.Graph;
import com.sifno.stellarmap.graph.GraphAdapter;
import com.sifno.stellarmap.graph.UndirectedSpareGraph;

public class KSpace extends GraphAdapter<Integer,Integer> {
    //TODO не добавлять wSpace;

    private StellarMapInfoCache info;

    private static KSpace instance;
    public static KSpace getInstance() {
        if (instance == null) {
            instance = new KSpace();
        }
        return instance;
    }


    private KSpace() {
        super(new UndirectedSpareGraph<>());

        ServerDataLoader sdl = new ServerDataLoader();
        info = new StellarMapInfoCache();

        info.addRegions(sdl.loadRegionsAll());
        info.addConstellations(sdl.loadConstellationsAll());
        info.addStarSystems(sdl.loadStarSystemsAll());
        info.addStargates(sdl.loadStargatesAll());



        for (StarSystem starSystem: info.getStarSystems()) {
            if (starSystem.isKSpace())
                addVertex(starSystem.getID());
        }

        for (Stargate stargate: info.getStargates()) {
            if (getEdge(stargate.getJumpID()) == null) {
                Stargate destinationStargate =  info.getStargate(stargate.getDestinationStargateID());
                if (destinationStargate != null) {
                    addEdge(stargate.getJumpID(),stargate.getStarSystemID(),destinationStargate.getStarSystemID());
                }
            }

        }

    }
}
