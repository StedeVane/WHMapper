package com.sifno.whmapper.server;

//import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.ObservableGraph;
import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.Graphs;

import java.util.*;

/**
 * Created by Крочак on 29.06.15.
 */

public class WormholeModel {

    private Graph<SolarSystem, Jump> graph;

    //private Map<String,SolarSystem> systemList;

    private void add(SolarSystem s1, SolarSystem s2) {
        graph.addEdge(new WormholeJump(new Wormhole(s1), new Wormhole(s2)),s1,s2);
    }

    private void addGatewayTunnel(Stargate gw1, Stargate gw2) {
        new StargateJump(gw1, gw2);

     //   graph.containsVertex()
    }

    private void initSolarSystems(Set<SolarSystem> solarSystems) {
        Set<Jump> jumpSet = new HashSet<>();

        for (SolarSystem system : solarSystems) {
            graph.addVertex(system);
        }

        for (SolarSystem system: solarSystems) {
            for (Stargate stargate : system.getStargates()) {
                SolarSystem oppositeSystem = stargate.getJump().getOpposite(stargate).getSystem();
                if ( solarSystems.contains(oppositeSystem) && jumpSet.add(stargate.getJump()))
                    graph.addEdge(stargate.getJump(),system,oppositeSystem);
            }
        }

    }



    public WormholeModel() {
        UndirectedGraph ig = Graphs.synchronizedUndirectedGraph(new UndirectedSparseGraph())  ;

        ObservableGraph og = new ObservableGraph(ig);
        og.addGraphEventListener(evt -> System.out.println("got " + evt));
        this.graph = og;

//        Set<SolarSystem> solarSystems = new HashSet<>(NewEden.getSolarSystems(NewEden.getConstellation("Kimotoro")));
//        solarSystems.addAll(NewEden.getSolarSystems(NewEden.getConstellation("Okomon")));
//        solarSystems.addAll(NewEden.getSolarSystems(NewEden.getConstellation("Ihilakken")));

        Set<SolarSystem> solarSystems = new HashSet<>(NewEden.getSolarSystems(NewEden.getRegion("The Forge")));

        initSolarSystems(solarSystems);
    }


    public Graph<SolarSystem, Jump> getGraph() {
        return graph;
    }
}
