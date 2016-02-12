package com.sifno.whmapper.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sifno.stellarmap.dataobject.StarSystem;

public interface ServerAsync {


    void getSolarSystem(int id, AsyncCallback<StarSystem> async);

}
