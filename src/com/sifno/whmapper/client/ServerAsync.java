package com.sifno.whmapper.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sifno.stellarmap.dataobject.SolarSystemData;


/**
 * Created by Pavel on 01.08.2015.
 */
public interface ServerAsync {


    void getSolarSystem(int id, AsyncCallback<SolarSystemData> async);

}
