package com.sifno.stellarmap;

import java.util.Collection;

/**
 * Created by Алёна on 12.08.2015.
 */
public interface SolarSystem extends Location{
    public Constellation getConstellation();
    public void setConstellation(Constellation constellation);

    public Region getRegion();

    public double getSecurity();
    public void setSecurity(double security);

    public String getClassType();
    public void setClassType(String classType);

    public double getLuminosity();
    public void setLuminosity(double luminosity);

    public int getSunType();
    public void setSunType(int sunType);

    public void add(Stargate stargate);

    public Collection<Stargate> getStargates();

    public static SolarSystemClient createClient(SolarSystem solarSystem) {
        SolarSystemClient to_return = new SolarSystemClient();

        

        return
    }
}
