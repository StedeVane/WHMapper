package com.sifno.stellarmap.graph.event;


import com.sifno.stellarmap.graph.Graph;

public class GraphEvent<V, E> {
    protected Graph<V, E> source;
    protected GraphEvent.Type type;

    public GraphEvent(Graph<V, E> source, GraphEvent.Type type) {
        this.source = source;
        this.type = type;
    }

    public Graph<V, E> getSource() {
        return this.source;
    }

    public GraphEvent.Type getType() {
        return this.type;
    }

    public static class Edge<V, E> extends GraphEvent<V, E> {
        protected E edge;

        public Edge(Graph<V, E> source, GraphEvent.Type type, E edge) {
            super(source, type);
            this.edge = edge;
        }

        public E getEdge() {
            return this.edge;
        }

        public String toString() {
            return "GraphEvent type:" + this.type + " for " + this.edge;
        }
    }

    public static class Vertex<V, E> extends GraphEvent<V, E> {
        protected V vertex;

        public Vertex(Graph<V, E> source, GraphEvent.Type type, V vertex) {
            super(source, type);
            this.vertex = vertex;
        }

        public V getVertex() {
            return this.vertex;
        }

        public String toString() {
            return "GraphEvent type:" + this.type + " for " + this.vertex;
        }
    }

    public static enum Type {
        VERTEX_ADDED,
        VERTEX_REMOVED,
        EDGE_ADDED,
        EDGE_REMOVED;

        private Type() {
        }

    }
}
