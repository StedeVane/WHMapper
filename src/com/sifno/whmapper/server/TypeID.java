package com.sifno.whmapper.server;

import java.util.Map;

/**
 * Created by Алёна on 23.07.2015.
 */
public class TypeID {

    private Integer iconID;
    private Integer raceID;
    private Map <Integer,Map> traits;
    private Integer groupID;
    private Double mass;
    private Translation description;
    private Boolean published;
    private Map <Integer,Map> masteries;
    private Double capacity;
    private Double volume;
    private Integer soundID;

    private Integer graphicID;
    private Translation name;
    private Integer factionID;
    private String sofDnaAddition;
    private Double radius;
    private Integer portionSize;
    private String sofFactionName;
    private Double basePrice;
    private Integer marketGroupID;
    private int id;

    public Integer getIconID() {
        return iconID;
    }

    public void setIconID(Integer iconID) {
        this.iconID = iconID;
    }

    public Integer getRaceID() {
        return raceID;
    }

    public void setRaceID(Integer raceID) {
        this.raceID = raceID;
    }

    public Map<Integer, Map> getTraits() {
        return traits;
    }

    public void setTraits(Map<Integer, Map> traits) {
        this.traits = traits;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Translation getDescription() {
        return description;
    }

    public void setDescription(Translation description) {
        this.description = description;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Map<Integer, Map> getMasteries() {
        return masteries;
    }

    public void setMasteries(Map<Integer, Map> masteries) {
        this.masteries = masteries;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Integer getSoundID() {
        return soundID;
    }

    public void setSoundID(Integer soundID) {
        this.soundID = soundID;
    }

    public Integer getGraphicID() {
        return graphicID;
    }

    public void setGraphicID(Integer graphicID) {
        this.graphicID = graphicID;
    }

    public Translation getName() {
        return name;
    }

    public void setName(Translation name) {
        this.name = name;
    }

    public Integer getFactionID() {
        return factionID;
    }

    public void setFactionID(Integer factionID) {
        this.factionID = factionID;
    }

    public String getSofDnaAddition() {
        return sofDnaAddition;
    }

    public void setSofDnaAddition(String sofDnaAddition) {
        this.sofDnaAddition = sofDnaAddition;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Integer getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(Integer portionSize) {
        this.portionSize = portionSize;
    }

    public String getSofFactionName() {
        return sofFactionName;
    }

    public void setSofFactionName(String sofFactionName) {
        this.sofFactionName = sofFactionName;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getMarketGroupID() {
        return marketGroupID;
    }

    public void setMarketGroupID(Integer marketGroupID) {
        this.marketGroupID = marketGroupID;
    }


    public TypeID(int id,Map<String,Object> map) {

        this.id = id;

        for (String str : map.keySet()) {
            switch (str) {
                case "iconID": iconID = (Integer) map.get("iconID");
                    break;
                case "raceID": raceID = (Integer) map.get("raceID");
                    break;
                case "traits": traits = (Map) map.get("traits");
                    break;
                case "groupID": groupID = (Integer) map.get("groupID");
                    break;
                case "mass": mass = ((Number) map.get("mass")).doubleValue();
                    break;
                case "description": description = new Translation((Map)map.get("description"));
                    break;
                case "published": published = (Boolean) map.get("published");
                    break;
                case "masteries": masteries = (Map) map.get("masteries");
                    break;
                case "capacity": capacity = ((Number) map.get("capacity")).doubleValue();
                    break;
                case "volume": volume = ((Number) map.get("volume")).doubleValue();
                    break;
                case "soundID": soundID = (Integer) map.get("soundID");
                    break;
                case "graphicID": graphicID = (Integer) map.get("graphicID");
                    break;
                case "name": name = new Translation((Map)map.get("name"));
                    break;
                case "factionID": factionID = (Integer) map.get("factionID");
                    break;
                case "sofDnaAddition": sofDnaAddition = (String) map.get("sofDnaAddition");
                    break;
                case "radius": radius = ((Number) map.get("radius")).doubleValue();
                    break;
                case "portionSize": portionSize = (Integer) map.get("portionSize");
                    break;
                case "sofFactionName": sofFactionName = (String) map.get("sofFactionName");
                    break;
                case "basePrice": basePrice = ((Number) map.get("basePrice")).doubleValue();
                    break;
                case "marketGroupID": marketGroupID = (Integer) map.get("marketGroupID");
                    break;
                default:
                    System.err.println("unknown field: "+str);
                    break;
            }

        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
