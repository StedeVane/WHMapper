package com.sifno.stellarmap;

import com.sifno.stellarmap.Endpoint;
import com.sifno.stellarmap.Link;
import com.sifno.whmapper.server.Wormhole;

/**
 * Created by Крочак on 16.07.15.
 */
public class WormholeLink extends Link {

    Wormhole wh1, wh2;


    int size;


    public boolean isExplored() {
        return size==2;
    }


    public WormholeLink() {}

    public WormholeLink(Wormhole wh1) {
        this(wh1, null);
        size = 1;
    }

    public WormholeLink(Wormhole wh1, Wormhole wh2) {
        this.wh1 = wh1;
        this.wh2 = wh2;
        size = 2;
    }

    public boolean add(Wormhole wormhole) {
        if (size == 2) return false;

        if (wh1 == wormhole || wh2 == wormhole)
            return false;

        if (wh1 == null) {
            wh1 = wormhole;
            size++;
            return true;
        }

        if (wh2 == null) {
            wh2 = wormhole;
            size++;
            return true;
        }

        return false;
    }

    @Override
    public Wormhole[] getEndpoints() {
        Wormhole[] to_return = new Wormhole[]{this.wh1, this.wh2};
        return to_return;
    }

    @Override
    public Wormhole getSource() {
//        if (wh1!=null && wh1.getIndex() != NewEden.K162)
//            return wh1;
//
//        if (wh2!=null && wh2.getIndex() != NewEden.K162)
//            return wh2;
//
        return null;
    }

    @Override
    public Wormhole getDest() {
//        if (wh1!=null && wh1.getIndex() == NewEden.K162)
//            return wh1;
//
//        if (wh2!=null && wh2.getIndex() == NewEden.K162)
//            return wh2;

        return null;
    }

    @Override
    public boolean isSource(Endpoint endpoint) {
//        if (!(endpoint instanceof Wormhole))
//            return false;
//
//        Wormhole wormhole = (Wormhole) endpoint;
//        if (wormhole.getIndex() == null)
//            return false;
//
//        return wormhole.getIndex() !=  NewEden.K162;
        return false;
    }

    @Override
    public boolean isDest(Endpoint endpoint) {
//        if (!(endpoint instanceof Wormhole))
//            return false;
//
//        Wormhole wormhole = (Wormhole) endpoint;
//        if (wormhole.getIndex() == null)
//            return false;
//
//        return wormhole.getIndex() == NewEden.K162;
        return false;
    }

    @Override
    public Wormhole getOpposite(Endpoint endpoint) {
        if (!(endpoint instanceof Wormhole))
            return null;
        Wormhole wormhole = (Wormhole) endpoint;
        if (wh1 != null && wormhole == wh1)
            return wh2;
        if (wh2 != null && wormhole == wh2)
            return wh1;

        return null;
    }

    @Override
    public String toString() {
        return wh1.getSystem() + " <-> " + wh2.getSystem();
    }
}
