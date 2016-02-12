package com.sifno.stellarmap.dataobject;


import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signature {

    private static int freeID = 0;

    private int id;

    private String name;
    private int starSystemID;

    private ScanGroup scanGroup;
    private Group group;
    private String type;
    private Time time;
    private double signal;

    public Signature() {
        this(freeID++); //TODO скорее всего проблемы с многопточностью
    }

    protected Signature(Signature signature) {
        setId(signature.getId());
        setName(signature.getName());
        setStarSystemID(signature.getStarSystemID());
        setScanGroup(signature.getScanGroup());
        setGroup(signature.getGroup());
        setType(signature.getType());
        setTime(signature.getTime());
        setSignal(signature.getSignal());
    }

    public Signature(int id) {
        this.id = id;
    }

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

    public int getStarSystemID() {
        return starSystemID;
    }

    public void setStarSystemID(int starSystemID) {
        this.starSystemID = starSystemID;
    }

    public ScanGroup getScanGroup() {
        return scanGroup;
    }

    public void setScanGroup(ScanGroup scanGroup) {
        this.scanGroup = scanGroup;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public double getSignal() {
        return signal;
    }

    public void setSignal(double signal) {
        this.signal = signal;
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

//            Signature signature = null; //new Signature(new StarSystem());//WormholeMap.currentSystem);
//            signature.setId(line[0]);
            Signature signature = new Signature();
            signature.setName(line[0]);

            if (line[1].equals("Cosmic Anomaly"))
                signature.setScanGroup(ScanGroup.Anomaly);
            else if (line[1].equals("Cosmic Signature"))
                signature.setScanGroup(ScanGroup.Signature);
            else
                continue;

            signature.setType(line[3]);

            Pattern p = Pattern.compile("([0-9])+.([0-9]+)%");
            Matcher m = p.matcher(line[4]);
            if (m.find()) {
                signature.setSignal(Double.valueOf(m.group(1)+"."+m.group(2)));
            }

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
}


