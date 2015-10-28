package com.sifno.whmapper.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sifno.stellarmap.DataLoader;
import com.sifno.stellarmap.dataobject.ConstellationData;
import com.sifno.stellarmap.dataobject.RegionData;
import com.sifno.stellarmap.dataobject.SolarSystemData;
import com.sifno.stellarmap.dataobject.StargateData;

import java.util.Collection;

/**
 * Created by Алёна on 28.10.2015.
 */
public class ClientDataLoader implements DataLoader{
    @Override
    public RegionData downloadRegion(int regionID) {
        return null;
    }

    @Override
    public ConstellationData downloadConstellation(int constellationID) {
        return null;
    }

    @Override
    public SolarSystemData downloadSolarSystem(int solarSystemID) {

        SolarSystemData data = null;

        AsyncCallback<SolarSystemData> callback = new AsyncCallback<SolarSystemData>() {
            @Override
            public void onFailure(Throwable caught) {
                System.out.println(caught.getMessage());
            }

            @Override
            public void onSuccess(SolarSystemData result) {
                data = result;
            }
        };

        Server.App.getInstance().getSolarSystem(solarSystemID, callback);



    }

    public void waitCallback(Object obj) {
        while (obj==null) {

        }
    }

    @Override
    public Collection<StargateData> downloadStargates(Collection<Integer> stargatesID) {
        return null;
    }

    @Override
    public StargateData downloadStargate(int stargateID) {
        return null;
    }

    @Override
    public Collection<RegionData> downloadRegionsAll() {
        return null;
    }

    @Override
    public Collection<ConstellationData> downloadConstellationsAll() {
        return null;
    }

    @Override
    public Collection<SolarSystemData> downloadSolarSystemsAll() {
        return null;
    }

    @Override
    public Collection<StargateData> downloadStargatesAll() {
        return null;
    }
}
