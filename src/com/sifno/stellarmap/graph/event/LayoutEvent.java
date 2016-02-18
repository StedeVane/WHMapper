package com.sifno.stellarmap.graph.event;


import com.sifno.stellarmap.graph.PlanarGraph;

public class LayoutEvent<V, E> {

    protected PlanarGraph<V,E> source;
    protected LayoutEvent.Type type;

    public LayoutEvent(PlanarGraph<V, E> source, LayoutEvent.Type type) {
        this.source = source;
        this.type = type;
    }

    public PlanarGraph<V, E> getSource() {
        return this.source;
    }

    public LayoutEvent.Type getType() {
        return this.type;
    }

    public static class Vertex<V, E> extends LayoutEvent<V, E> {
        protected V vertex;

        public Vertex(PlanarGraph<V, E> source, Type type, V vertex) {
            super(source, type);
            this.vertex = vertex;
        }

        public V getVertex() {
            return this.vertex;
        }

        public String toString() {
            return "LayoutEvent type:" + this.type + " for " + this.vertex;
        }
    }

    public static enum Type {
        VERTEX_LOCKED,
        VERTEX_RELOCATE;

        private Type() {
        }

    }

}
