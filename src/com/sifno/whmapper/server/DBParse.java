package com.sifno.whmapper.server;


import java.io.*;

import java.sql.*;


import java.util.*;

/**
 * Created by Pavel on 19.07.2015.
 */
public class DBParse {

    private static final String URL = "jdbc:mysql://localhost:3306/whmdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Cbayjhektp88";


    List<Index> Indexes;
   // private Map<Integer,Map> wormholes;

    public DBParse() {

      /*  try {
            readTypeIdYAML();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
*/
    //    MySQLDataBase dataBase = null;
        SQLiteJDBC dataBase = null;

        PreparedStatement preparedStatement = null;
        try {

            dataBase = new SQLiteJDBC(new File(SQLiteJDBC.path));

           // dataBase = new MySQLDataBase();
            System.out.println(dataBase.getConnection().isClosed());

            //importWHIndex(dataBase, Indexes);



            //Statement statement = dataBase.getConnection().createStatement();
            dataBase.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void readStargate() {

        List<String> fileList = new ArrayList<>();
        SQLiteJDBC database = new SQLiteJDBC(new File(SQLiteJDBC.path));
        Connection connection = database.getConnection();

        try {
            //InputStream input = new FileInputStream(new File("stargates.txt"));
            BufferedReader reader = new BufferedReader(new FileReader("stargates.txt"));
            final String INSERT = "INSERT INTO temp_stargates(id, name, solarsystem_id) VALUES (?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);

            String currentLine = reader.readLine();
            while ((currentLine = reader.readLine()) != null) {
                String[] line = currentLine.split("\t");

                preparedStatement.setInt(1, Integer.valueOf(line[0]));
                preparedStatement.setString(2, line[1]);
                preparedStatement.setInt(3, Integer.valueOf(line[2]));

                preparedStatement.addBatch();

                fileList.add(currentLine);
            }

            preparedStatement.executeBatch();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return;
    }


/*
    void importWHIndex(SQLiteJDBC dataBase, List<Index> list) {
        String INSERT_WH_INDEX = "INSERT INTO wormhole_indexes_2(id,name) VALUES(?,?)";

        try {
            PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(INSERT_WH_INDEX);

            for (Index whi : list) {

                preparedStatement.setInt(1, whi.getId());
                preparedStatement.setString(2, whi.getName());
                preparedStatement.addBatch();

            }

            int b=0;
            preparedStatement.executeBatch();
            if (b!=0) {
                System.out.println(" accept");
            }
            else System.out.println(" error");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
/*

    void readTypeIdYAML()throws FileNotFoundException  {
        InputStream input = new FileInputStream(new File("F:/GitHub/WHMapper/src/main/resources/YAML/typeIDs.yaml"));
        Yaml yaml = new Yaml();
        //String document = "\n- Hesperiidae\n- Papilionidae\n- Apatelodidae\n- Epiplemidae";
        Map<Integer,Map> map = (Map) yaml.load(input);
        // List<Objects> list = (List)map;
        //     System.out.println(list);
        //     System.out.println(yaml.dump(list));




        Map<Integer,TypeID> mapID = new HashMap<>();
        for (int i: map.keySet()) {
            mapID.put(i, new TypeID(i,map.get(i)));
        }

        Indexes = new ArrayList<>();
        for (TypeID typeID : mapID.values()) {
            Integer groupID = typeID.getGroupID();
            if (groupID!=null && groupID == 988) {
                String name = typeID.getName().getEn();
              //  if (name.matches("Wormhole [A-Z]\\d{3}")) name = name.substring(9);
                Indexes.add(new Index(typeID.getId(), name));
            }

        }

        int maxNameLength = 0;
        int maxDescriptionLength =0;
        for (TypeID typeID : mapID.values()) {
            if (typeID.getName() != null)
                maxNameLength = Math.max(getMaxLength(typeID.getName()), maxNameLength);
            if (typeID.getDescription()!=null)
                maxDescriptionLength = Math.max(getMaxLength(typeID.getDescription()),maxDescriptionLength);
        }
        System.out.println("maxNameLength: "+maxNameLength);
        System.out.println("maxDescriptionLength: "+maxDescriptionLength);
        return;



// fields
//        Set<String> fields = new HashSet<>();
//        for (Map m :map.values()) {
//            fields.addAll(m.keySet());
//        }
//
//        System.out.println(fields);



       // boolean result;
       // result = find(map);
    }

    private int getMaxLength(Translation tr) {
        int result = 0;
        if (tr.getDe()!= null) result = Math.max(tr.getDe().length(),result);
        if (tr.getEn()!= null) result = Math.max(tr.getEn().length(),result);
        if (tr.getFr()!= null) result = Math.max(tr.getFr().length(),result);
        if (tr.getJa()!= null) result = Math.max(tr.getJa().length(),result);
        if (tr.getRu()!= null) result = Math.max(tr.getRu().length(),result);
        if (tr.getZh()!= null) result = Math.max(tr.getZh().length(),result);

        if (result==3421) System.out.println(tr.getEn().length() + "\n" + tr.getEn());

        return result;
    }*/
}
