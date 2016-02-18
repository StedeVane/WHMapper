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

    public int getDestinationWormholeID() {
        return destinationWormholeID;
    }

    public void setDestinationWormholeID(int destinationWormholeID) {
        this.destinationWormholeID = destinationWormholeID;
    }

    public int getJumpID() {
        return jumpID;
    }

    public void setJumpID(int jumpID) {
        this.jumpID = jumpID;
    }

    public int getWormholeIndex() {
        return wormholeIndex;
    }

    public void setWormholeIndex(int wormholeIndex) {
        this.wormholeIndex = wormholeIndex;
    }

    public int getWormholeClass() {
        return wormholeClass;
    }

    public void setWormholeClass(int wormholeClass) {
        this.wormholeClass = wormholeClass;
    }
}
