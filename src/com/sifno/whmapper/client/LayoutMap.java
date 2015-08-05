package com.sifno.whmapper.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;
import com.sifno.whmapper.server.SolarSystem;

/**
 * Created by Pavel on 01.08.2015.
 */
@RemoteServiceRelativePath("LayoutMap")
public interface LayoutMap extends RemoteService {
    /**
     * Utility/Convenience class.
     * Use LayoutMap.App.getInstance() to access static instance of LayoutMapAsync
     */
    public static class App {
        private static final LayoutMapAsync ourInstance = (LayoutMapAsync) GWT.create(LayoutMap.class);

        public static LayoutMapAsync getInstance() {
            return ourInstance;
        }
    }

    public MyClass getObject(String str);

    public SolarSystemClient getSolarSystemClient(String name);
}
