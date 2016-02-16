package com.sifno.whmapper.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;
import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.stellarmap.graph.PlanarGraphImpl;

@RemoteServiceRelativePath("LayoutMap")
public interface Server extends RemoteService {

    /**
     * Utility/Convenience class.
     * Use Server.App.getInstance() to access static instance of LayoutMapAsync
     */
    public static class App {
        private static final ServerAsync ourInstance = (ServerAsync) GWT.create(Server.class);

        public static ServerAsync getInstance() {
            return ourInstance;
        }
    }

    public PlanarGraphImpl<Integer,Integer> updateRequest();
    public StarSystem getSolarSystem(int id);

    public PlanarGraphImpl<Integer, Integer> testUpdate(String systemID);


}
