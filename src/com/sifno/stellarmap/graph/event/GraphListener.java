package com.sifno.stellarmap.graph.event;


import com.google.gwt.event.dom.client.ClickEvent;

public interface GraphListener<V,E>  {
    void handleGraphEvent(GraphEvent<V, E> event);
}
