package com.sifno.whmapper.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sifno.whmapper.server.DataLoader;
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
    public Region loadRegion(int regionID) {
        return null;
    }

    @Override
    public Constellation loadConstellation(int constellationID) {
        return null;
    }

    @Override
    public StarSystem loadStarSystem(int solarSystemID) {

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
    public Collection<Stargate> loadStargates(Collection<Integer> stargatesID) {
        return null;
    }

    @Override
    public Stargate loadStargate(int stargateID) {
        return null;
    }

    @Override
    public Collection<Region> loadRegionsAll() {
        return null;
    }

    @Override
    public Collection<Constellation> loadConstellationsAll() {
        return null;
    }

    @Override
    public Collection<StarSystem> loadStarSystemsAll() {
        return null;
    }

    @Override
    public Collection<Stargate> loadStargatesAll() {
        return null;
    }
}
