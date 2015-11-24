package com.sifno.stellarmap.graphdrawing.layout;

import com.sifno.stellarmap.graphdrawing.Graph;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Map;

/**
 * Created by Алёна on 24.11.2015.
 */
public interface Layout<V,E> {

    public Graph<V, E> getGraph();
    public void setGraph(Graph<V, E> graph);

    public Dimension getSize();
    public void setSize(Dimension size);

    public void lock(V vertex, boolean b);
    public boolean isLocked(V vertex);

    public Point2D getLocation(V vertex);
    public void setLocation(V vertex, Point2D point);

    public Map<V, Point2D> getLocations();
    public void setLocations(Map<V, Point2D> locations);

}
