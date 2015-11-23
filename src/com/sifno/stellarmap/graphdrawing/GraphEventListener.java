package com.sifno.stellarmap.graphdrawing;

import java.util.EventListener;

/**
 * Created by Алёна on 20.11.2015.
 */
public interface GraphEventListener<V,E> extends EventListener {
    void handleGraphEvent(GraphEvent<V,E> event);
}
