package com.sifno.stellarmap.graphdrawing;

import com.sifno.stellarmap.graphdrawing.Graph;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Map;

/**
 * Created by Алёна on 24.11.2015.
 */
public interface Layout<V,E> {

    public Dimension getSize();
    public void setSize(Dimension size);

    public void lock(V vertex, boolean b);
    public boolean isLocked(V vertex);

    public Point.Double getLocation(V vertex);
    public void setLocation(V vertex, Point.Double point);

    public Map<V, Point.Double> getLocations();
    public void setLocations(Map<V, Point.Double> locations);

}
