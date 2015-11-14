package com.sifno.graphdrawing;

import java.awt.geom.Point2D;
import java.util.*;

/**
 * Created by Алёна on 13.11.2015.
 */
public class PlanarGraph<V,E> implements Graph<V,E>{

    private Set<V> vertices = new HashSet<>();
    private Map<E, Pair<V>> edges = new HashMap<>();

    private Map<V,Map<E,V>> neighbors = new HashMap<>();

    private Map<V, Point2D> locations = new HashMap<>();

    @Override
    public void addVertex(V vertex) {
        if (vertex == null) throw  new NullPointerException("Graph cannot contain null value");

        vertices.add(vertex);
        locations.put(vertex, new Point2D.Double());
        neighbors.put(vertex, new HashMap<>());
    }

    @Override
    public void addEdge(E e, V v1, V v2) {
        if (e == null || v1 == null || v2 == null) throw  new IllegalArgumentException("parameters must not be null");
        if (!vertices.contains(v1) || !vertices.contains(v2)) throw new IllegalStateException("There are no the vertex in the graph");
        edges.put(e,new Pair<V>(v1,v2));
        neighbors.get(v1).put(e,v2);
        neighbors.get(v2).put(e,v1);
    }

    public Pair<V> getEdge(E e) {
        if (e == null) throw  new IllegalArgumentException("parameter must not be null");
        return edges.get(e);
    }

    public Point2D getLocation(V vertex) {
        return locations.get(vertex);
    }

    public void setLocation(V vertex, Point2D point) {
        locations.put(vertex, point);
    }

    public Collection<V> getVertices() {
        return vertices;
    }

    public Collection<E> getEdges() {
        return edges.keySet();
    }

    public Collection<V> getNeighbors(V vertex) {
        return neighbors.get(vertex).values();
    }



}
