package com.sifno.stellarmap.graph;


import com.sifno.stellarmap.graph.event.GraphListener;


public interface Graph<V,E> extends GraphModel<V,E> {

    public void addGraphListener(GraphListener<V,E> graphListener);
    public void removeGraphListener(GraphListener<V,E> graphListener);
}
