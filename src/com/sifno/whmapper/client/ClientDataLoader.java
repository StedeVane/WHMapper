package com.sifno.whmapper.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sifno.oldmap.DataLoader;
import com.sifno.stellarmap.dataobject.Constellation;
import com.sifno.stellarmap.dataobject.Region;
import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.stellarmap.dataobject.Stargate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Алёна on 28.10.2015.
 */
public class ClientDataLoader implements DataLoader{


    @Override
    public Region downloadRegion(int regionID) {
        return null;
    }

    @Override
    public Constellation downloadConstellation(int constellationID) {
        return null;
    }

    @Override
    public StarSystem downloadStarSystem(int solarSystemID) {

        List<StarSystem> data = new ArrayList<>();

        AsyncCallback<StarSystem> callback = new AsyncCallback<StarSystem>() {
            @Override
            public void onFailure(Throwable caught) {
                System.out.println(caught.getMessage());
                data.add(null);
            }

            @Override
            public void onSuccess(StarSystem result) {
                data.add(result);
            }
        };

        Server.App.getInstance().getSolarSystem(solarSystemID, callback);


        return null;

    }



    @Override
    public Collection<Stargate> downloadStargates(Collection<Integer> stargatesID) {
        return null;
    }

    @Override
    public Stargate downloadStargate(int stargateID) {
        return null;
    }

    @Override
    public Collection<Region> downloadRegionsAll() {
        return null;
    }

    @Override
    public Collection<Constellation> downloadConstellationsAll() {
        return null;
    }

    @Override
    public Collection<StarSystem> downloadStarSystemsAll() {
        return null;
    }

    @Override
    public Collection<Stargate> downloadStargatesAll() {
        return null;
    }
}
