package com.sifno.whmapper.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sifno.stellarmap.SolarSystem;
import com.sifno.stellarmap.SolarSystemServer;
import com.sifno.whmapper.client.Server;
import com.sifno.whmapper.client.MyClass;
import com.sifno.stellarmap.SolarSystemClient;

/**
 * Created by Pavel on 01.08.2015.
 */
public class ServerImpl extends RemoteServiceServlet implements Server {
    public MyClass getObject(String str) {
        return new MyClass(str);
    }

    @Override
    public SolarSystemClient getSolarSystem(String name) {

        SolarSystem solarSystem

        SolarSystemServer result = NewEden.getSolarSystem(name);
        return result.getSolarSystemClient();
    }
}

