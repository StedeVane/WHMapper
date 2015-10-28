package com.sifno.whmapper.server;



import com.sifno.stellarmap.Constellation;
import com.sifno.stellarmap.Region;
import com.sifno.stellarmap.Signature;
import com.sifno.stellarmap.StellarMap;
import com.sifno.stellarmap.dataobject.ConstellationData;
import com.sifno.stellarmap.dataobject.RegionData;
import com.sifno.stellarmap.dataobject.SolarSystemData;
import com.sifno.stellarmap.dataobject.StargateData;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by ������ on 29.06.15.
 */
public class Main extends JFrame {




    public Main() {

        ServerDataLoader sdl = new ServerDataLoader();
        StellarMap map = new StellarMap(sdl);

        RegionData r = sdl.downloadRegion(10000002);
        ConstellationData c = sdl.downloadConstellation(20000001);
        SolarSystemData s = sdl.downloadSolarSystem(30000001);
        StargateData sg = sdl.downloadStargate(50000001);

      //  WormholeMap wormholeMap = new WormholeMap();
      //  add(wormholeMap);

        Label label = new Label();
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
                Signature.parse(
                        "BVI-856\tCosmic Signature\t\t\t0,00%\t2,39 AU\n" +
                                "GTJ-136\tCosmic Signature\tData Site\tCentral Guristas Sparking Transmitter\t100,00%\t1,90 AU\n" +
                                "HVH-625\tCosmic Signature\t\t\t0,00%\t4,93 AU\n" +
                                "HZW-357\tCosmic Signature\tWormhole\tUnstable Wormhole\t100,00%\t4,99 AU\n" +
                                "ICM-204\tCosmic Signature\t\t\t0,00%\t5,87 AU\n" +
                                "MFV-568\tCosmic Signature\t\t\t0,00%\t6,29 AU\n" +
                                "RAB-085\tCosmic Signature\t\t\t0,00%\t2,50 AU"
                );


            }
        });
    }
}
