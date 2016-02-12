package com.sifno.stellarmap.dataobject;


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

    public Wormhole(Signature signature) {
        super(signature);
        setScanGroup(ScanGroup.Signature);
        setGroup(Group.Wormhole);
        setType("Wormhole");
    }

}
