package com.sifno.whmapper.server;



import com.sifno.whmapper.client.Signature;

import javax.swing.*;

/**
 * Created by ������ on 29.06.15.
 */
public class Main extends JFrame {



    public Main() {



        WormholeMap wormholeMap = new WormholeMap();
        add(wormholeMap);


        pack();

        setVisible(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();


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
