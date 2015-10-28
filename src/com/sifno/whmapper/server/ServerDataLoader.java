package com.sifno.whmapper.server;

import com.sifno.stellarmap.*;
import com.sifno.stellarmap.dataobject.ConstellationData;
import com.sifno.stellarmap.dataobject.RegionData;
import com.sifno.stellarmap.dataobject.SolarSystemData;
import com.sifno.stellarmap.dataobject.StargateData;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Алёна on 19.08.2015.
 */
public class ServerDataLoader implements DataLoader {



    final String SELECT_REGIONS_ALL = "SELECT * FROM regions;";
    final String SELECT_CONSTELLATIONS_ALL = "SELECT * FROM constellations;";
    final String SELECT_SOLARSYSTEMS_ALL =
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
    final String SELECT_STARGATES_ALL = "SELECT * FROM stargates;";

    final String SELECT_REGION = "SELECT * FROM regions WHERE id = ?;";
    final String SELECT_CONSTELLATION = "SELECT * FROM constellations WHERE id = ?;";
    final String SELECT_SOLARSYSTEM = "SELECT * FROM solarsystems WHERE id = ?;";
    final String SELECT_STARGATE = "SELECT * FROM stargates WHERE id = ?;";


    @Override
    public RegionData downloadRegion(int regionID) {

        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        RegionData data = new RegionData();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REGION);
            preparedStatement.setInt(1, regionID);
            ResultSet result = preparedStatement.executeQuery();

            data.setID(result.getInt("id"));
            data.setName(result.getString("name"));
            data.setFractionID(result.getInt("fraction_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public ConstellationData downloadConstellation(int constellationID) {

        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        ConstellationData data = new ConstellationData();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONSTELLATION);
            preparedStatement.setInt(1, constellationID);
            ResultSet result = preparedStatement.executeQuery();

            data.setID(result.getInt("id"));
            data.setName(result.getString("name"));
            data.setRegionID(result.getInt("region_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public SolarSystemData downloadSolarSystem(int solarSystemID) {

        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        SolarSystemData data = new SolarSystemData();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SOLARSYSTEM);
            preparedStatement.setInt(1, solarSystemID);
            ResultSet result = preparedStatement.executeQuery();

            data.setID(result.getInt("id"));
            data.setName(result.getString("name"));
            data.setConstellationID(result.getInt("constellation_id"));
            data.setSecurity(result.getDouble("security"));
            data.setLuminosity(result.getDouble("luminosity"));
            data.setSunTypeID(result.getInt("sun_type_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public StargateData downloadStargate(int stargateID) {

        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        StargateData data = new StargateData();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STARGATE);
            preparedStatement.setInt(1, stargateID);
            ResultSet result = preparedStatement.executeQuery();

            data.setID(result.getInt("id"));
            data.setName(result.getString("name"));
            data.setSolarSystemID(result.getInt("solarsystem_id"));
            data.setDestinationStargateID(result.getInt("destination_stargate_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    //TODO Получить множество врат из базы
    @Override
    public Collection<StargateData> downloadStargates(Collection<Integer> stargatesID) {
        return null;
    }


    @Override
    public Collection<RegionData> downloadRegionsAll() {

        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        Set<RegionData> resultCollection = new HashSet<>();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REGIONS_ALL);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                RegionData data = new RegionData();

                data.setID(result.getInt("id"));
                data.setName(result.getString("name"));
                data.setFractionID(result.getInt("fraction_id"));

                resultCollection.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        database.close();

        return resultCollection;
    }

    @Override
    public Collection<ConstellationData> downloadConstellationsAll() {

        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        Set<ConstellationData> resultCollection = new HashSet<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONSTELLATIONS_ALL);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                ConstellationData data = new ConstellationData();

                data.setID(result.getInt("id"));
                data.setName(result.getString("name"));
                data.setRegionID(result.getInt("region_id"));

                resultCollection.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        database.close();

        return resultCollection;
    }

    @Override
    public Collection<SolarSystemData> downloadSolarSystemsAll() {
        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        Set<SolarSystemData> resultCollection = new HashSet<>();


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SOLARSYSTEMS_ALL);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                SolarSystemData data = new SolarSystemData();

                data.setID(result.getInt("id"));
                data.setName(result.getString("name"));
                data.setConstellationID(result.getInt("constellation_id"));
                data.setSecurity(result.getDouble("security"));
                data.setLuminosity(result.getDouble("luminosity"));
                data.setSunTypeID(result.getInt("sun_type_id"));
                data.setSystemClassID(result.getString("class"));

                resultCollection.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        database.close();

        return resultCollection;
    }

    @Override
    public Collection<StargateData> downloadStargatesAll() {
        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        Set<StargateData> resultCollection = new HashSet<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STARGATES_ALL);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                StargateData data = new StargateData();
                data.setID(result.getInt("id"));
                data.setName(result.getString("name"));
                data.setSolarSystemID(result.getInt("solarsystem_id"));
                data.setDestinationStargateID(result.getInt("destination_stargate_id"));

                resultCollection.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        database.close();

        return resultCollection;
    }
}
