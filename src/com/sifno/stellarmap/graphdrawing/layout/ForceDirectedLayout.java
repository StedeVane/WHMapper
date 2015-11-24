package com.sifno.stellarmap.graphdrawing.layout;

import com.google.gwt.user.client.Random;
import com.sifno.stellarmap.graphdrawing.Graph;
import com.sifno.stellarmap.graphdrawing.GraphEvent;
import com.sifno.stellarmap.graphdrawing.GraphEventListener;
import com.sifno.stellarmap.graphdrawing.ObservableGraph;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

/**
 * Created by Алёна on 23.11.2015.
 */
public class ForceDirectedLayout<V,E> extends LayoutDecorator<V,E> {

    ForceDirectedAlgorithm<V,E> algorithm = new ForceDirectedAlgorithm<>();

    public ForceDirectedLayout(Layout<V, E> delegate) {
        super(delegate);
    }

    private void layoutUpdate() {
//        algorithm.leadToEquilibrium(this);

    }

    private void changeGraphInitialization() {
        Map<V, Point2D> temp = new HashMap<>();
        for (V vertex: getGraph().getVertices()) {
            if (getLocations().containsKey(vertex))
                temp.put(vertex,getLocations().get(vertex));
            else {
                temp.put(vertex,new Point2D.Double(Random.nextDouble()*getSize().getWidth(),Random.nextDouble()*getSize().getHeight()));
            }
        }
        setLocations(temp);
    }

    private void changeSizeInitialization(double widthRatio, double heightRatio) {
        for (Point2D point: getLocations().values()) {
            point.setLocation(point.getX()*widthRatio, point.getY()*heightRatio);
        }
        layoutUpdate();
    }

    @Override
    public void setGraph(Graph<V, E> graph) {
        ObservableGraph obsGraph = new ObservableGraph<>(graph);

        super.setGraph(obsGraph);

        obsGraph.addGraphEventListener(new GraphEventListener<V, E>() {
            @Override
            public void handleGraphEvent(GraphEvent<V, E> event) {
                changeGraphInitialization();
            }
        });
        changeGraphInitialization();
    }

    @Override
    public void setSize(Dimension size) {

        if (size.getWidth() <= 0 || size.getHeight() <= 0) throw new IllegalArgumentException("size must be positive");
        double widthRatio = size.getWidth() / getSize().getWidth();
        double heightRatio = size.getHeight() / getSize().getHeight();

        super.setSize(size);

        changeSizeInitialization(widthRatio, heightRatio);

    }

    @Override
    public void setLocation(V vertex, Point2D point) {
        super.setLocation(vertex,point);
     //   layoutUpdate();
    }

    @Override
    public void setLocations(Map<V, Point2D> locations) {
        super.setLocations(locations);
     //   layoutUpdate();
    }


}
