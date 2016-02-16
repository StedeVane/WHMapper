package com.sifno.stellarmap.graph;

import com.sifno.stellarmap.graph.event.GraphLayoutListener;

import java.awt.*;
import java.util.Map;

public interface Layout<V,E> extends LayoutModel<V,E> {

    public void addLayoutListener(GraphLayoutListener<V,E> layoutListener);
    public void removeLayoutListener(GraphLayoutListener<V,E> layoutListener);
}
