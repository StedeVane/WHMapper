package com.sifno.stellarmap.graphdrawing;

import java.util.Collection;

/**
 * Created by Алёна on 13.11.2015.
 */
public interface Graph<V,E> {
    public void addVertex(V vertex);
    public void addEdge(E e, V v1, V v2);

    public void removeVertex(V vertex);
    public void removeEdge(E edge);

    public Pair<V> getEdge(E e);

    public Collection<V> getVertices();
    public Collection<E> getEdges();
    public Collection<V> getNeighbors(V vertex);
}
