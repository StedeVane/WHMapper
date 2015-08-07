package com.sifno.whmapper.client;




import java.io.Serializable;
import java.util.Map;

/**
 * Created by Pavel on 02.08.2015.
 */
public class SolarSystemClient implements Serializable {

    private int id;
    private String name = "default";
 //   private ConstellationClietn constellation;
 //   private RegionClient region;
    private double security;

    private double luminosity;
    private int sunType;

    private String classType = "xxx";

    public SolarSystemClient() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSecurity() {
        return security;
    }

    public void setSecurity(double security) {
        this.security = security;
    }

    public double getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(double luminosity) {
        this.luminosity = luminosity;
    }

    public int getSunType() {
        return sunType;
    }

    public void setSunType(int sunType) {
        this.sunType = sunType;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }
}
