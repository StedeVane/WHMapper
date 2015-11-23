package com.sifno.stellarmap.graphdrawing;

import java.util.Collection;

/**
 * Created by Алёна on 20.11.2015.
 */
public class GraphDecorator<V,E> implements Graph<V,E> {
    Graph delegate;

    public GraphDecorator(Graph<V,E> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void addVertex(V vertex) {
        delegate.addVertex(vertex);
    }

    @Override
    public void addEdge(E e, V v1, V v2) {
        delegate.addEdge(e,v1,v2);
    }

    @Override
    public void removeVertex(V vertex) {
        delegate.removeVertex(vertex);
    }

    @Override
    public void removeEdge(E edge) {
        delegate.removeEdge(edge);
    }

    @Override
    public Pair<V> getEdge(E e) {
        return delegate.getEdge(e);
    }

    @Override
    public Collection<V> getVertices() {
        return delegate.getVertices();
    }

    @Override
    public Collection<E> getEdges() {
        return getEdges();
    }

    @Override
    public Collection<V> getNeighbors(V vertex) {
        return delegate.getNeighbors(vertex);
    }
}
