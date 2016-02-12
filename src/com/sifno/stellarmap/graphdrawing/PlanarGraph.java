package com.sifno.stellarmap.graphdrawing;

import com.google.gwt.user.client.Random;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;

public class PlanarGraph<V,E> implements Layout<V,E>, Graph<V,E> {

    Graph<V,E> delegate;

    private Dimension size = new Dimension(800,500);
    private Map<V, Point2D> locations = new HashMap<>();
    private Set<V> locked = new HashSet<>();


    public PlanarGraph(Graph<V,E> graph) {
        this.delegate = graph;
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

        for (Point2D point: getLocations().values()) {
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

    //TODO �������� �� ����� ������ ����� ��������� �����-������
    @Override
    public void setLocations(Map<V, Point2D> locations) {
        this.locations = locations;
    }

    @Override
    public void addVertex(V vertex) {
        delegate.addVertex(vertex);
        locations.put(vertex, new Point2D.Double(Random.nextDouble() * getSize().getWidth(), Random.nextDouble() * getSize().getHeight()));
    }

    @Override
    public void addEdge(E e, V v1, V v2) {
        delegate.addEdge(e,v1,v2);
    }

    @Override
    public void removeVertex(V vertex) {
        delegate.removeVertex(vertex);
        locations.remove(vertex);
        locked.remove(vertex);
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
        return delegate.getEdges();
    }

    @Override
    public Collection<V> getNeighbors(V vertex) {
        return delegate.getNeighbors(vertex);
    }

}
