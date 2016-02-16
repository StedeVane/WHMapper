package com.sifno.stellarmap.graph;


import java.io.Serializable;
import java.util.Collection;

public interface GraphModel<V,E> extends Serializable {
    public void addVertex(V vertex);
    public void addEdge(E e, V v1, V v2);

    public void removeVertex(V vertex);
    public void removeEdge(E edge);

    public boolean containsVertex(V vertex);
    public Pair<V> getEdge(E e);
    public E getEdge(V v1, V v2);

    public Collection<V> getVertices();
    public Collection<E> getEdges();
    public Collection<V> getNeighbors(V vertex);
}
