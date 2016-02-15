package com.sifno.whmapper.server;



import com.sifno.stellarmap.StellarMapInfoCache;
import com.sifno.stellarmap.dataobject.Signature;

import javax.swing.*;
import java.util.List;

public class Main extends JFrame {




    public Main() {

      //  DBConverter dbConverter = new DBConverter();

//        dbConverter.ImportRegions();
//        dbConverter.ImportConstellations();
//        dbConverter.ImportStarsystems();
//        dbConverter.ImportStargates();

        ServerDataLoader sdl = new ServerDataLoader();
//
        StellarMapInfoCache model = new StellarMapInfoCache();
        model.addRegions(sdl.loadRegionsAll());
        model.addConstellations(sdl.loadConstellationsAll());
        model.addStarSystems(sdl.loadStarSystemsAll());
        model.addStargates(sdl.loadStargatesAll());
//
//        Graph<Integer, Integer> g = null;


//        Kspace map = new Kspace(sdl);
//
//        Region r = sdl.loadRegion(10000002);
//        Constellation c = sdl.loadConstellation(20000001);
//        StarSystem s = sdl.downloadSolarSystem(30000001);
//        Stargate sg = sdl.loadStargate(50000001);

      //  WormholeMap wspace = new WormholeMap();
      //  add(wspace);


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
     //           new Main();

                List<Signature> signatures =  Signature.parse(
                        "BVI-856\tCosmic Signature\t\t\t0,00%\t2,39 AU\n" +
                                "GTJ-136\tCosmic Signature\tData Site\tCentral Guristas Sparking Transmitter\t100,00%\t1,90 AU\n" +
                                "HVH-625\tCosmic Signature\t\t\t0,00%\t4,93 AU\n" +
                                "HZW-357\tCosmic Signature\tWormhole\tUnstable Wormhole\t100,00%\t4,99 AU\n" +
                                "ICM-204\tCosmic Signature\t\t\t0,00%\t5,87 AU\n" +
                                "MFV-568\tCosmic Signature\t\t\t0,00%\t6,29 AU\n" +
                                "RAB-085\tCosmic Signature\t\t\t0,00%\t2,50 AU"
                );

                Kspace.getInstance();

                return;
            }
        });
    }
}
