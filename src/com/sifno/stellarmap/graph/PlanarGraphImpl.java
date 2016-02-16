package com.sifno.stellarmap.graph;

import com.sifno.stellarmap.graph.event.GraphLayoutListener;
import com.sifno.stellarmap.graph.event.GraphListener;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

import java.util.*;

public class PlanarGraphImpl<V,E> extends AbstractGraph<V,E> implements PlanarGraph<V,E> {


    private Dimension size = new Dimension(800,500);
    private Map<V, Point.Double> locations = new HashMap<>();
    private Set<V> locked = new HashSet<>();


    public PlanarGraphImpl(Graph<V, E> graph) {
        super(graph);
    }

    protected PlanarGraphImpl() {
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
    public void addLayoutListener(GraphLayoutListener<V, E> layoutListener) {

    }

    @Override
    public void removeLayoutListener(GraphLayoutListener<V, E> layoutListener) {

    }

    @Override
    public void addVertex(V vertex) {
        super.addVertex(vertex);
        java.util.Random random = new java.util.Random();
        locations.put(vertex, new Point.Double(random.nextDouble() * getSize().getWidth(), random.nextDouble() * getSize().getHeight()));

    }

    @Override
    public void removeVertex(V vertex) {
        super.removeVertex(vertex);
        locations.remove(vertex);
        locked.remove(vertex);
    }

    @Override
    public void addGraphListener(GraphListener<V, E> graphListener) {

    }

    @Override
    public void removeGraphListener(GraphListener<V, E> graphListener) {

    }
}
