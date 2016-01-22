package com.sifno.whmapper.server;

import com.sifno.stellarmap.dataobject.Constellation;
import com.sifno.stellarmap.dataobject.Region;
import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.stellarmap.dataobject.Stargate;

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
    final String SELECT_STARSYSTEMS_ALL =
            "SELECT starsystems.id, starsystems.constellation_id, starsystems.name,\n" +
                    "  starsystems.security, starsystems.luminosity, starsystems.sun_type_id, wormhole_classes.name AS class\n" +
                    "FROM starsystems\n" +
                    "  LEFT JOIN constellations ON  starsystems.constellation_id = constellations.id\n" +
                    "  LEFT JOIN regions ON constellations.region_id = regions.id\n" +
                    "  LEFT OUTER JOIN wormhole_class_destinations\n" +
                    "    ON (location_id = starsystems.id\n" +
                    "        OR (location_id = constellations.id\n" +
                    "            AND starsystems.id NOT IN (SELECT id FROM wormhole_class_destinations, starsystems\n" +
                    "  WHERE id = location_id)\n" +
                    "        )\n" +
                    "        OR (location_id = regions.id\n" +
                    "            AND starsystems.id NOT IN (SELECT id FROM wormhole_class_destinations, starsystems\n" +
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
    final String SELECT_STARSYSTEM = "SELECT * FROM starsystems WHERE id = ?;";
    final String SELECT_STARGATE = "SELECT * FROM stargates WHERE id = ?;";


    @Override
    public Region loadRegion(int regionID) {

        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        Region data = new Region();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REGION);
            preparedStatement.setInt(1, regionID);
            ResultSet result = preparedStatement.executeQuery();

            data.setID(result.getInt("id"));
            data.setName(result.getString("name"));
            data.setFactionID(result.getInt("fraction_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public Constellation loadConstellation(int constellationID) {

        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        Constellation data = new Constellation();

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
    public StarSystem loadStarSystem(int starSystemID) {

        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        StarSystem data = new StarSystem();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STARSYSTEM);
            preparedStatement.setInt(1, starSystemID);
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
    public Stargate loadStargate(int stargateID) {

        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        Stargate data = new Stargate();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STARGATE);
            preparedStatement.setInt(1, stargateID);
            ResultSet result = preparedStatement.executeQuery();

            data.setID(result.getInt("id"));
            data.setName(result.getString("name"));
            data.setStarSystemID(result.getInt("starsystem_id"));
            data.setDestinationStargateID(result.getInt("destination_stargate_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    //TODO Получить множество врат из базы
    @Override
    public Collection<Stargate> loadStargates(Collection<Integer> stargatesID) {
        return null;
    }


    @Override
    public Collection<Region> loadRegionsAll() {

        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        Set<Region> resultCollection = new HashSet<>();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REGIONS_ALL);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Region data = new Region();

                data.setID(result.getInt("id"));
                data.setName(result.getString("name"));
                data.setFactionID(result.getInt("faction_id"));

                resultCollection.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        database.close();

        return resultCollection;
    }

    @Override
    public Collection<Constellation> loadConstellationsAll() {

        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        Set<Constellation> resultCollection = new HashSet<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONSTELLATIONS_ALL);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Constellation data = new Constellation();

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
    public Collection<StarSystem> loadStarSystemsAll() {
        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        Set<StarSystem> resultCollection = new HashSet<>();


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STARSYSTEMS_ALL);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                StarSystem data = new StarSystem();

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
    public Collection<Stargate> loadStargatesAll() {
        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        Set<Stargate> resultCollection = new HashSet<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STARGATES_ALL);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Stargate data = new Stargate();
                data.setID(result.getInt("id"));
                data.setName(result.getString("name"));
                data.setStarSystemID(result.getInt("starsystem_id"));
                data.setDestinationStargateID(result.getInt("destination_stargate_id"));
                data.setJumpID(result.getInt("jump_id"));

                resultCollection.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        database.close();

        return resultCollection;
    }
}
