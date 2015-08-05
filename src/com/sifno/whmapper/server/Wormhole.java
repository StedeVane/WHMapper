package com.sifno.whmapper.server;

import com.sifno.whmapper.client.Signature;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Крочак
 * Date: 28.10.14
 * Time: 14:01
 * To change this template use File | Settings | File Templates.
 */
public class Wormhole extends Signature implements Endpoint, Serializable {

    private WormholeJump jump;
    private Index index;

    public Wormhole() {

    }

    public Wormhole(Signature signature) {
        super(signature);

        setType("Unstable Wormhole");
        setGroup(Group.Wormhole);
        jump = new WormholeJump(this);
    }


    public Wormhole(SolarSystem solarSystem) {
        super(solarSystem);

        setType("Unstable Wormhole");
        setGroup(Group.Wormhole);
        setScanGroup(ScanGroup.Signature);
        jump = new WormholeJump(this);
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
