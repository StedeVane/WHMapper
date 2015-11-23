package com.sifno.stellarmap.graphdrawing.layout;

import com.sifno.stellarmap.graphdrawing.Graph;

import java.awt.*;

/**
 * Created by Алёна on 23.11.2015.
 */
public class LayoutFactory<V,E> {

    Algorithm algorithm;
    Dimension size;

    public LayoutFactory(Algorithm algorithm, Dimension size) {
        this.algorithm = algorithm;
        this.size = size;
    }

    public Layout<V,E> getLayout(Graph<V,E> graph) {

        Layout<V,E> layout = new Layout<>();
        layout.setGraph(graph);
        layout.setSize(size);


        algorithm


        return layout;
    }

    public Layout<V,E> getLayout(Graph<V,E> graph, Layout<V,E> layout);



}
