package com.sifno.stellarmap.graph;

import com.sifno.stellarmap.graph.event.GraphListener;

import java.util.Collection;

public class GraphAdapter<V,E> implements Graph<V,E> {


    protected Graph<V,E> delegate;

    public GraphAdapter(Graph<V, E> delegate) {
        this.delegate = delegate;
    }

    protected GraphAdapter() {
    }

    @Override
    public boolean addVertex(V vertex) {
       return delegate.addVertex(vertex);
    }

    @Override
    public boolean addEdge(E e, V v1, V v2) {
        return delegate.addEdge(e, v1, v2);
    }

    @Override
    public boolean removeVertex(V vertex) {
        return delegate.removeVertex(vertex);
    }

    @Override
    public boolean removeEdge(E edge) {
        return delegate.removeEdge(edge);
    }

    @Override
    public boolean containsVertex(V vertex) {
        return delegate.containsVertex(vertex);
    }

    @Override
    public Pair<V> getEdge(E e) {
        return delegate.getEdge(e);
    }

    @Override
    public E getEdge(V v1, V v2) {
        return delegate.getEdge(v1, v2);
    }

    @Override
    public Collection<V> getVertices() {
        return delegate.getVertices();
    }

    @Override
    public Collection<E> getEdges() {
        return delegate.getEdges();
    }

    @Override
    public Collection<V> getNeighbors(V vertex) {
        return delegate.getNeighbors(vertex);
    }

    @Override
    public void addGraphListener(GraphListener<V, E> graphListener) {
        delegate.addGraphListener(graphListener);
    }

    @Override
    public void removeGraphListener(GraphListener<V, E> graphListener) {
        delegate.removeGraphListener(graphListener);
    }

}
