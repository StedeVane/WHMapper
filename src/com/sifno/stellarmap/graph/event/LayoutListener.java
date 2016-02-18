package com.sifno.stellarmap.graph.event;


public interface LayoutListener<V,E> {
    void handleLayoutEvent(LayoutEvent<V, E> event);
}
