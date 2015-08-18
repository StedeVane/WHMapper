package com.sifno.stellarmap;


import com.sifno.stellarmap.SolarSystemServer;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Крочак
 * Date: 31.10.14
 * Time: 9:08
 * To change this template use File | Settings | File Templates.
 */
public class Signature implements Serializable {

    private SolarSystemServer system;

    private String id;
    private ScanGroup scanGroup;
    private Group group;
    private String type;


    private double signal;

    private Time time;

    public Signature() {}


    public Signature(SolarSystemServer solarSystem) {
        if (solarSystem != null)
            this.system = solarSystem;
        else
            throw new IllegalArgumentException("Signature cannot contain null values");
    }

    public Signature(Signature signature) {
        system = signature.system;
        id = signature.id;
        scanGroup = signature.scanGroup;
        group = signature.group;
        type = signature.type;
        time = signature.time;
        signal = signature.signal;
    }

    public double getSignal() {
        return signal;
    }

    public void setSignal(double signal) {
        this.signal = signal;
    }


    public ScanGroup getScanGroup() {
        return scanGroup;
    }

    public void setScanGroup(ScanGroup scanGroup) {
        this.scanGroup = scanGroup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SolarSystemServer getSystem() {
        return system;
    }

    public void setSystem(SolarSystemServer system) {
        if (system != null) {
            this.system = system;
        }
        else {
            throw new IllegalArgumentException("Link cannot contain null values");
        }
    }

    public enum Group {
        Wormhole, Gas, Relic, Data, Combat, Ore
    }

    public enum ScanGroup {
        Anomaly, Signature
    }



    public static List<Signature> parse(String dump) {

        List<Signature> result = new ArrayList<>();
        String[] scanningLines = dump.split("\n");

        for (String str :scanningLines) {
            String[] line = str.split("\t");

            if (line.length != 6)
                continue;

            if (!line[0].matches("[A-Z]{3}\\-[0-9]{3}"))
                continue;

            Signature signature = new Signature(new SolarSystemServer());//WormholeMap.currentSystem);
            signature.setId(line[0]);

            if (line[1].equals("Cosmic Anomaly"))
                signature.setScanGroup(ScanGroup.Anomaly);
            else if (line[1].equals("Cosmic Signature"))
                signature.setScanGroup(ScanGroup.Signature);
            else
                continue;

            signature.setType(line[3]);
            signature.setSignal(99);

            if (line[2].equals("Data Site"))
                signature.setGroup(Group.Data);
            else if (line[2].equals("Relic Site"))
                signature.setGroup(Group.Relic);
            else if (line[2].equals("Ore Site"))
                signature.setGroup(Group.Ore);
            else if (line[2].equals("Combat Site"))
                signature.setGroup(Group.Combat);
            else if (line[2].equals("Gas Site"))
                signature.setGroup(Group.Gas);
            else if (line[2].equals("Wormhole")) {
                signature = new Wormhole(signature);
            }

            result.add(signature);
        }

        return  result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Signature)) return false;

        Signature signature = (Signature) o;

        if (!system.equals(signature.system)) return false;
        return id!=null ? id.equals(signature.id)  : false;
      //  return !(id != null ? !id.equals(signature.id) : signature.id != null);

    }

    @Override
    public int hashCode() {
        int result = system.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : super.hashCode());
        return result;
    }
}
