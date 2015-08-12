package com.sifno.whmapper.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sifno.stellarmap.SolarSystemClient;

/**
 * Created by Pavel on 01.08.2015.
 */
public interface ServerAsync {
    void getObject(String str, AsyncCallback<MyClass> async);

    void getSolarSystemClient(String name, AsyncCallback<SolarSystemClient> async);
}
