package com.sifno.whmapper.server;

//import edu.uci.ics.jung.graph.*;
import com.sifno.stellarmap.*;
import com.sifno.stellarmap.SolarSystem;
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

    private Graph<SolarSystemServer, Link> graph;

    //private Map<String,SolarSystemServer> systemList;

    private void add(SolarSystemServer s1, SolarSystemServer s2) {
        graph.addEdge(new WormholeLink(new Wormhole(s1), new Wormhole(s2)),s1,s2);
    }

    private void addGatewayTunnel(Stargate gw1, Stargate gw2) {
        new StargateLink(gw1, gw2);

     //   graph.containsVertex()
    }

    private void initSolarSystems(Set<SolarSystem> solarSystems) {
        Set<Link> linkSet = new HashSet<>();

        for (SolarSystem system : solarSystems) {
            graph.addVertex(system);
        }

        for (SolarSystem system: solarSystems) {
            for (Stargate stargate : system.getStargates()) {
                SolarSystem oppositeSystem = stargate.getLink().getOpposite(stargate).getSystem();
                if ( solarSystems.contains(oppositeSystem) && linkSet.add(stargate.getLink()))
                    graph.addEdge(stargate.getLink(),system,oppositeSystem);
            }
        }

    }



    public WormholeModel() {
        UndirectedGraph ig = Graphs.synchronizedUndirectedGraph(new UndirectedSparseGraph())  ;

        ObservableGraph og = new ObservableGraph(ig);
        og.addGraphEventListener(evt -> System.out.println("got " + evt));
        this.graph = og;

//        Set<SolarSystemServer> solarSystems = new HashSet<>(NewEden.getSolarSystems(NewEden.getConstellation("Kimotoro")));
//        solarSystems.addAll(NewEden.getSolarSystems(NewEden.getConstellation("Okomon")));
//        solarSystems.addAll(NewEden.getSolarSystems(NewEden.getConstellation("Ihilakken")));

        Set<SolarSystemServer> solarSystems = new HashSet<>(NewEden.getSolarSystems(NewEden.getRegion("The Forge")));

        initSolarSystems(solarSystems);
    }


    public Graph<SolarSystemServer, Link> getGraph() {
        return graph;
    }
}
