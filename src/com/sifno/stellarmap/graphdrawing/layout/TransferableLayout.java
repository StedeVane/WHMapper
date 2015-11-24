package com.sifno.stellarmap.graphdrawing.layout;

import com.sifno.stellarmap.graphdrawing.Graph;
import com.sifno.stellarmap.graphdrawing.ObservableGraph;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Алёна on 24.11.2015.
 */
public class TransferableLayout<V,E> implements Layout<V,E> {

    private Graph<V,E> graph;
    private Dimension size;
    private Map<V, Point2D> locations = new HashMap<>();
    private Set<V> locked = new HashSet<>();

    public TransferableLayout(Dimension size) {
        if (size.getWidth() <= 0 || size.getHeight() <= 0) throw new IllegalArgumentException("size must be positive");
        this.size = size;
    }

    @Override
    public Graph<V, E> getGraph() {
        return graph;
    }

    @Override
    public void setGraph(Graph<V, E> graph) {
        this.graph = graph;
    }

    @Override
    public Dimension getSize() {
        return size;
    }

    @Override
    public void setSize(Dimension size) {
        if (size.getWidth() <= 0 || size.getHeight() <= 0) throw new IllegalArgumentException("size must be positive");
        this.size = size;
    }

    @Override
    public void lock(V vertex, boolean b) {
        if (b) locked.add(vertex);
        else locked.remove(vertex);
    }

    @Override
    public boolean isLocked(V vertex) {
        return locked.contains(vertex);
    }

    @Override
    public Point2D getLocation(V vertex) {
        return locations.get(vertex);
    }

    @Override
    public void setLocation(V vertex, Point2D point) {
        locations.put(vertex,point);
    }

    @Override
    public Map<V, Point2D> getLocations() {
        return locations;
    }

    @Override
    public void setLocations(Map<V, Point2D> locations) {
        this.locations = locations;
    }
}
