package com.sifno.stellarmap;

import java.io.Serializable;

/**
 * Created by Pavel on 26.07.2015.
 */
public interface Location extends Serializable {

    public String getName();
    public void setName(String name);

    public int getId();
    public void setId(int id);
}
