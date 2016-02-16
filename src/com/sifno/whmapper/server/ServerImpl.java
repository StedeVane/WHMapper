package com.sifno.whmapper.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.stellarmap.graph.Balancer;
import com.sifno.stellarmap.graph.PlanarGraphImpl;
import com.sifno.stellarmap.graph.UndirectedSpareGraph;
import com.sifno.whmapper.client.Server;
import com.sifno.whmapper.client.MyClass;


/**
 * Created by Pavel on 01.08.2015.
 */
public class ServerImpl extends RemoteServiceServlet implements Server {
    public MyClass getObject(String str) {
        return new MyClass(str);
    }

    @Override
    public PlanarGraphImpl<Integer, Integer> updateRequest() {
        String str = this.getThreadLocalRequest().getHeader("eve_solarsystemid");

        PlanarGraphImpl<Integer,Integer> graph  = new PlanarGraphImpl<>(new UndirectedSpareGraph<Integer,Integer>());
        graph.addVertex(Integer.valueOf(str));
        Balancer<Integer,Integer> balancer = new Balancer<>(graph);
        balancer.leadToEquilibrium();
        return graph;

    }

    @Override
    public StarSystem getSolarSystem(int id) {



        System.out.println(this.getThreadLocalRequest().getHeaderNames());
        return null;
      // return null;
    }

    @Override
    public PlanarGraphImpl<Integer, Integer> testUpdate(String systemID) {
        Reality reality = GodObject.getReality();


        if (reality.p == null) {
            reality.p = Integer.valueOf(systemID);
            reality.location(Integer.valueOf(systemID));
        } else if (!Integer.valueOf(systemID).equals(reality.p)) {
            reality.jump(reality.p, Integer.valueOf(systemID) );
            reality.p = Integer.valueOf(systemID);
        }

        return reality.getPlanarGraphImpl();
    }
}

