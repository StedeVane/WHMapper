package com.sifno.whmapper.server;



import com.sifno.oldmap.Link;
import com.sifno.oldmap.Signature;
import com.sifno.oldmap.SolarSystem;
import com.sifno.oldmap.StellarMap;
import com.sifno.stellarmap.StellarMapModel;
import com.sifno.stellarmap.dataobject.Constellation;
import com.sifno.stellarmap.dataobject.Region;
import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.stellarmap.dataobject.Stargate;
import edu.uci.ics.jung.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ������ on 29.06.15.
 */
public class Main extends JFrame {




    public Main() {

     //   DBConverter dbConverter = new DBConverter();

    //    dbConverter.ImportRegions();
    //    dbConverter.ImportConstellations();
    //    dbConverter.ImportStarsystems();
    //    dbConverter.ImportStargates();

//        ServerDataLoader sdl = new ServerDataLoader();
//
//        StellarMapModel model = new StellarMapModel();
//        model.addRegions(sdl.downloadRegionsAll());
//        model.addConstellations(sdl.downloadConstellationsAll());
//        model.addStarSystems(sdl.downloadStarSystemsAll());
//        model.addStargates(sdl.downloadStargatesAll());
//
//        Graph<Integer, Integer> g = null;


//        StellarMap map = new StellarMap(sdl);
//
//        Region r = sdl.downloadRegion(10000002);
//        Constellation c = sdl.downloadConstellation(20000001);
//        StarSystem s = sdl.downloadSolarSystem(30000001);
//        Stargate sg = sdl.downloadStargate(50000001);

      //  WormholeMap wormholeMap = new WormholeMap();
      //  add(wormholeMap);


     //   GraphDrawer gd = new GraphDrawer();
     //   add(gd);

        Map<Integer,Integer> m = new HashMap<>();
        m.put(null,1);
        m.put(1,null);
        m.put(new Integer(1), 2);


        Label label = new Label("Hello");
     //   add(label);


       // label.set


        pack();

        setVisible(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();


            /*    List<Integer> integerList = null;
                for (Integer integer : integerList) {
                    System.out.println(integer);
                }
*/
//                Signature.parse(
//                        "BVI-856\tCosmic Signature\t\t\t0,00%\t2,39 AU\n" +
//                                "GTJ-136\tCosmic Signature\tData Site\tCentral Guristas Sparking Transmitter\t100,00%\t1,90 AU\n" +
//                                "HVH-625\tCosmic Signature\t\t\t0,00%\t4,93 AU\n" +
//                                "HZW-357\tCosmic Signature\tWormhole\tUnstable Wormhole\t100,00%\t4,99 AU\n" +
//                                "ICM-204\tCosmic Signature\t\t\t0,00%\t5,87 AU\n" +
//                                "MFV-568\tCosmic Signature\t\t\t0,00%\t6,29 AU\n" +
//                                "RAB-085\tCosmic Signature\t\t\t0,00%\t2,50 AU"
//                );


            }
        });
    }
}
