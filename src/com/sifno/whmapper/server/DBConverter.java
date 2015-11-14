package com.sifno.whmapper.server;

import com.sifno.stellarmap.dataobject.Stargate;

import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Алёна on 10.11.2015.
 */
public class DBConverter {



    public static SQLiteJDBC whmapperdb = new SQLiteJDBC(new File("D:\\GitHub\\WHMapper\\whmapperdb.db"));
    public static SQLiteJDBC evedb = new SQLiteJDBC(new File("D:\\Coding\\eve resurce\\Parallax_1.0_115480_db\\universeDataDx.db"));

    public static SQLiteJDBC olddb = new SQLiteJDBC(new File(SQLiteJDBC.path));


    private void CopyInteger(ResultSet result, PreparedStatement insertStatement, String fromColumn, int toColumn ) throws SQLException {
        Integer number = result.getInt(fromColumn);
        if (result.wasNull())
            insertStatement.setNull(toColumn, Types.INTEGER);
        else
            insertStatement.setInt(toColumn, number);
    }

    private void CopyString(ResultSet result, PreparedStatement insertStatement, String fromColumn, int toColumn ) throws SQLException {
        if (result.getString(fromColumn)!=null)
            insertStatement.setString(toColumn, result.getString(fromColumn));
        else insertStatement.setNull(toColumn, Types.VARCHAR);
    }

    private void CopyDouble(ResultSet result, PreparedStatement insertStatement, String fromColumn, int toColumn ) throws SQLException {
        Double number = result.getDouble(fromColumn);
        if (result.wasNull())
            insertStatement.setNull(toColumn, Types.DOUBLE);
        else
            insertStatement.setDouble(toColumn, number);
    }

    public void ImportStargates() {
       // Connection eveConnection = evedb.getConnection();
        Connection olddbConnection = olddb.getConnection();
        Connection whmapperConnection = whmapperdb.getConnection();

        try {
            Statement statement = whmapperConnection.createStatement();

            statement.execute("DROP TABLE IF EXISTS stargates");
            statement.execute("CREATE TABLE stargates (\n" +
                                "  id INTEGER PRIMARY KEY NOT NULL,\n" +
                                "  name VARCHAR(255),\n" +
                                "  starsystem_id INTEGER NOT NULL,\n" +
                                "  destination_stargate_id INTEGER NOT NULL,\n" +
                                "  jump_id INTEGER\n" +
                                ");"
            );

            PreparedStatement selectStatement = olddbConnection.prepareStatement("SELECT id, name, solarsystem_id, destination_stargate_id FROM stargates;");
            ResultSet result = selectStatement.executeQuery();

            PreparedStatement insertStatement = whmapperConnection.prepareStatement("INSERT INTO stargates (id, name, starsystem_id, destination_stargate_id, jump_id) VALUES (?,?,?,?,?);");

            Map<Integer, Stargate> stargateMap = new HashMap<>();

            while (result.next()) {
                Stargate data = new Stargate();

                data.setID(result.getInt("id"));
                data.setName(result.getString("name"));
                data.setStarSystemID(result.getInt("solarsystem_id"));
                data.setDestinationStargateID(result.getInt("destination_stargate_id"));
                data.setJumpID(0);

                stargateMap.put(data.getID(),data);
            }

            int jumpID = 1;
            for (Stargate stargate: stargateMap.values()) {
                if (stargate.getJumpID() == 0) {
                    Stargate destinationStargate = stargateMap.get(stargate.getDestinationStargateID());
                    stargate.setJumpID(jumpID);
                    destinationStargate.setJumpID(jumpID);
                    jumpID++;
                }
            }

            for (Stargate stargate: stargateMap.values()) {
                insertStatement.setInt(1,stargate.getID());
                insertStatement.setString(2, stargate.getName());
                insertStatement.setInt(3,stargate.getStarSystemID());
                insertStatement.setInt(4,stargate.getDestinationStargateID());
                insertStatement.setInt(5,stargate.getJumpID());

                insertStatement.addBatch();
            }

            insertStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void ImportStarsystems() {
        Connection eveConnection = evedb.getConnection();
        Connection whmapperConnection = whmapperdb.getConnection();

        try {
            Statement statement = whmapperConnection.createStatement();

            statement.execute("DROP TABLE IF EXISTS starsystems;");
            statement.execute("CREATE TABLE starsystems\n" +
                                "(\n" +
                                "  id INTEGER PRIMARY KEY NOT NULL,\n" +
                                "  name TEXT,\n" +
                                "  constellation_id INTEGER,\n" +
                                "  security REAL,\n" +
                                "  luminosity REAL,\n" +
                                "  sun_type_id INTEGER\n" +
                                ");"
            );

            PreparedStatement selectStatement = eveConnection.prepareStatement("SELECT solarSystemID, solarSystemName, constellationID, security, luminosity, sunTypeID FROM mapSolarSystems;");
            ResultSet result = selectStatement.executeQuery();

            PreparedStatement insertStatement = whmapperConnection.prepareStatement("INSERT INTO starsystems (id, name, constellation_id, security, luminosity, sun_type_id) VALUES (?,?,?,?,?,?);");

            while (result.next()) {
                CopyInteger(result, insertStatement, "solarSystemID",1);
                CopyString(result, insertStatement, "solarSystemName", 2);
                CopyInteger(result, insertStatement, "constellationID", 3);
                CopyDouble(result, insertStatement, "security",4);
                CopyDouble(result, insertStatement, "luminosity", 5);
                CopyInteger(result,insertStatement,"sunTypeID",6);
                insertStatement.addBatch();
            }

            insertStatement.executeBatch();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void ImportConstellations() {
        Connection eveConnection = evedb.getConnection();
        Connection whmapperConnection = whmapperdb.getConnection();

        try {
            Statement statement = whmapperConnection.createStatement();
            statement.execute("DROP TABLE IF EXISTS constellations;");
            statement.execute("CREATE TABLE constellations (\n" +
                                "  id INTEGER PRIMARY KEY NOT NULL,\n" +
                                "  name VARCHAR(255),\n" +
                                "  region_id INTEGER\n  " +
                                ");"
            );

            PreparedStatement selectStatement = eveConnection.prepareStatement("SELECT constellationID, constellationName, regionID FROM mapConstellations;");
            ResultSet result = selectStatement.executeQuery();

            PreparedStatement insertStatement = whmapperConnection.prepareStatement("INSERT INTO constellations (id, name, region_id) VALUES (?,?,?);");

            while (result.next()) {
                CopyInteger(result, insertStatement, "constellationID",1);
                CopyString(result,insertStatement,"constellationName",2);
                CopyInteger(result,insertStatement,"regionID",3);
                insertStatement.addBatch();
            }

            insertStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void ImportRegions() {
        Connection eveConnection = evedb.getConnection();
        Connection whmapperConnection = whmapperdb.getConnection();

        try {
            Statement statement  = whmapperConnection.createStatement();
            statement.execute("DROP TABLE IF EXISTS regions;");
            statement.execute(  "CREATE TABLE regions (\n" +
                                "  id INTEGER PRIMARY KEY NOT NULL,\n" +
                                "  name VARCHAR(255),\n" +
                                "  faction_id INTEGER\n" +
                                ");"
            );

            PreparedStatement selectStatement = eveConnection.prepareStatement("SELECT regionID, regionName, factionID from mapRegions;");
            ResultSet result = selectStatement.executeQuery();

            PreparedStatement insertStatement = whmapperConnection.prepareStatement("INSERT INTO regions (id, name, faction_id) VALUES (?,?,?);");

            while (result.next()) {

                CopyInteger(result,insertStatement,"regionID",1);
                CopyString(result,insertStatement,"regionName",2);
                CopyInteger(result,insertStatement,"factionID",3);
                insertStatement.addBatch();
            }

            insertStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

}
