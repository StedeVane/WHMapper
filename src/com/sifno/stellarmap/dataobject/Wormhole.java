package com.sifno.stellarmap.dataobject;

/**
 * Created by Алёна on 22.01.2016.
 */
public class Wormhole extends Signature {

    private int destinationWormholeID;
    private int jumpID;

    private int wormholeIndex;
    private int wormholeClass;

    public Wormhole() {
        super();
        setScanGroup(ScanGroup.Signature);
        setGroup(Group.Wormhole);
        setType("Wormhole");
    }


}
