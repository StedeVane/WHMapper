package com.sifno.stellarmap.graphdrawing.layout;

import com.sifno.stellarmap.graphdrawing.Graph;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Map;

/**
 * Created by Алёна on 24.11.2015.
 */
public class LayoutDecorator<V,E> implements Layout<V,E> {

    Layout<V,E> delegate;

    public LayoutDecorator(Layout<V,E> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Graph<V, E> getGraph() {
        return delegate.getGraph();
    }

    @Override
    public void setGraph(Graph<V, E> graph) {
        delegate.setGraph(graph);
    }

    @Override
    public Dimension getSize() {
        return delegate.getSize();
    }

    @Override
    public void setSize(Dimension size) {
        delegate.setSize(size);
    }

    @Override
    public void lock(V vertex, boolean b) {
        delegate.lock(vertex,b);
    }

    @Override
    public boolean isLocked(V vertex) {
        return delegate.isLocked(vertex);
    }

    @Override
    public Point2D getLocation(V vertex) {
        return delegate.getLocation(vertex);
    }

    @Override
    public void setLocation(V vertex, Point2D point) {
        delegate.setLocation(vertex,point);
    }

    @Override
    public Map<V, Point2D> getLocations() {
        return getLocations();
    }

    @Override
    public void setLocations(Map<V, Point2D> locations) {
        delegate.setLocations(locations);
    }
}
