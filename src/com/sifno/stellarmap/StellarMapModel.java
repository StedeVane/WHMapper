package com.sifno.stellarmap;

import com.sifno.stellarmap.graphdrawing.GraphDecorator;
import com.sifno.stellarmap.graphdrawing.UndirectedSpareGraph;

public class StellarMapModel extends GraphDecorator<Integer,Integer> {

    public StellarMapModel() {
        super(new UndirectedSpareGraph<>());
    }


}
