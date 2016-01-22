package com.sifno.whmapper.server;



import com.sifno.stellarmap.StellarMapCache;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ������ on 29.06.15.
 */
public class Main extends JFrame {




    public Main() {

      //  DBConverter dbConverter = new DBConverter();

//        dbConverter.ImportRegions();
//        dbConverter.ImportConstellations();
//        dbConverter.ImportStarsystems();
//        dbConverter.ImportStargates();

        ServerDataLoader sdl = new ServerDataLoader();
//
        StellarMapCache model = new StellarMapCache();
        model.addRegions(sdl.loadRegionsAll());
        model.addConstellations(sdl.loadConstellationsAll());
        model.addStarSystems(sdl.loadStarSystemsAll());
        model.addStargates(sdl.loadStargatesAll());
//
//        Graph<Integer, Integer> g = null;


//        StellarMap map = new StellarMap(sdl);
//
//        Region r = sdl.loadRegion(10000002);
//        Constellation c = sdl.loadConstellation(20000001);
//        StarSystem s = sdl.downloadSolarSystem(30000001);
//        Stargate sg = sdl.loadStargate(50000001);

      //  WormholeMap wormholeMap = new WormholeMap();
      //  add(wormholeMap);


//        GraphDrawer gd = new GraphDrawer();
//        add(gd);
//
//
//        Label label = new Label("Hello");
//     //   add(label);


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
