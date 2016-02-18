package com.sifno.stellarmap.graph;


import java.util.*;

public class UndirectedSpareGraph<V,E> extends AbstractGraph<V,E> implements Graph<V,E> {

    private Set<V> vertices = new HashSet<>();
    private Map<E, Pair<V>> edges = new HashMap<>();
    private Map<V,Map<E,V>> neighbors = new HashMap<>();

    public UndirectedSpareGraph() {
        vertices = new HashSet<>();
        edges = new HashMap<>();
        neighbors = new HashMap<>();
    }

    public UndirectedSpareGraph(GraphAdapter<V,E> graph) {
        vertices = new HashSet<>(graph.getVertices());

        edges = new HashMap<>();
        for (E edge: graph.getEdges()) {
            edges.put(edge,graph.getEdge(edge));
        }

        neighbors = new HashMap<>();
        for (V vertix: vertices) {
            Map<E,V> neighorEdges = new HashMap<>();
            for (V neighor: graph.getNeighbors(vertix)) {
                neighorEdges.put(graph.getEdge(vertix,neighor),neighor);
            }
            neighbors.put(vertix, neighorEdges);
        }
    }


    @Override
    public boolean addVertex(V vertex) {
        if (vertex == null) throw  new NullPointerException("GraphAdapter cannot contain null value");
        if (vertices.contains(vertex)) return false;

        vertices.add(vertex);
        neighbors.put(vertex, new HashMap<E,V>());

        return super.addVertex(vertex);
    }

    @Override
    public boolean addEdge(E e, V v1, V v2) {
        if (e == null || v1 == null || v2 == null) throw  new IllegalArgumentException("parameters must not be null");
        if (!vertices.contains(v1) || !vertices.contains(v2)) throw new IllegalStateException("There are no the vertex in the graph");

        if (edges.containsKey(e)) return false;

        edges.put(e,new Pair<V>(v1,v2));
        neighbors.get(v1).put(e,v2);
        neighbors.get(v2).put(e,v1);
        return super.addEdge(e,v1,v2);
    }

    @Override
    public boolean removeVertex(V vertex) {
        if (!vertices.contains(vertex)) return false;

        vertices.remove(vertex);
        for (E e: neighbors.get(vertex).keySet()) {
            V v = neighbors.get(vertex).get(e);

            edges.remove(e);
            neighbors.get(v).remove(e);
        }
        neighbors.remove(vertex);
        return super.removeVertex(vertex);
    }

    @Override
    public boolean removeEdge(E edge) {
        if (!edges.containsKey(edge)) return false;
        edges.remove(edge);
        return super.removeEdge(edge);
    }

    @Override
    public boolean containsVertex(V vertex) {
        return vertices.contains(vertex);
    }


    @Override
    public Pair<V> getEdge(E e) {
        if (e == null) throw  new IllegalArgumentException("parameter must not be null");
        return edges.get(e);
    }

    @Override
    public E getEdge(V v1, V v2) {
        Map<E,V> neighorEdges = neighbors.get(v1);
        if (neighorEdges==null) return null;

        for (E edge: neighorEdges.keySet()) {
            if (neighorEdges.get(edge).equals(v2)) return edge;
        }
        return null;
    }

    @Override
    public Collection<V> getVertices() {
        return vertices;
    }

    @Override
    public Collection<E> getEdges() {
        return edges.keySet();
    }

    @Override
    public Collection<V> getNeighbors(V vertex) {
        return neighbors.get(vertex).values();
    }
}
