package com.sifno.stellarmap.dataobject;


public class Wormhole extends Signature {

    private int destinationWormholeID;
    private int jumpID;

    private int wormholeIndex;
    private int wormholeClass;

    private void init() {
        setScanGroup(ScanGroup.Signature);
        setGroup(Group.Wormhole);
        setType("Wormhole");
    }

    public Wormhole() {
        init();
    }

    public Wormhole(Integer starSystemID) {
        super(starSystemID);
        init();
    }

    public Wormhole(Signature signature) {
        super(signature);
        init();
    }

}
