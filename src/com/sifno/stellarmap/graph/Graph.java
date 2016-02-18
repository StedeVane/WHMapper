package com.sifno.stellarmap.graph;


import com.sifno.stellarmap.graph.event.GraphListener;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface Graph<V,E> extends Serializable {

    public boolean addVertex(V vertex);
    public boolean addEdge(E e, V v1, V v2);

    public boolean removeVertex(V vertex);
    public boolean removeEdge(E edge);

    public boolean containsVertex(V vertex);
    public Pair<V> getEdge(E e);
    public E getEdge(V v1, V v2);

    public Collection<V> getVertices();
    public Collection<E> getEdges();
    public Collection<V> getNeighbors(V vertex);

    public void addGraphListener(GraphListener<V, E> graphListener);
    public void removeGraphListener(GraphListener<V, E> graphListener);
}
