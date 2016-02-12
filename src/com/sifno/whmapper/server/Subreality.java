package com.sifno.whmapper.server;

import com.sifno.stellarmap.dataobject.Signature;
import com.sifno.stellarmap.dataobject.StarSystem;
import com.sifno.stellarmap.graphdrawing.Pair;
import com.sifno.stellarmap.graphdrawing.PlanarGraph;
import com.sifno.stellarmap.graphdrawing.UndirectedSpareGraph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Subreality {

    Map<String,Set<StarSystem>> fieldsMap;
    Map<String,PlanarGraph<Integer,Integer>> graphs;

    
    StellarMap stellarMap;

    Map<Integer,Signature> signatureMap = new HashMap<>();

    private Map<Integer, Pair<Integer>> edges = new HashMap<>();

    public Subreality(StellarMap stellarMap) {
        this.stellarMap = stellarMap;
        this.fieldsMap = new HashMap<>();
    }


    public PlanarGraph<Integer,Integer> getPlanarGraph(String field) {
        if (!fieldsMap.containsKey(field)) return null;

        //TODO варвар, каждый раз создаю новый граф.
        PlanarGraph<Integer,Integer> result = new PlanarGraph<>(new UndirectedSpareGraph<>());

        for (StarSystem system: fieldsMap.get(field)) {

        }
        return null;
    }


}
