package com.sifno.stellarmap.graphdrawing;


import java.util.*;

/**
 * Created by ���� on 20.11.2015.
 */
public class UndirectedSpareGraph<V,E> implements Graph<V,E> {

    private Set<V> vertices = new HashSet<>();
    private Map<E, Pair<V>> edges = new HashMap<>();
    private Map<V,Map<E,V>> neighbors = new HashMap<>();


    @Override
    public void addVertex(V vertex) {
        if (vertex == null) throw  new NullPointerException("Graph cannot contain null value");
        vertices.add(vertex);
        neighbors.put(vertex, new HashMap<E,V>());
    }

    @Override
    public void addEdge(E e, V v1, V v2) {
        if (e == null || v1 == null || v2 == null) throw  new IllegalArgumentException("parameters must not be null");
        if (!vertices.contains(v1) || !vertices.contains(v2)) throw new IllegalStateException("There are no the vertex in the graph");
        edges.put(e,new Pair<V>(v1,v2));
        neighbors.get(v1).put(e,v2);
        neighbors.get(v2).put(e,v1);
    }

    @Override
    public void removeVertex(V vertex) {
        vertices.remove(vertex);
        for (E e: neighbors.get(vertex).keySet()) {
            V v = neighbors.get(vertex).get(e);

            edges.remove(e);
            neighbors.get(v).remove(e);
        }
        neighbors.remove(vertex);
    }

    @Override
    public void removeEdge(E edge) {
        edges.remove(edge);
    }


    @Override
    public Pair<V> getEdge(E e) {
        if (e == null) throw  new IllegalArgumentException("parameter must not be null");
        return edges.get(e);
    }

    @Override
    public Collection<V> getVertices() {
        return vertices;
    }

    @Override
    public Collection<E> getEdges() {
        return edges.keySet();
    }

    @Override
    public Collection<V> getNeighbors(V vertex) {
        return neighbors.get(vertex).values();
    }
}