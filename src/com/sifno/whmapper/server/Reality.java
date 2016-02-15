package com.sifno.whmapper.server;

import com.sifno.stellarmap.Pilot;
import com.sifno.stellarmap.dataobject.Signature;
import com.sifno.stellarmap.dataobject.Wormhole;
import com.sifno.stellarmap.graphdrawing.Graph;
import com.sifno.stellarmap.graphdrawing.Pair;
import com.sifno.stellarmap.graphdrawing.PlanarGraph;
import com.sifno.stellarmap.graphdrawing.UndirectedSpareGraph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Reality {

//    Map<String,PlanarGraph<Integer,Integer>> graphs;

    Set<Pilot> pilots;

    Kspace kspace;
    Graph<Integer,Integer> wspace;

    Map<PlanarGraph<Integer,Integer> planarGraph;

    Map<Integer,Signature> signatureMap = new HashMap<>();

    private Map<Integer, Pair<Integer>> edges = new HashMap<>();

    public Reality(Kspace kspace) {
        this.kspace = kspace;
        wspace = new UndirectedSpareGraph<>();
    }


    public void jump(Integer ss1, Integer ss2) {
        if (wspace.getEdge(ss1,ss2)== null || kspace.getEdge(ss1,ss2)==null) {
            connectSystems(ss1,ss2);
        }
    }

    private void connectSystems(Integer ss1, Integer ss2) {
        Wormhole w1 = new Wormhole(ss1);
        w1.setId(GodObject.getFreeSignatureID());

        Wormhole w2 = new Wormhole(ss2);
        w2.setId(GodObject.getFreeSignatureID());

        int wormholeJumpID = GodObject.getFreeWormholeJumpID();

        signatureMap.put(w1.getId(),w1);
        signatureMap.put(w2.getId(),w2);

        wspace.addVertex(w1.getStarSystemID());
        wspace.addVertex(w2.getStarSystemID());
        wspace.addEdge(wormholeJumpID,ss1,ss2);
    }

    private void addVertex(Integer vertex) {

    }

    private void addEdge(Integer edge, Integer v1, Integer v2) {

    }


    public PlanarGraph<Integer,Integer> getPlanarGraph() {
        return new PlanarGraph<>(wspace);
    }



}
