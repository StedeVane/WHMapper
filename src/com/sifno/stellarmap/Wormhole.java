package com.sifno.stellarmap;

import com.sifno.stellarmap.SolarSystemServer;
import com.sifno.whmapper.server.Index;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Крочак
 * Date: 28.10.14
 * Time: 14:01
 * To change this template use File | Settings | File Templates.
 */
public class Wormhole extends Signature implements Endpoint, Serializable {

    private WormholeLink jump;
    private Index index;

    public Wormhole() {

    }

    public Wormhole(Signature signature) {
        super(signature);

        setType("Unstable Wormhole");
        setGroup(Group.Wormhole);
        jump = new WormholeLink(this);
    }


    public Wormhole(SolarSystemServer solarSystem) {
        super(solarSystem);

        setType("Unstable Wormhole");
        setGroup(Group.Wormhole);
        setScanGroup(ScanGroup.Signature);
        jump = new WormholeLink(this);
    }


    public Index getIndex() {
        return index;
    }

    public void setIndex(Index index) {
        this.index = index;
    }

    @Override
    public Endpoint getOpposite() {
        if (jump == null)
            return null;

        return jump.getOpposite(this);
    }

}
