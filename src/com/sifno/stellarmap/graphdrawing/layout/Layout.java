package com.sifno.stellarmap.graphdrawing.layout;

import com.sifno.stellarmap.graphdrawing.Graph;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Алёна on 23.11.2015.
 */
public class Layout<V,E> {
    private Graph<V,E> graph;
    private Dimension size;

    private Map<V, Point2D> locations = new HashMap<>();
    private Set<V> locked = new HashSet<>();

    public void setGraph(Graph<V, E> graph) {
        this.graph = graph;
    }

    public Graph<V, E> getGraph() {
        return graph;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public Dimension getSize() {
        return size;
    }

    public void lock(V vertex, boolean b) {
        if (b) locked.add(vertex);
        else locked.remove(vertex);
    }

    public boolean isLocked(V vertex) {
        return locked.contains(vertex);
    }

    public void setLocation(V vertex, Point2D point) {
        locations.put(vertex,point);
    }

    public Point2D getLocation(V vertex) {
        return locations.get(vertex);
    }
}
