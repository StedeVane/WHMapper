package com.sifno.whmapper.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sifno.whmapper.client.Server;
import com.sifno.whmapper.client.MyClass;
import com.sifno.whmapper.client.SolarSystemClient;

/**
 * Created by Pavel on 01.08.2015.
 */
public class ServerImpl extends RemoteServiceServlet implements Server {
    public MyClass getObject(String str) {
        return new MyClass(str);
    }

    @Override
    public SolarSystemClient getSolarSystemClient(String name) {
        SolarSystem result = NewEden.getSolarSystem(name);
        return result.getSolarSystemClient();
    }
}

