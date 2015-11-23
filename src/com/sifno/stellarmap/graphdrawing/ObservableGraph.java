package com.sifno.stellarmap.graphdrawing;



import java.util.LinkedList;
import java.util.List;

/**
 * Created by Алёна on 20.11.2015.
 */
public class ObservableGraph<V,E> extends GraphDecorator<V,E> {

    List<GraphEventListener<V,E>> listenerList = new LinkedList<>();

    public ObservableGraph(Graph<V, E> delegate) {
        super(delegate);
    }

    public void addGraphEventListener(GraphEventListener<V,E> listener) {
        listenerList.add(listener);
    }

    public void removeGraphEventListener(GraphEventListener<V,E> listener) {
        listenerList.remove(listener);
    }

    protected void fireGraphEvent(GraphEvent<V,E> event) {
        for (GraphEventListener<V,E> listener: listenerList) {
            listener.handleGraphEvent(event);
        }
    }

    @Override
    public void addVertex(V vertex) {
        super.addVertex(vertex);
        GraphEvent<V,E> event = new GraphEvent.Vertex<V,E>(delegate, GraphEvent.Type.VERTEX_ADDED,vertex);
        fireGraphEvent(event);
    }

    @Override
    public void addEdge(E e, V v1, V v2) {
        super.addEdge(e, v1, v2);
        GraphEvent<V,E> event =  new GraphEvent.Edge<V,E>(delegate,GraphEvent.Type.EDGE_ADDED, e);
        fireGraphEvent(event);
    }

    @Override
    public void removeVertex(V vertex) {
        super.removeVertex(vertex);
        GraphEvent<V,E> event =  new GraphEvent.Vertex<V,E>(delegate,GraphEvent.Type.VERTEX_REMOVED, vertex);
        fireGraphEvent(event);
    }

    @Override
    public void removeEdge(E edge) {
        super.removeEdge(edge);
        GraphEvent<V,E> event =  new GraphEvent.Edge<V,E>(delegate,GraphEvent.Type.EDGE_REMOVED, edge);
        fireGraphEvent(event);
    }

}
