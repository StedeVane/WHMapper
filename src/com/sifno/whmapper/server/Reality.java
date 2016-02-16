package com.sifno.whmapper.server;

import com.sifno.stellarmap.Pilot;
import com.sifno.stellarmap.dataobject.Signature;
import com.sifno.stellarmap.dataobject.Wormhole;
import com.sifno.stellarmap.graph.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Reality {

//    Map<String,PlanarGraphImpl<Integer,Integer>> graphs;

    public Integer p = null;

    Set<Pilot> pilots;


    KSpace kSpace;
    Graph<Integer,Integer> wSpace;

    PlanarGraphImpl<Integer,Integer> planarGraphImpl;

    Map<Integer,Signature> signatureMap = new HashMap<>();

    private Map<Integer, Pair<Integer>> edges = new HashMap<>();

    public Reality(KSpace kSpace) {
        this.kSpace = kSpace;
        wSpace = new UndirectedSpareGraph<>();
        planarGraphImpl = new PlanarGraphImpl<>(wSpace);
    }


    public void jump(Integer ss1, Integer ss2) {
        if (wSpace.getEdge(ss1,ss2)== null || kSpace.getEdge(ss1,ss2)==null) {
            connectSystems(ss1,ss2);
        }
    }

    public void location(Integer ss) {
        if (!kSpace.containsVertex(ss))
            planarGraphImpl.addVertex(ss);
    }

    private void connectSystems(Integer ss1, Integer ss2) {
        Wormhole w1 = new Wormhole(ss1);
        w1.setId(GodObject.getFreeSignatureID());

        Wormhole w2 = new Wormhole(ss2);
        w2.setId(GodObject.getFreeSignatureID());

        int wormholeJumpID = GodObject.getFreeWormholeJumpID();

        signatureMap.put(w1.getId(),w1);
        signatureMap.put(w2.getId(),w2);

        planarGraphImpl.addVertex(w1.getStarSystemID());
        planarGraphImpl.addVertex(w2.getStarSystemID());
        planarGraphImpl.addEdge(wormholeJumpID,ss1,ss2);
    }




    public PlanarGraphImpl<Integer,Integer> getPlanarGraphImpl() {
        Balancer balancer = new Balancer(planarGraphImpl);
        balancer.leadToEquilibrium();
        return planarGraphImpl;
    }



}
