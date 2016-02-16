package com.sifno.whmapper.server;

public class GodObject {

    private static int wormholeJumpCount = 0;
    private static int signatureCount = 0;

    public static int getFreeWormholeJumpID() {
        return wormholeJumpCount++;
    }

    public static int getFreeSignatureID() {
        return signatureCount++;
    }

    private static Reality reality = new Reality(KSpace.getInstance());
    public static Reality getReality() {
        return reality;
    }

}