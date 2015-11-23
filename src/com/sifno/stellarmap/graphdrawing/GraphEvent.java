package com.sifno.stellarmap.graphdrawing;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Алёна on 20.11.2015.
 */
public abstract class GraphEvent<V,E> {


    protected Graph<V,E> source;
    protected Type type;

    public GraphEvent(Graph<V,E> source, Type type ) {
        this.source = source;
        this.type = type;
    }


    public static enum Type {
        VERTEX_ADDED,
        VERTEX_REMOVED,
        EDGE_ADDED,
        EDGE_REMOVED,
        CHANGE_VERTICES_LOCATION
    }

    public static class VerticesLocation<V,E> extends  GraphEvent<V,E> {

        private Map<V, Point2D> locations;

        public VerticesLocation(Graph<V, E> source, Type type, Map<V,Point2D> locations) {
            super(source, type);
            this.locations = locations;
        }

        public Map<V, Point2D> getLocations() {
            return locations;
        }

        @Override
        public String toString() {
            return "GraphEvent type:"+type;
        }
    }

    public static class Vertex<V,E> extends GraphEvent<V,E> {
        protected V vertex;

        /**
         * Creates a graph event for the specified graph, vertex, and type.
         */
        public Vertex(Graph<V,E> source, Type type, V vertex) {
            super(source,type);
            this.vertex = vertex;
        }

        /**
         * Retrieves the vertex associated with this event.
         */
        public V getVertex() {
            return vertex;
        }

        @Override
        public String toString() {
            return "GraphEvent type:"+type+" for "+vertex;
        }

    }

    public static class Edge<V,E> extends GraphEvent<V,E> {
        protected E edge;

        /**
         * Creates a graph event for the specified graph, edge, and type.
         */
        public Edge(Graph<V,E> source, Type type, E edge) {
            super(source,type);
            this.edge = edge;
        }

        /**
         * Retrieves the edge associated with this event.
         */
        public E getEdge() {
            return edge;
        }

        @Override
        public String toString() {
            return "GraphEvent type:"+type+" for "+edge;
        }

    }

    public Graph<V, E> getSource() {
        return source;
    }

    public Type getType() {
        return type;
    }

}
