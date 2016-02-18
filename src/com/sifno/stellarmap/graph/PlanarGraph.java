package com.sifno.stellarmap.graph;

import com.sifno.stellarmap.graph.event.LayoutEvent;
import com.sifno.stellarmap.graph.event.LayoutListener;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

import java.util.*;
import java.util.List;

public class PlanarGraph<V,E> extends GraphAdapter<V,E> implements Layout<V,E> {

    protected transient List<LayoutListener<V,E>> layoutListeners;

    private Dimension size = new Dimension(800,500);
    private Map<V, Point.Double> locations = new HashMap<>();
    private Set<V> locked = new HashSet<>();

    private InitializerLocationVertex initializer;

    public PlanarGraph(Graph<V, E> graph) {
        super(graph);
    }

    protected PlanarGraph() {
    }

    @Override
    public Dimension getSize() {
        return size;
    }

    @Override
    public void setSize(Dimension size) {
        if (size.getWidth() <= 0 || size.getHeight() <= 0) throw new IllegalArgumentException("size must be positive");

        double widthRatio = size.getWidth() / getSize().getWidth();
        double heightRatio = size.getHeight() / getSize().getHeight();

        this.size = size;

        for (Point.Double point: getLocations().values()) {
            point.setLocation(point.getX()*widthRatio, point.getY()*heightRatio);
        }
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
    public Point.Double getLocation(V vertex) {
        return locations.get(vertex);
    }

    @Override
    public void setLocation(V vertex, Point.Double point) {
        if (containsVertex(vertex)) throw new IllegalStateException("There are no the vertex in the graph");
        locations.put(vertex,point);

    }

    @Override
    public Map<V, Point2D.Double> getLocations() {
        return locations;
    }

    //TODO возможно от этого метода нужно избавится когда-нибудь
    @Override
    public void setLocations(Map<V, Point.Double> locations) {
        this.locations = locations;
    }

    @Override
    public void addLayoutListener(LayoutListener<V, E> layoutListener) {
        if (layoutListeners==null)
            layoutListeners = new ArrayList<>();
        layoutListeners.add(layoutListener);
    }

    @Override
    public void removeLayoutListener(LayoutListener<V, E> layoutListener) {
        if (layoutListeners!=null)
            layoutListeners.remove(layoutListener);
    }

    protected void fireLayoutEvent(LayoutEvent<V, E> event) {
        if (layoutListeners!=null) {
            for (LayoutListener<V,E> layoutListener : layoutListeners) {
                layoutListener.handleLayoutEvent(event);
            }
        }
    }

    @Override
    public boolean addVertex(V vertex) {
        if (super.addVertex(vertex)) {
            java.util.Random random = new java.util.Random();
            locations.put(vertex, new Point.Double(random.nextDouble() * getSize().getWidth(), random.nextDouble() * getSize().getHeight()));
            return true;
        }
        return false;
    }

    @Override
    public boolean removeVertex(V vertex) {
        if (super.removeVertex(vertex)){
            locations.remove(vertex);
            locked.remove(vertex);
            return true;
        }
        return false;
    }
}
