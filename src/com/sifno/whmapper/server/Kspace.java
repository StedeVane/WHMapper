package com.sifno.whmapper.server;


import com.sifno.stellarmap.StellarMapInfoCache;
import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.stellarmap.dataobject.Stargate;
import com.sifno.stellarmap.graph.Graph;
import com.sifno.stellarmap.graph.GraphAdapter;
import com.sifno.stellarmap.graph.UndirectedSpareGraph;

public class KSpace extends GraphAdapter<Integer,Integer> {
    //TODO не добавлять wSpace;

    private Graph<Integer,Integer> graph = new UndirectedSpareGraph<>();
    private StellarMapInfoCache info;

    private static KSpace instance;
    public static KSpace getInstance() {
        if (instance == null) {
            instance = new KSpace();
        }
        return instance;
    }


    private KSpace() {
        ServerDataLoader sdl = new ServerDataLoader();
        info = new StellarMapInfoCache();

        info.addRegions(sdl.loadRegionsAll());
        info.addConstellations(sdl.loadConstellationsAll());
        info.addStarSystems(sdl.loadStarSystemsAll());
        info.addStargates(sdl.loadStargatesAll());



        for (StarSystem starSystem: info.getStarSystems()) {
            if (starSystem.isKSpace())
                graph.addVertex(starSystem.getID());
        }

        for (Stargate stargate: info.getStargates()) {
            if (graph.getEdge(stargate.getJumpID()) == null) {
                Stargate destinationStargate =  info.getStargate(stargate.getDestinationStargateID());
                if (destinationStargate != null) {
                    graph.addEdge(stargate.getJumpID(),stargate.getStarSystemID(),destinationStargate.getStarSystemID());
                }
            }

        }

    }
}
