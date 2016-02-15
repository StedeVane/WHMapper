package com.sifno.whmapper.server;


import com.sifno.stellarmap.StellarMapInfoCache;
import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.stellarmap.dataobject.Stargate;
import com.sifno.stellarmap.graphdrawing.Graph;
import com.sifno.stellarmap.graphdrawing.Pair;
import com.sifno.stellarmap.graphdrawing.UndirectedSpareGraph;

import java.util.Collection;

public class Kspace implements Graph<Integer,Integer>{
    //TODO не добавлять wspace;

    private Graph<Integer,Integer> graph = new UndirectedSpareGraph<>();
    private StellarMapInfoCache info;

    private static Kspace instance;
    public static Kspace getInstance() {
        if (instance == null) {
            instance = new Kspace();
        }
        return instance;
    }


    private Kspace() {
        ServerDataLoader sdl = new ServerDataLoader();
        info = new StellarMapInfoCache();

        info.addRegions(sdl.loadRegionsAll());
        info.addConstellations(sdl.loadConstellationsAll());
        info.addStarSystems(sdl.loadStarSystemsAll());
        info.addStargates(sdl.loadStargatesAll());



        for (StarSystem starSystem: info.getStarSystems()) {
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



    @Override
    public void addVertex(Integer vertex) {
        graph.addVertex(vertex);
    }

    @Override
    public void addEdge(Integer edge, Integer v1, Integer v2) {
        graph.addEdge(edge,v1,v2);
    }

    @Override
    public void removeVertex(Integer vertex) {
        graph.removeVertex(vertex);
    }

    @Override
    public void removeEdge(Integer edge) {
        graph.removeEdge(edge);
    }

    @Override
    public Pair<Integer> getEdge(Integer edge) {
        return graph.getEdge(edge);
    }

    @Override
    public Integer getEdge(Integer v1, Integer v2) {
        return graph.getEdge(v1,v2);
    }

    @Override
    public Collection<Integer> getVertices() {
        return getVertices();
    }

    @Override
    public Collection<Integer> getEdges() {
        return getEdges();
    }

    @Override
    public Collection<Integer> getNeighbors(Integer vertex) {
        return getNeighbors(vertex);
    }
}
