package com.sifno.stellarmap.dataobject;

import java.io.Serializable;

/**
 * Created by Алёна on 18.08.2015.
 */
public interface Location extends Serializable {
    public String getName();
    public void setName(String name);

    public int getID();
    public void setID(int id);
}
