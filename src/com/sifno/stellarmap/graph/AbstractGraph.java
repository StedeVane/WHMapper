package com.sifno.stellarmap.graph;

import java.util.Collection;

public abstract class AbstractGraph<V,E> implements Graph<V,E> {
    GraphModel<V,E> model;

    public AbstractGraph(GraphModel<V, E> model) {
        this.model = model;
    }

    protected AbstractGraph() {}


    @Override
    public void addVertex(V vertex) {
        model.addVertex(vertex);
    }

    @Override
    public void addEdge(E e, V v1, V v2) {
        model.addEdge(e, v1, v2);
    }

    @Override
    public void removeVertex(V vertex) {
        model.removeVertex(vertex);
    }

    @Override
    public void removeEdge(E edge) {
        model.removeEdge(edge);
    }

    @Override
    public boolean containsVertex(V vertex) {
        return model.containsVertex(vertex);
    }

    @Override
    public Pair<V> getEdge(E e) {
        return model.getEdge(e);
    }

    @Override
    public E getEdge(V v1, V v2) {
        return model.getEdge(v1, v2);
    }

    @Override
    public Collection<V> getVertices() {
        return model.getVertices();
    }

    @Override
    public Collection<E> getEdges() {
        return model.getEdges();
    }

    @Override
    public Collection<V> getNeighbors(V vertex) {
        return model.getNeighbors(vertex);
    }
}
