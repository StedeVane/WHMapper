package com.sifno.whmapper.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.sifno.stellarmap.dataobject.SolarSystemData;
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
    public SolarSystemData getSolarSystem(int id) {

        System.out.println(this.getThreadLocalRequest().getHeaderNames());
        return null;
      // return null;
    }
}

