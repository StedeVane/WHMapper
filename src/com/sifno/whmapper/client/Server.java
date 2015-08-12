package com.sifno.whmapper.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;

/**
 * Created by Pavel on 01.08.2015.
 */
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

    public MyClass getObject(String str);

    public SolarSystemClient getSolarSystemClient(String name);
}