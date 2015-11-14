package com.sifno.graphdrawing;

/**
 * Created by Алёна on 13.11.2015.
 */
public interface Graph<V,E> {

    public void addVertex(V vertex);
    public void addEdge(E e, V v1, V v2);


}
