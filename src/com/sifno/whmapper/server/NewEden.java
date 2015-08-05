package com.sifno.whmapper.server;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Pavel on 26.07.2015.
 */
public class NewEden {

    final public static NewEden instance = new NewEden();
    final public static Index K162 = instance.indexes.get("K162");

    //Map<String,Location> keyNameMap;
    //Map<Integer,Location> keyIdMap;

    Map<Integer, Region> regionIdMap = new HashMap<>();
    Map<String, Region> regionNameMap = new HashMap<>();

    Map<Integer, Constellation> constellationIdMap = new HashMap<>();
    Map<String, Constellation> constellationNameMap = new HashMap<>();

    Map<Integer, SolarSystem> solarSystemIdMap = new HashMap<>();
    Map<String, SolarSystem> solarSystemNameMap = new HashMap<>();

    Map<Region,Map<Constellation,List<SolarSystem>>> newEden = new HashMap<>();

    Map<String,Index> indexes = new HashMap<>();

    Map<Integer,Stargate> stargateIdMap = new HashMap<>();
    Set<StargateJump> stargateJumpSet = new HashSet<>();

   // List<SolarSystem> solarSystemList;
   // List<Constellation> constellationList;
   // List<Region> regionList;

    final String SELECT_REGIONS = "SELECT * FROM regions;";
    final String SELECT_CONSTELLATIONS = "SELECT * FROM constellations;";
    final String SELECT_SOLARSYSTEMS =
            "SELECT solarsystems.id, solarsystems.constellation_id, solarsystems.name,\n" +
            "  solarsystems.security, solarsystems.luminosity, solarsystems.sun_type_id, wormhole_classes.name AS class\n" +
            "FROM solarsystems\n" +
            "  LEFT JOIN constellations ON  solarsystems.constellation_id = constellations.id\n" +
            "  LEFT JOIN regions ON constellations.region_id = regions.id\n" +
            "  LEFT OUTER JOIN wormhole_class_destinations\n" +
            "    ON (location_id = solarsystems.id\n" +
            "        OR (location_id = constellations.id\n" +
            "            AND solarsystems.id NOT IN (SELECT id FROM wormhole_class_destinations, solarsystems\n" +
            "  WHERE id = location_id)\n" +
            "        )\n" +
            "        OR (location_id = regions.id\n" +
            "            AND solarsystems.id NOT IN (SELECT id FROM wormhole_class_destinations, solarsystems\n" +
            "  WHERE id = location_id)\n" +
            "            AND constellations.id NOT IN (SELECT id FROM wormhole_class_destinations, constellations\n" +
            "  WHERE id = location_id)\n" +
            "        )\n" +
            "    )\n" +
            "\n" +
            "  LEFT JOIN wormhole_classes ON wormhole_class_id = wormhole_classes.id;";

    final String SELECT_WORMHOLE_INDEXES =
            "    SELECT wormhole_indexes.name, wormhole_classes.name as class, mass, mass_jump, mass_regeneration, life_time\n" +
            "    FROM wormhole_indexes\n" +
            "    LEFT OUTER JOIN wormhole_classes ON wormhole_class_id = wormhole_classes.id;";


    final String SELECT_STARGATES = "SELECT * FROM stargates;";

    private NewEden() {

        System.out.println("START()");

        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REGIONS);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Region region= new Region();

                region.setId(result.getInt("id"));
                region.setName(result.getString("name"));
                region.setFractionID(result.getInt("fraction_id"));


                regionIdMap.put(region.getId(), region);
                regionNameMap.put(region.getName(), region);

            }

            preparedStatement = connection.prepareStatement(SELECT_CONSTELLATIONS);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                Constellation constellation = new Constellation();

                constellation.setId(result.getInt("id"));
                constellation.setName(result.getString("name"));
                constellation.setRegion((Region) regionIdMap.get(result.getInt("region_id")));

                constellationIdMap.put(constellation.getId(), constellation);
                constellationNameMap.put(constellation.getName(), constellation);
            }

            preparedStatement = connection.prepareStatement(SELECT_SOLARSYSTEMS);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                SolarSystem solarSystem = new SolarSystem();

                solarSystem.setId(result.getInt("id"));
                solarSystem.setName(result.getString("name"));
                solarSystem.setConstellation((Constellation) constellationIdMap.get(result.getInt("constellation_id")));
                solarSystem.setSecurity(result.getDouble("security"));
                solarSystem.setLuminosity(result.getDouble("luminosity"));
                solarSystem.setSunType(result.getInt("sun_type_id"));
                solarSystem.setClassType(result.getString("class"));

                solarSystemIdMap.put(solarSystem.getId(), solarSystem);
                solarSystemNameMap.put(solarSystem.getName(), solarSystem);
            }

            preparedStatement = connection.prepareStatement(SELECT_WORMHOLE_INDEXES);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                Index Index = new Index();
                Index.setName(result.getString("name"));
                Index.setDest(result.getString("class"));
                Index.setTotalMass(result.getInt("mass"));
                Index.setMassJump(result.getInt("mass_jump"));
                Index.setMssRegeneration(result.getInt("mass_regeneration"));
                Index.setLifeTime(result.getInt("life_time"));

                indexes.put(Index.getIndex(), Index);
            }

            preparedStatement = connection.prepareStatement(SELECT_STARGATES);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                Stargate stargate = new Stargate();
                stargate.setId(result.getInt("id"));
                stargate.setName(result.getString("name"));
                stargate.setSystem(solarSystemIdMap.get(result.getInt("solarsystem_id")));
                stargateIdMap.put(stargate.getId(), stargate);

                stargate.getSystem().add(stargate);
            }

            result = preparedStatement.executeQuery();
            while (result.next()) {
                Stargate stargate1 = stargateIdMap.get(result.getInt("id"));
                Stargate stargate2 = stargateIdMap.get(result.getInt("destination_stargate_id"));
                if (stargate1.getJump()==null && stargate2.getJump()==null) {
                    StargateJump jump = new StargateJump(stargate1, stargate2);
                    stargate1.setJump(jump);
                    stargate2.setJump(jump);
                    stargateJumpSet.add(jump);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.close();

        for (Region region: regionIdMap.values()) {
            newEden.put(region,new HashMap<>());
        }
        for (Constellation constellation: constellationIdMap.values()) {
            newEden.get(constellation.getRegion()).put(constellation,new ArrayList<>());
        }
        for (SolarSystem system: solarSystemIdMap.values()) {
            newEden.get(system.getRegion()).get(system.getConstellation()).add(system);
        }

    }

    public static Region getRegion(int id) {
        return instance.regionIdMap.get(id);
    }

    public static Region getRegion(String name) {
        return instance.regionNameMap.get(name);
    }

    public static Constellation getConstellation(int id) {
        return instance.constellationIdMap.get(id);
    }

    public static Constellation getConstellation(String name) {
        return instance.constellationNameMap.get(name);
    }

    public static SolarSystem getSolarSystem(int id) {
        return instance.solarSystemIdMap.get(id);
    }

    public static SolarSystem getSolarSystem(String name) {
        return instance.solarSystemNameMap.get(name);
    }

    public static Collection<Region> getRegionsAll() {
        return  instance.regionIdMap.values();
    }

    public static Collection<Constellation> getConstellationsAll() {
        return  instance.constellationIdMap.values();
    }

    public static Collection<SolarSystem> getSolarSystemsAll() {
        return  instance.solarSystemIdMap.values();
    }

    public static Collection<SolarSystem> getSolarSystems(Constellation constellation) {
        return instance.newEden.get(constellation.getRegion()).get(constellation);
    }

    public static Collection<SolarSystem> getSolarSystems(Region region) {
        Set<SolarSystem> result = new HashSet<>();
        for (Constellation constellation: instance.newEden.get(region).keySet()) {
            result.addAll(instance.newEden.get(region).get(constellation));
        }
        return result;
    }


    public static Index getIndex(String index) {
        return instance.indexes.get(index);
    }

}
