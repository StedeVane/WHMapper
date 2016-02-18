package com.sifno.stellarmap.graph;

import com.sifno.stellarmap.graph.event.GraphEvent;
import com.sifno.stellarmap.graph.event.GraphListener;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGraph<V,E> implements Graph<V,E>{
    protected transient List<GraphListener<V,E>> graphListeners;

    public void addGraphListener(GraphListener<V, E> graphListener) {
        if (graphListeners==null)
            graphListeners = new ArrayList<>();
        graphListeners.add(graphListener);
    }


    public void removeGraphListener(GraphListener<V, E> graphListener) {
        if (graphListeners!=null)
            graphListeners.remove(graphListener);
    }

    protected void fireGraphEvent(GraphEvent<V, E> event) {
        if (graphListeners!=null) {
            for (GraphListener<V,E> graphListener : graphListeners) {
                graphListener.handleGraphEvent(event);
            }
        }
    }

    @Override
    public boolean addVertex(V vertex) {
        fireGraphEvent(new GraphEvent.Vertex<>(this, GraphEvent.Type.VERTEX_ADDED, vertex));
        return true;
    }

    @Override
    public boolean addEdge(E e, V v1, V v2) {
        fireGraphEvent(new GraphEvent.Edge<>(this, GraphEvent.Type.EDGE_ADDED,e));
        return true;
    }

    @Override
    public boolean removeVertex(V vertex) {
        fireGraphEvent(new GraphEvent.Vertex<>(this, GraphEvent.Type.VERTEX_REMOVED,vertex));
        return true;
    }

    @Override
    public boolean removeEdge(E edge) {
        fireGraphEvent(new GraphEvent.Edge<>(this, GraphEvent.Type.EDGE_REMOVED,edge));
        return true;
    }
}
