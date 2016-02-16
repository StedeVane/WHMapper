package com.sifno.whmapper.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.stellarmap.graph.PlanarGraphImpl;

public interface ServerAsync {


    void getSolarSystem(int id, AsyncCallback<StarSystem> async);
    void updateRequest(AsyncCallback<PlanarGraphImpl<Integer, Integer>> async);

    void testUpdate(String systemID, AsyncCallback<PlanarGraphImpl<Integer,Integer>> async);
}
