package com.sifno.stellarmap;

/**
 * Created by Алёна on 18.08.2015.
 */
public interface StellarMap {

    public Region getRegion(int locationID);
    public Constellation getConstellation(int locationID);

}
