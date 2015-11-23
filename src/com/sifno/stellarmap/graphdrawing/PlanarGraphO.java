package com.sifno.stellarmap.graphdrawing;

import java.awt.geom.Point2D;
import java.util.*;

/**
 * Created by Алёна on 13.11.2015.
 */
public class PlanarGraphO<V,E> extends ObservableGraph<V,E>{
    //TODO спросить у Ди про исключения
    private int Width, Height;

    private Map<V, Point2D> locations = new HashMap<>();

    public PlanarGraphO(Graph<V, E> graph) {
        super(graph);
        Width = 0;
        Height = 0;
    }

    @Override
    public void addVertex(V vertex) {
        super.addVertex(vertex);
        locations.put(vertex, new Point2D.Double());
    }

    public Point2D getLocation(V vertex) {
        return locations.get(vertex);
    }

    public void setLocation(V vertex, Point2D point) {
        locations.put(vertex, point);

    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }


}
