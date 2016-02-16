package com.sifno.stellarmap.graph;

import com.sifno.stellarmap.graph.event.GraphListener;

import java.util.List;

public class ObservableGraph<V,E> extends AbstractGraph<V,E> {

    private transient List<GraphListener<V,E>> graphListeners;

    public ObservableGraph(GraphModel<V,E> model) {
        super(model);
    }

    protected ObservableGraph() {
    }

    @Override
    public void addGraphListener(GraphListener<V, E> graphListener) {
        graphListeners.add(graphListener);
    }

    @Override
    public void removeGraphListener(GraphListener<V, E> graphListener) {
        graphListeners.remove(graphListener);
    }
}
