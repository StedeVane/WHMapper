package com.sifno.stellarmap.graph;

import com.sifno.stellarmap.graph.event.LayoutListener;

import java.awt.*;
import java.util.Map;

public interface Layout<V,E> {
    public Dimension getSize();
    public void setSize(Dimension size);

    public void lock(V vertex, boolean b);
    public boolean isLocked(V vertex);

    public Point.Double getLocation(V vertex);
    public void setLocation(V vertex, Point.Double point);

    public Map<V, Point.Double> getLocations();
    public void setLocations(Map<V, Point.Double> locations);

    public void addLayoutListener(LayoutListener<V,E> layoutListener);
    public void removeLayoutListener(LayoutListener<V,E> layoutListener);
}
