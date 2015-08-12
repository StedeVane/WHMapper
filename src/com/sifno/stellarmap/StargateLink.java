package com.sifno.stellarmap;

import com.sifno.stellarmap.Endpoint;
import com.sifno.stellarmap.Link;
import com.sifno.whmapper.server.Stargate;

/**
 * Created by Крочак on 16.07.15.
 */
public class StargateLink extends Link {

    private Stargate gw1 , gw2;

    public StargateLink() {}


    public StargateLink(Stargate gw1, Stargate gw2) {
        if(gw1 != null || gw2 != null) {
            this.gw1 = gw1;
            this.gw2 = gw2;
        } else {
            throw new IllegalArgumentException("Link cannot contain null values");
        }
    }

    @Override
    public Stargate[] getEndpoints() {
        Stargate[] to_return = new Stargate[]{this.gw1, this.gw2};
        return to_return;
    }

    @Override
    public Stargate getSource() {
        return null;
    }

    @Override
    public Stargate getDest() {
        return null;
    }

    @Override
    public boolean isSource(Endpoint endpoint) {
        return false;
    }

    @Override
    public boolean isDest(Endpoint endpoint) {
        return false;
    }

    @Override
    public Stargate getOpposite(Endpoint endpoint) {
        if (!(endpoint instanceof Stargate))
            return null;
        Stargate stargate = (Stargate) endpoint;
        if (stargate == gw1)
            return gw2;
        if (stargate == gw2)
            return gw1;

        return null;
    }

    @Override
    public String toString() {
        return gw1 +" <-> "+gw2;
    }
}
