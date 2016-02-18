package com.sifno.whmapper.server;

import com.sifno.stellarmap.Pilot;
import com.sifno.stellarmap.dataobject.Signature;
import com.sifno.stellarmap.dataobject.Wormhole;
import com.sifno.stellarmap.graph.*;
import com.sifno.stellarmap.graph.event.GraphEvent;
import com.sifno.stellarmap.graph.event.GraphListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Reality {

//    Map<String,PlanarGraph<Integer,Integer>> graphs;

    public Integer p = null;

    Set<Pilot> pilots;


    KSpace kSpace;
    Graph<Integer,Integer> wSpace;

    PlanarGraph<Integer,Integer> planarGraph;

    Map<Integer,Signature> signatureMap = new HashMap<>();

    private Map<Integer, Pair<Integer>> edges = new HashMap<>();

    public Reality(KSpace kSpace) {
        this.kSpace = kSpace;
        wSpace = new UndirectedSpareGraph<>();
        planarGraph = new PlanarGraph<>(wSpace);
        planarGraph.addGraphListener(new GraphListener<Integer, Integer>() {
            @Override
            public void handleGraphEvent(GraphEvent<Integer, Integer> event) {
                System.out.println(event.getSource()+" "+event.getType());
            }
        });
    }


    public void jump(Integer ss1, Integer ss2) {
        if (wSpace.getEdge(ss1,ss2)== null && kSpace.getEdge(ss1,ss2)==null) {
            connectSystems(ss1,ss2);
        }
    }

    public void location(Integer ss) {
        if (!kSpace.containsVertex(ss))
            planarGraph.addVertex(ss);
    }

    private void connectSystems(Integer ss1, Integer ss2) {

        int wormholeJumpID = GodObject.getFreeWormholeJumpID();

        Wormhole w1 = new Wormhole(ss1);
        w1.setId(GodObject.getFreeSignatureID());

        Wormhole w2 = new Wormhole(ss2);
        w2.setId(GodObject.getFreeSignatureID());

        w1.setDestinationWormholeID(w2.getId());
        w2.setDestinationWormholeID(w1.getId());

        w1.setJumpID(wormholeJumpID);
        w2.setJumpID(wormholeJumpID);

        signatureMap.put(w1.getId(),w1);
        signatureMap.put(w2.getId(),w2);

        planarGraph.addVertex(ss1);
        planarGraph.addVertex(ss2);
        planarGraph.addEdge(wormholeJumpID,ss1,ss2);
    }




    public PlanarGraph<Integer,Integer> getPlanarGraph() {
        Balancer balancer = new Balancer(planarGraph);
        balancer.leadToEquilibrium();
        return planarGraph;
    }



}
